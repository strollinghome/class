#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "tables.h"
#include "translate_utils.h"
#include "translate.h"

/* Writes instructions during the assembler's first pass to OUTPUT. The case
   for general instructions has already been completed, but you need to write
   code to translate the li and blt pseudoinstructions. Your pseudoinstruction 
   expansions should not have any side effects.

   NAME is the name of the instruction, ARGS is an array of the arguments, and
   NUM_ARGS specifies the number of items in ARGS.

   Error checking for regular instructions are done in pass two. However, for
   pseudoinstructions, you must make sure that ARGS contains the correct number
   of arguments. You do NOT need to check whether the registers / label are 
   valid, since that will be checked in part two.

   Also for li:
    - make sure that the number is representable by 32 bits. (Hint: the number 
        can be both signed or unsigned).
    - if the immediate can fit in the imm field of an addiu instruction, then
        expand li into a single addiu instruction. Otherwise, expand it into 
        a lui-ori pair.

   And for blt:
    - your expansion should use the fewest number of instructions possible.

   MARS has slightly different translation rules for li, and it allows numbers
   larger than the largest 32 bit number to be loaded with li. You should follow
   the above rules if MARS behaves differently.

   Use fprintf() to write. If writing multiple instructions, make sure that 
   each instruction is on a different line.

   Returns the number of instructions written (so 0 if there were any errors).
 */
unsigned write_pass_one(FILE* output, const char* name, char** args, int num_args) {
    long int immediate;
    if (strcmp(name, "li") == 0) {
        if (num_args == 2) {
            int err = translate_num(&immediate, args[1], -32768, 65535);
            if (!err) {
                strcpy(args[0], args[0]);
                strcpy(args[2], args[1]); // immediate into 3rd spot, make room for $rs = $0
                strcpy(args[1], "$0");
                write_inst_string(output, "addiu", args, 3);
                return 1;
            } else {
                int err = translate_num(&immediate, args[1], -2147483648, 4294967295);
                if (err) {
                    return 0;
                }
                uint16_t upper = (immediate >> 16) & 0xFFFF; 
                uint16_t lower = immediate & 0xFFFF;
                char temp_rt[24]; // fix magic num
                strcpy(temp_rt, args[0]);
                strcpy(args[0], "$at");
                sprintf(args[1], "%d", upper);
                write_inst_string(output, "lui", args, 2);
                strcpy(args[0],  temp_rt);
                strcpy(args[1], "$at");
                sprintf(args[2], "%d", lower);
                write_inst_string(output, "ori", args, 3);
                return 2;
            }
        }
        return 0;
    } else if (strcmp(name, "blt") == 0) {
        if (num_args == 3) {
            char label[1024]; // Fix this magic num
            strcpy(label, args[2]);
            strcpy(args[2], args[1]); 
            strcpy(args[1], args[0]);
            strcpy(args[0], "$at"); // trust me
            write_inst_string(output, "slt", args, 3);
            strcpy(args[1], "$0");
            strcpy(args[2], label);
            write_inst_string(output, "bne", args, 3);
            return 2;
        }
        return -1;
    } else {
        write_inst_string(output, name, args, num_args);
        return 1;
    }
}

/* Writes the instruction in hexadecimal format to OUTPUT during pass #2.
   
   NAME is the name of the instruction, ARGS is an array of the arguments, and
   NUM_ARGS specifies the number of items in ARGS. 

   The symbol table (SYMTBL) is given for any symbols that need to be resolved
   at this step. If a symbol should be relocated, it should be added to the
   relocation table (RELTBL), and the fields for that symbol should be set to
   all zeros. 

   You must perform error checking on all instructions and make sure that their
   arguments are valid. If an instruction is invalid, you should not write 
   anything to OUTPUT but simply return -1. MARS may be a useful resource for
   this step.

   Note the use of helper functions. Consider writing your own! If the function
   definition comes afterwards, you must declare it first (see translate.h).

   Returns 0 on success and -1 on error. 
 */
int translate_inst(FILE* output, const char* name, char** args, size_t num_args, uint32_t addr,
    SymbolTable* symtbl, SymbolTable* reltbl) {
    if (strcmp(name, "addu") == 0)       return write_rtype(0x21, output, args, num_args);
    else if (strcmp(name, "or") == 0)    return write_rtype(0x25, output, args, num_args);
    else if (strcmp(name, "slt") == 0)   return write_rtype(0x2a, output, args, num_args);
    else if (strcmp(name, "sltu") == 0)  return write_rtype(0x2b, output, args, num_args);
    else if (strcmp(name, "sll") == 0)   return write_shift(0x00, output, args, num_args);
    else if (strcmp(name, "jr") == 0)   return write_jr(0x8, output, args, num_args);
    else if (strcmp(name, "addiu") == 0)   return write_addiu(0x9, output, args, num_args);
    else if (strcmp(name, "ori") == 0)   return write_ori(0xd, output, args, num_args);
    else if (strcmp(name, "lui") == 0)   return write_lui(0xf, output, args, num_args);
    else if (strcmp(name, "lb") == 0)   return write_itype(0x20, output, args, num_args);
    else if (strcmp(name, "lbu") == 0)   return write_itype(0x24, output, args, num_args);
    else if (strcmp(name, "lw") == 0)   return write_itype(0x23, output, args, num_args);
    else if (strcmp(name, "sb") == 0)   return write_mem(0x28, output, args, num_args);
    else if (strcmp(name, "sw") == 0)   return write_mem(0x2b, output, args, num_args);
    else if (strcmp(name, "beq") == 0)   return write_branch(0x4, output, args, num_args, addr, symtbl);
    else if (strcmp(name, "bne") == 0)   return write_branch(0x5, output, args, num_args, addr, symtbl);
    else if (strcmp(name, "j") == 0)   return write_jump(0x2, output, args, num_args, addr, reltbl);
    else if (strcmp(name, "jal") == 0)   return write_jump(0x3, output, args, num_args, addr, reltbl);
    else                                 return -1;
}

/* A helper function for writing most R-type instructions. You should use
   translate_reg() to parse registers and write_inst_hex() to write to 
   OUTPUT. Both are defined in translate_utils.h.

   This function is INCOMPLETE. Complete the implementation below. You will
   find bitwise operations to be the cleanest way to complete this function.
 */
int write_rtype(uint8_t funct, FILE* output, char** args, size_t num_args) {
    // Perhaps perform some error checking?

    int rd = translate_reg(args[0]) << 11;
    int rs = translate_reg(args[1]) << 21;
    int rt = translate_reg(args[2]) << 16;

    uint32_t instruction = 0;
    instruction |= rd | rs | rt | funct;
    write_inst_hex(output, instruction);
    return 0;
}

/* A helper function for writing shift instructions. You should use 
   translate_num() to parse numerical arguments. translate_num() is defined
   in translate_utils.h.
 */
int write_shift(uint8_t funct, FILE* output, char** args, size_t num_args) {
	// Perhaps perform some error checking?
    long int shamt;
    int rd = translate_reg(args[0]) << 11;
    int rt = translate_reg(args[1]) << 16;
    int err = translate_num(&shamt, args[2], 0, 31);
    if (!err) {
        uint32_t instruction = 0;
        shamt <<= 6;
        instruction |= rd | rt | shamt | funct;
        write_inst_hex(output, instruction);
    }
    return err;
}

int write_itype(uint8_t opcode, FILE* output, char** args, size_t num_args) {
    int rt = translate_reg(args[0]) << 16;
    int rs = translate_reg(args[2]) << 21;
    long int offset;
    int err = translate_num(&offset, args[1], -32768, 32767);
    if (!err) {
        uint32_t opcodeS = opcode << 26;
        offset &= 0xffff; // this gets id of bits greater than 15th in case of neg. offset
        uint32_t instruction = 0;
        instruction |= opcodeS | rs | rt | offset;
        write_inst_hex(output, instruction);
    }
    return err;
}

int write_jr(uint8_t funct, FILE* output, char** args, size_t num_args) {
    if (num_args == 1) {
        int rs = translate_reg(args[0]) << 21;
        uint32_t instruction = 0;
        instruction |= rs | funct;
        write_inst_hex(output, instruction);
        return 0;
    }
    return -1;
}


int write_addiu(uint8_t opcode, FILE* output, char** args, size_t num_args) {
    if (num_args == 3) {
        int long immediate;
	    uint32_t opcodeS = opcode << 26;
        int rt = translate_reg(args[0]) << 16;
        int rs = translate_reg(args[1]) << 21;
        int err = translate_num(&immediate, args[2], 0, 65535);
        if (!err) {
            uint32_t instruction = 0;
            immediate &= 0xffff; // This gets rid of bits greater than 15th in case of neg. imm.
            instruction |= opcodeS | rs | rt | immediate;
            write_inst_hex(output, instruction);
        }
        return err;
    }
    return -1;
}


int write_ori(uint8_t opcode, FILE* output, char** args, size_t num_args) {
    if (num_args == 3) {
        int long immediate;
        uint32_t opcodeS = opcode << 26;
        int rt = translate_reg(args[0]) << 16;
        int rs = translate_reg(args[1]) << 21;
        int err = translate_num(&immediate, args[2], 0, 65535);
        if (!err) {
            uint32_t instruction = 0;
            instruction |= opcodeS | rs | rt | immediate;
            write_inst_hex(output, instruction);
        }
        return err;
    }
    return -1;
}


int write_lui(uint8_t opcode, FILE* output, char** args, size_t num_args) {
    if (num_args == 2) {
        int long immediate;
        uint32_t opcodeS = opcode << 26;
        int rt = translate_reg(args[0]) << 16;
        int err = translate_num(&immediate, args[1], -32768, 32767);
        if (!err) {
            uint32_t instruction = 0;
            instruction |= opcodeS | rt | immediate;
            write_inst_hex(output, instruction);
        }
        return err;
    }
    return -1;
}

int write_mem(uint8_t opcode, FILE* output, char** args, size_t num_args) {
    if (num_args == 3) {
        int long immediate;
	    uint32_t opcodeS = opcode << 26;
        int rt = translate_reg(args[0]) << 16;
        int rs = translate_reg(args[2]) << 21;
        int err = translate_num(&immediate, args[1], -32768, 32767);
        if (!err) {
            uint32_t instruction = 0;
            immediate &= 0xffff; // This gets rid of bits greater than 15th in case of neg. imm.
            instruction |= opcodeS | rs | rt | immediate;
            write_inst_hex(output, instruction);
        }
        return err;
    }
    return -1;
}


int write_branch(uint8_t opcode, FILE* output, char** args, size_t num_args, 
    uint32_t addr, SymbolTable* symtbl) {
    if (num_args == 3) {
        int rt = translate_reg(args[1]) << 16;
        int rs = translate_reg(args[0]) << 21;
        char* label = args[2];
        uint32_t instruction = 0;
    	uint32_t opcodeS = opcode << 26;
        uint16_t PCoffset = (get_addr_for_symbol(symtbl, label) - (addr + 4)) / 4; // addr is PC
            // label is already in symtbl.
        PCoffset &= 0xffff;
        instruction |= opcodeS | rs | rt | PCoffset;
        write_inst_hex(output, instruction);
        return 0;
    }
    return -1;
}

int write_jump(uint8_t opcode, FILE* output, char** args, size_t num_args, 
    uint32_t addr, SymbolTable* reltbl) {
    if (num_args == 1) {
        char* label = args[0];
        uint32_t instruction = 0;
        uint32_t opcodeS = opcode << 26;
        add_to_table(reltbl, label, addr); // adds j's address and the label it'd jump to
        instruction |= opcodeS;
        write_inst_hex(output, instruction);
        return 0;
    }
    return -1;
}
