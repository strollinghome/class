
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "utils.h"
#include "tables.h"

const int SYMTBL_NON_UNIQUE = 0;
const int SYMTBL_UNIQUE_NAME = 1;

/*******************************
 * Helper Functions
 *******************************/

void allocation_failed() {
    write_to_log("Error: allocation failed\n");
    exit(1);
}

void addr_alignment_incorrect() {
    write_to_log("Error: address is not a multiple of 4.\n");
}

void name_already_exists(const char* name) {
    write_to_log("Error: name '%s' already exists in table.\n", name);
}

void write_symbol(FILE* output, uint32_t addr, const char* name) {
    fprintf(output, "%u\t%s\n", addr, name);
}

/*******************************
 * Symbol Table Functions
 *******************************/

/* Creates a new SymbolTable containg 0 elements and returns a pointer to that
   table. Multiple SymbolTables may exist at the same time. 
   If memory allocation fails, you should call allocation_failed().
   Mode will be either SYMTBL_NON_UNIQUE or SYMTBL_UNIQUE_NAME. You will need
   to store this value for use during add_to_table(). 
 */
SymbolTable* create_table(int mode) {
    SymbolTable *retval;

    retval = malloc(sizeof(SymbolTable));
    if (retval == NULL) {
        free_table(retval);
        allocation_failed();
    }

    retval->len = 0;
    retval->tbl = malloc(1 * sizeof(Symbol));

    if (retval->tbl == NULL) {
        free_table(retval);
        allocation_failed();
    }

    return retval;
}

/* Frees the given SymbolTable and all associated memory. */
void free_table(SymbolTable* table) {
    // Free the Symbol* then the Table
    Symbol *marker = table->tbl;
    for (int i = 0; i < table->len; i++) {
        free((marker[i]).name);
    }
    free(table->tbl);
    free(table);
}

/* Adds a new symbol and its address to the SymbolTable pointed to by TABLE. 
   ADDR is given as the byte offset from the first instruction. The SymbolTable
   must be able to resize itself as more elements are added. 

   Note that NAME may point to a temporary array, so it is not safe to simply
   store the NAME pointer. You must store a copy of the given string.

   If ADDR is not word-aligned, you should call addr_alignment_incorrect() and
   return -1. If the table's mode is SYMTBL_UNIQUE_NAME and NAME already exists
   in the table, you should call name_already_exists() and return -1. If memory
   allocation fails, you should call allocation_failed(). 

   Otherwise, you should store the symbol name and address and return 0.
 */
int add_to_table(SymbolTable* table, const char* name, uint32_t addr) {
    // If not multiple of four then WRONG
    if (addr % 4 != 0) {
        addr_alignment_incorrect();
        return -1;
    }

    // If table contains name, then BAD (if mode is NON_UNIQUE)
    if (table_contains(table, name) != -1 && table->mode == SYMTBL_NON_UNIQUE) {
        name_already_exists(name);
        return -1;
    }

    // Saw this convenient thing on fb, reallocate auto-copies old contents.
    table->tbl = realloc(table->tbl, (table->len + 1) * sizeof(Symbol));
    if (table->tbl == NULL) {
        free_table(table);
        allocation_failed();
        return -1;
    }

    // One extra spot available now from the realloc; add stuff into the symbol.
    table->tbl[table->len].name = malloc((strlen(name) + 1));
    strcpy(table->tbl[table->len].name, name);
    table->tbl[table->len].addr = addr;
    table->len = table->len + 1;
    return 0;
}

/* Returns an index if the table contains name, -1 otherwise. */
int table_contains(SymbolTable* table, const char* name) {
    Symbol *marker = table->tbl;
    for (int i = 0; i < table->len; i++) {
        if (strcmp(marker[i].name, name) == 0) {
            return i;
        }
    }
    return -1;
}

/* Returns the address (byte offset) of the given symbol. If a symbol with name
   NAME is not present in TABLE, return -1.
 */
int64_t get_addr_for_symbol(SymbolTable* table, const char* name) {
    int retval = table_contains(table, name);
    if (retval != -1) {
        return table->tbl[retval].addr;
    }
    return -1;   
}

/* Writes the SymbolTable TABLE to OUTPUT. You should use write_symbol() to
   perform the write. Do not print any additional whitespace or characters.
 */
void write_table(SymbolTable* table, FILE* output) {
    Symbol *marker = table -> tbl;
    for (int i = 0; i < table -> len; i++) {
        write_symbol(output, marker[i].addr, marker[i].name);
    }
}
