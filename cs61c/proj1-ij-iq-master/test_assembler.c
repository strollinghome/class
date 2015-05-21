#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <CUnit/Basic.h>

#include "src/utils.h"
#include "src/tables.h"
#include "src/translate_utils.h"
#include "src/translate.h"

const char* TMP_FILE = "test_output.txt";
const int BUF_SIZE = 1024;

/****************************************
 *  Helper functions 
 ****************************************/

int do_nothing() {
    return 0;
}

int init_log_file() {
    set_log_file(TMP_FILE);
    return 0;
}

int check_lines_equal(char **arr, int num) {
    char buf[BUF_SIZE];

    FILE *f = fopen(TMP_FILE, "r");
    if (!f) {
        CU_FAIL("Could not open temporary file");
        return 0;
    }
    for (int i = 0; i < num; i++) {
        if (!fgets(buf, BUF_SIZE, f)) {
            CU_FAIL("Reached end of file");
            return 0;
        }
        CU_ASSERT(!strncmp(buf, arr[i], strlen(arr[i])));
    }
    fclose(f);
    return 0;
}

/****************************************
 *  Test cases for translate_utils.c 
 ****************************************/

void test_translate_reg() {
    CU_ASSERT_EQUAL(translate_reg("$0"), 0);
    CU_ASSERT_EQUAL(translate_reg("$at"), 1);
    CU_ASSERT_EQUAL(translate_reg("$v0"), 2);
    CU_ASSERT_EQUAL(translate_reg("$a0"), 4);
    CU_ASSERT_EQUAL(translate_reg("$a1"), 5);
    CU_ASSERT_EQUAL(translate_reg("$a2"), 6);
    CU_ASSERT_EQUAL(translate_reg("$a3"), 7);
    CU_ASSERT_EQUAL(translate_reg("$t0"), 8);
    CU_ASSERT_EQUAL(translate_reg("$t1"), 9);
    CU_ASSERT_EQUAL(translate_reg("$t2"), 10);
    CU_ASSERT_EQUAL(translate_reg("$t3"), 11);
    CU_ASSERT_EQUAL(translate_reg("$s0"), 16);
    CU_ASSERT_EQUAL(translate_reg("$s1"), 17);
    CU_ASSERT_EQUAL(translate_reg("$3"), -1);
    CU_ASSERT_EQUAL(translate_reg("asdf"), -1);
    CU_ASSERT_EQUAL(translate_reg("hey there"), -1);
}

void test_translate_num() {
    long int output;

    CU_ASSERT_EQUAL(translate_num(&output, "35", -1000, 1000), 0);
    CU_ASSERT_EQUAL(output, 35);
    CU_ASSERT_EQUAL(translate_num(&output, "145634236", 0, 9000000000), 0);
    CU_ASSERT_EQUAL(output, 145634236);
    CU_ASSERT_EQUAL(translate_num(&output, "0xC0FFEE", -9000000000, 9000000000), 0);
    CU_ASSERT_EQUAL(output, 12648430);
    CU_ASSERT_EQUAL(translate_num(&output, "72", -16, 72), 0);
    CU_ASSERT_EQUAL(output, 72);
    CU_ASSERT_EQUAL(translate_num(&output, "72", -16, 71), -1);
    CU_ASSERT_EQUAL(translate_num(&output, "72", 72, 150), 0);
    CU_ASSERT_EQUAL(output, 72);
    CU_ASSERT_EQUAL(translate_num(&output, "72", 73, 150), -1);
    CU_ASSERT_EQUAL(translate_num(&output, "35x", -100, 100), -1);
}

/****************************************
 *  Test cases for tables.c 
 ****************************************/

void test_table_1() {
    int retval;

    SymbolTable* tbl = create_table(SYMTBL_UNIQUE_NAME);
    CU_ASSERT_PTR_NOT_NULL(tbl);

    retval = add_to_table(tbl, "abc", 8);
    CU_ASSERT_EQUAL(retval, 0);
    retval = add_to_table(tbl, "efg", 12);
    CU_ASSERT_EQUAL(retval, 0);
    retval = add_to_table(tbl, "q45", 16);
    CU_ASSERT_EQUAL(retval, 0);
    retval = add_to_table(tbl, "q45", 24); 
    CU_ASSERT_EQUAL(retval, -1); 
    retval = add_to_table(tbl, "bob", 14); 
    CU_ASSERT_EQUAL(retval, -1); 

    retval = get_addr_for_symbol(tbl, "abc");
    CU_ASSERT_EQUAL(retval, 8); 
    retval = get_addr_for_symbol(tbl, "q45");
    CU_ASSERT_EQUAL(retval, 16); 
    retval = get_addr_for_symbol(tbl, "ef");
    CU_ASSERT_EQUAL(retval, -1);
    
    free_table(tbl);

    char* arr[] = { "Error: name 'q45' already exists in table.",
                    "Error: address is not a multiple of 4." };
    check_lines_equal(arr, 2);

    SymbolTable* tbl2 = create_table(SYMTBL_NON_UNIQUE);
    CU_ASSERT_PTR_NOT_NULL(tbl2);

    retval = add_to_table(tbl, "q45", 16);
    CU_ASSERT_EQUAL(retval, 0);
    retval = add_to_table(tbl, "q45", 24);
    CU_ASSERT_EQUAL(retval, 0);

    free_table(tbl2);
}

void test_table_2() {
    int retval, max = 100;

    SymbolTable* tbl = create_table(SYMTBL_UNIQUE_NAME);
    CU_ASSERT_PTR_NOT_NULL(tbl);

    char buf[10];
    for (int i = 0; i < max; i++) {
        sprintf(buf, "%d", i);
        retval = add_to_table(tbl, buf, 4 * i);
        CU_ASSERT_EQUAL(retval, 0);
    }

    for (int i = 0; i < max; i++) {
        sprintf(buf, "%d", i);
        retval = get_addr_for_symbol(tbl, buf);
        CU_ASSERT_EQUAL(retval, 4 * i);
    }

    free_table(tbl);
}

/****************************************
 *  Test cases for write_<inst> (custom) 
 ****************************************/

void test_all_write_inst() {
    FILE* out = fopen("test_write_inst_out.txt", "w");
    char* args[3]; args[0] = malloc(20); args[1] = malloc(20); args[2] = malloc(20); // max args = 3

    strcpy(args[0], "$t0"); strcpy(args[1], "$t1"); strcpy(args[2], "$t2");
    write_rtype(0x21, out, args, 3); // addu
    strcpy(args[0], "$t3"); strcpy(args[1], "$t0"); strcpy(args[2], "$t1");
    write_rtype(0x25, out, args, 3); // or
    strcpy(args[0], "$t2"); strcpy(args[1], "$t3"); strcpy(args[2], "$t0");
    write_rtype(0x2a, out, args, 3); // slt
    strcpy(args[0], "$t1"); strcpy(args[1], "$t2"); strcpy(args[2], "$t3");
    write_rtype(0x2b, out, args, 3); // sltu

    strcpy(args[0], "$t0"); strcpy(args[1], "$t1"); strcpy(args[2], "4");
    write_shift(0x00, out, args, 3); // sll

    strcpy(args[0], "$t3");
    write_jr(0x8, out, args, 1); // jr

    strcpy(args[0], "$t0"); strcpy(args[1], "$t1"); strcpy(args[2], "100");
    write_addiu(0x9, out, args, 3); // addiu

    strcpy(args[0], "$t1"); strcpy(args[1], "$t2"); strcpy(args[2], "100");
    write_ori(0xd, out, args, 3); // ori

    strcpy(args[0], "$t2"); strcpy(args[1], "321"); 
    write_lui(0xf, out, args, 2); // lui

    strcpy(args[0], "$t1"); strcpy(args[1], "-100"); strcpy(args[2], "$t2");
    write_itype(0x20, out, args, 3); // lb
    strcpy(args[0], "$t1"); strcpy(args[1], "-100"); strcpy(args[2], "$t2");
    write_itype(0x24, out, args, 3); // lbu
    strcpy(args[0], "$t0"); strcpy(args[1], "16"); strcpy(args[2], "$t3");
    write_itype(0x23, out, args, 3); // lw

    strcpy(args[0], "$t1"); strcpy(args[1], "4"); strcpy(args[2], "$t2");
    write_mem(0x28, out, args, 3); // sb
    strcpy(args[0], "$t0"); strcpy(args[1], "16"); strcpy(args[2], "$t3");
    write_mem(0x2b, out, args, 3); // sw

    strcpy(args[0], "$t1"); strcpy(args[1], "$t2"); strcpy(args[2], "Foo");
    SymbolTable* symtbl = create_table(SYMTBL_NON_UNIQUE);
    add_to_table(symtbl, args[2], 0x03f8);
    write_branch(0x4, out, args, 3, 0x03f8, symtbl); // beq
    strcpy(args[0], "$t1"); strcpy(args[1], "$t2"); strcpy(args[2], "Foo");
    write_branch(0x5, out, args, 3, 0x03fc, symtbl); // bne
    free_table(symtbl);

    strcpy(args[0], "Biz");
    SymbolTable* reltbl = create_table(SYMTBL_UNIQUE_NAME);
    write_jump(0x2, out, args, 1, 0x02fa, reltbl); // j
    strcpy(args[0], "Baz");
    write_jump(0x3, out, args, 1, 0x03e4, reltbl); // jal
    free_table(reltbl);


    fclose(out);

    char* arr[] = {"012a4021\n", "01095825\n", "0168502a\n", "014b482b\n",
                    "00094100\n", "01600008\n", "25280064\n", "35490064\n",
                    "3c0a0141\n", "8149ff9c\n", "9149ff9c\n", "8d680010\n",
                    "a1490004\n", "ad680010\n", "112affff\n", "152afffe\n",
                    "08000000\n", "0c000000\n"};

    const int LINE_SIZE = 512;
    char actual[LINE_SIZE];

    out = fopen("test_write_inst_out.txt", "r");
    for (int i = 0; i < 18; i++) {
        CU_ASSERT_PTR_NOT_NULL(fgets(actual, LINE_SIZE, out));
        printf("\n%s -----> %s", actual, arr[i]);
        CU_ASSERT(!strcmp(actual, arr[i]));
    }
    free(args[0]); free(args[1]); free(args[2]);
}

/****************************************
 *  Test cases for pseudo inst's (custom)
 ****************************************/

void test_pseudo_conversion() {
    FILE *out = fopen("pseudo_expand_tests.txt", "w");
    char* args[3]; args[0] = malloc(1024); args[1] = malloc(1024); args[2] = malloc(1024); // max args = 3
    int retval;

    strcpy(args[0], "$t1"); strcpy(args[1], "100000");
    retval = write_pass_one(out, "li", args, 2); // li -> lui,ori
    CU_ASSERT(retval == 2);
    strcpy(args[0], "$t1");  strcpy(args[1], "100");
    retval = write_pass_one(out, "li", args, 2); // li -> addiu
    CU_ASSERT(retval == 1);
    strcpy(args[1], "$t2"); strcpy(args[2], "Foo");
    retval = write_pass_one(out, "blt", args, 3); // blt
    CU_ASSERT(retval == 2);

    fclose(out);
    free(args[0]); free(args[1]); free(args[2]);

    char *arr[] = {"lui $at 1\n", "ori $t1 $at 34464\n", "addiu $t1 $0 100\n",
                    "slt $at $t1 $t2\n", "bne $at $0 Foo\n"};

    const int LINE_SIZE = 512;
    char actual[LINE_SIZE];
    out = fopen("pseudo_expand_tests.txt", "r");
    for (int i = 0; i < 5; i++) {
        CU_ASSERT_PTR_NOT_NULL(fgets(actual, LINE_SIZE, out));
        printf("\n%s -----> %s", actual, arr[i]);
        CU_ASSERT(!strcmp(actual, arr[i]));
    }
}

/****************************************
 *  Add your test cases here
 ****************************************/

int main(int argc, char** argv) {
    CU_pSuite pSuite1 = NULL, pSuite2 = NULL, pSuite3 = NULL, pSuite4 = NULL;

    if (CUE_SUCCESS != CU_initialize_registry()) {
        return CU_get_error();
    }

    /* Suite 1 */
    pSuite1 = CU_add_suite("Testing translate_utils.c", NULL, NULL);
    if (!pSuite1) {
        goto exit;
    }
    if (!CU_add_test(pSuite1, "test_translate_reg", test_translate_reg)) {
        goto exit;
    }
    if (!CU_add_test(pSuite1, "test_translate_num", test_translate_num)) {
        goto exit;
    }

    /* Suite 2 */
    pSuite2 = CU_add_suite("Testing tables.c", init_log_file, NULL);
    if (!pSuite2) {
        goto exit;
    }
    if (!CU_add_test(pSuite2, "test_table_1", test_table_1)) {
        goto exit;
    }
    if (!CU_add_test(pSuite2, "test_table_2", test_table_2)) {
        goto exit;
    }

    /* Suite 3 (custom) */
    pSuite3 = CU_add_suite("Testing all 'write_<instruction>' functions", NULL, NULL);
    if (!pSuite3) {
        goto exit;
    }
    if (!CU_add_test(pSuite3, "test_all_write_inst", test_all_write_inst)) {
        goto exit;
    }

    /* Suite 4 (custom) */
    pSuite4 = CU_add_suite("Testing pseudo instruction conversion", NULL, NULL);
    if (!pSuite4) {
        goto exit;
    }
    if (!CU_add_test(pSuite4, "test_pseudo_conversion", test_pseudo_conversion)) {
        goto exit;
    }

    CU_basic_set_mode(CU_BRM_VERBOSE);
    CU_basic_run_tests();

exit:
    CU_cleanup_registry();
    return CU_get_error();;
}
