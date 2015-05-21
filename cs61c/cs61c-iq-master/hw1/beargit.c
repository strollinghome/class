#include <stdio.h>
#include <string.h>

#include <unistd.h>
#include <sys/stat.h>

#include "beargit.h"
#include "util.h"

#include <time.h>
#include <stdlib.h>

/* Implementation Notes:
 *
 * - Functions return 0 if successful, 1 if there is an error.
 * - All error conditions in the function description need to be implemented
 *   and written to stderr. We catch some additional errors for you in main.c.
 * - Output to stdout needs to be exactly as specified in the function description.
 * - Only edit this file (beargit.c)
 * - You are given the following helper functions:
 *   * fs_mkdir(dirname): create directory <dirname>
 *   * fs_rm(filename): delete file <filename>
 *   * fs_mv(src,dst): move file <src> to <dst>, overwriting <dst> if it exists
 *   * fs_cp(src,dst): copy file <src> to <dst>, overwriting <dst> if it exists
 *   * write_string_to_file(filename,str): write <str> to filename (overwriting contents)
 *   * read_string_from_file(filename,str,size): read a string of at most <size> (incl.
 *     NULL character) from file <filename> and store it into <str>. Note that <str>
 *     needs to be large enough to hold that string.
 *  - You NEED to test your code. The autograder we provide does not contain the
 *    full set of tests that we will run on your code. See "Step 5" in the homework spec.
 */

/* beargit init
 *
 * - Create .beargit directory
 * - Create empty .beargit/.index file
 * - Create .beargit/.prev file containing 0..0 commit id
 *
 * Output (to stdout):
 * - None if successful
 */

static void fs_rm(const char* filename);

int beargit_init(void) {
    fs_mkdir(".beargit");

    FILE* findex = fopen(".beargit/.index", "w");
    fclose(findex);

    write_string_to_file(".beargit/.prev", "0000000000000000000000000000000000000000");

    return 0;
}


/* beargit add <filename>
 * 
 * - Append filename to list in .beargit/.index if it isn't in there yet
 *
 * Possible errors (to stderr):
 * >> ERROR: File <filename> already added
 *
 * Output (to stdout):
 * - None if successful
 */

int beargit_add(const char* filename) {
    FILE* findex = fopen(".beargit/.index", "r");
    FILE *fnewindex = fopen(".beargit/.newindex", "w");

    char line[FILENAME_SIZE];
    while(fgets(line, sizeof(line), findex)) {
        strtok(line, "\n");
        if (strcmp(line, filename) == 0) {
            fprintf(stderr, "ERROR: File %s already added\n", filename);
            fclose(findex);
            fclose(fnewindex);
            fs_rm(".beargit/.newindex");
            return 3;
        }

        fprintf(fnewindex, "%s\n", line);
    }

    fprintf(fnewindex, "%s\n", filename);
    fclose(findex);
    fclose(fnewindex);

    fs_mv(".beargit/.newindex", ".beargit/.index");

    return 0;
}


/* beargit rm <filename>
 */

int beargit_rm(const char* filename) {
    FILE* findex = fopen(".beargit/.index", "r");
    FILE* fnewindex = fopen(".beargit/.newindex", "w");
    char line[FILENAME_SIZE];
    int file_exists;
    file_exists = 0;
    while(fgets(line, sizeof(line), findex)) {
        strtok(line, "\n");
        if (strcmp(line, filename) != 0) {
            fprintf(fnewindex, "%s\n", line);
        } else if (strcmp(line, filename) == 0) {
            file_exists = 1;
        }
    }
    fclose(findex);
    fclose(fnewindex);
    fs_rm(".beargit/.index");
    fs_mv(".beargit/.newindex", ".beargit/.index");
    if (!file_exists) {
        fprintf(stderr, "ERROR: File %s not tracked\n", filename);
        return 1;
    }
    return 0;
}

/* beargit commit -m <msg>
 */

const char* go_bears = "GO BEARS!";

int is_commit_msg_ok(const char* msg) {
    const char* go_bears_copy = malloc(sizeof(go_bears));
    int char_count;
    while (*msg != '\0') {
        char_count = 0;
        go_bears_copy = go_bears;
        while (*msg == *go_bears_copy) {
            msg++;
            go_bears_copy++;
            char_count++;
        }
        if (char_count == 9 || char_count == 10) {
            return 1;
        }
        msg++;
    }
    return 0;
}

void next_commit_id(char* commit_id) {
    char path[FILENAME_SIZE] = ".beargit/";
    int index;
    if (commit_id[0] == '0') {
        for (int i = 0; commit_id[i] != '\0'; i++) {
            commit_id[i] = '6';
        }
    }
    index = COMMIT_ID_BYTES - 1;
    while (index >= 0) {
        if (commit_id[index] == '6' && commit_id[index - 1] == '6') {
            commit_id[index] = '1';
            break;
        } else if (commit_id[index] == '1') {
            commit_id[index] = 'c';
            break;
        } else if (commit_id[index] == 'c') {
            commit_id[index] = '6';
            index--;
        } else {
            index--;
        }
    }
    strcat(path, commit_id);
    fs_mkdir(path);
    
}

/* Generates ".beargit/<new id>
 * */
void create_path(char* path, char* commit_id) {
    strcpy(path + 9, commit_id);
    strcpy(path + 49, "/");
}

int beargit_commit(const char* msg) {
    FILE* findex = fopen(".beargit/.index", "r");
    FILE* fmsg;
    char line[FILENAME_SIZE];
    char path[FILENAME_SIZE] = ".beargit/";
    char prev[FILENAME_SIZE] = ".beargit/.prev";
    if (!is_commit_msg_ok(msg)) {
        fprintf(stderr, "ERROR: Message must contain \"%s\"\n", go_bears);
        return 1;
    }

    char commit_id[COMMIT_ID_SIZE];
    read_string_from_file(".beargit/.prev", commit_id, COMMIT_ID_SIZE);
    next_commit_id(commit_id);
    
    create_path(path, commit_id);
    strcpy(path + 50, ".prev");
    fs_cp(".beargit/.prev", path);
    create_path(path, commit_id);
    strcpy(path + 50, ".index");
    fs_cp(".beargit/.index", path);
    while(fgets(line, sizeof(line), findex)) {
        strtok(line, "\n");
        create_path(path, commit_id);
        strcpy(path + 50, line);
        fs_cp(line, path);
    }
    write_string_to_file(".beargit/.prev", commit_id);
    create_path(path, commit_id);
    strcpy(path + 50, ".msg");
    fmsg = fopen(path, "w");
    write_string_to_file(path, msg);
    return 0;
}

/* beargit status
 */

int beargit_status() {
    FILE* findex = fopen(".beargit/.index", "r");
    char line[FILENAME_SIZE];
    int file_count;
    file_count = 0;
    fprintf(stdout, "Tracked files:\n\n");
    while(fgets(line, sizeof(line), findex)) {
        strtok(line, "\n");
        fprintf(stdout, "  %s\n", line);
        file_count++;
    }
    fprintf(stdout, "\n%d files total\n", file_count);
    fclose(findex);
    return 0;
}

/* beargit log
 */

int beargit_log() {
    char prev[FILENAME_SIZE] = ".prev";
    char path[FILENAME_SIZE + COMMIT_ID_SIZE] = ".beargit/";
    char id[COMMIT_ID_SIZE];
    char msg[MSG_SIZE];
    strcpy(path + 9, prev);
    read_string_from_file(path, id, COMMIT_ID_SIZE);
    if (id[0] == '0') {
        fprintf(stderr, "ERROR: There are no commits!\n");
        return 1;
    }
    fprintf(stdout, "\n");
    while (id[0] != '0') {
        fprintf(stdout, "commit %s\n", id);
        create_path(path, id);
        strcpy(path + 50, ".msg");
        read_string_from_file(path, msg, MSG_SIZE);
        fprintf(stdout, "    %s\n\n", msg);
        create_path(path, id);
        strcpy(path + 50, prev);
        read_string_from_file(path, id, COMMIT_ID_SIZE);
    }
    return 0;
}
