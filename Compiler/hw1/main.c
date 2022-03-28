#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define FILE_NAME "error3.txt"

#define STsize 1000  // size of string table
#define HTsize 100  // size of hash table

#define FALSE 0
#define TRUE 1

#define isLetter(x) (((x) >= 'a' && (x) <= 'z') || ((x) >= 'A' && (x) <= 'Z'))
#define isDigit(x) ((x) >= '0' && (x) <= '9')
#define isUnderBar(x) ((x) == '_')

typedef struct HTentry* HTpointer;
typedef struct HTentry {
    int index;  // index of identifier in ST
    HTpointer next;  // pointer to next identifier
} HTentry;

enum errorTypes { noerror, illsp, illid, overst, longid };
typedef enum errorTypes ERRORtypes;

char seperators[] = " .,;:?!\t\n";

HTpointer HT[HTsize];
char ST[STsize];

int nextid = 0;  // the current identifier
int nextfree = 0;  // the next available index of ST
int hashcode;  // hash code of identifier
int sameid;  // first index of identifier

int found;  // for the previous occurrence of an identifier

ERRORtypes err;

FILE* fp;  // to be a pointer to FILE
char input;


// Initialize - Open input file
void initialize() {
    fp = fopen(FILE_NAME, "r");
    input = fgetc(fp);
}

// isSeperator - Distinguish the seperator
int isSeperator(char c) {
    int i;
    int sep_len;

    sep_len = strlen(seperators);
    for (i = 0; i < sep_len; i++) {
        if (c == seperators[i])
            return 1;
    }
    return 0;
}

// PrintHeading - Print the heading
void PrintHeading() {
    printf("\n\n");
    printf("  -----------      ------------ \n");
    printf("  Index in ST       identifier  \n");
    printf("  -----------      ------------ \n");
    printf("\n");
}

// 류서영 작성
/* PrintHStable - Prints the hash table. Write out the hashcode and the list of identifiers
                  associated with each hashcode, but only for non-empty lists.
                  Print out the number of characters used up in ST. */
void PrintHStable() {

    int i, currid;
    HTpointer currpt;
    printf("\n\n\n\n[[ HASH TABLE ]]\n\n");

    for (i = 0; i < HTsize; i++) {
        if (HT[i] != NULL) {
            printf("Hash Code %2d : ", i);
            currpt = HT[i];
            while (currpt != NULL) {
                currid = currpt->index;
                while (ST[currid] != '\0' && currid < STsize) {
                    printf("%c", ST[currid]);
                    currid++;
                }
                printf("   ");
                currpt = currpt->next;
            }
            printf("\n");
        }
    }
    printf("\n\n<%d characters are used in the string table>\n", nextfree - 1);
}

// 류서영 작성
/* PrintError - Print out error messages
                overst : overflow in ST
                print the hashtable and abort by calling the function "abort()*.
                illid : illegal identifier
                illsp : illegal seperator
                longid : too long identifier */
void PrintError(ERRORtypes err) {
    int i;
    char ill;
    if (err == overst) {
        printf("  ...Error...      OVERFLOW\n");
        PrintHStable();
        return;
    }
    if (err == illsp) {
        printf("  ...Error...      ");
        for (i = nextid; i < nextfree; i++)
            printf("%c", ST[i]);
        printf("%c", input);
        ill = input;
        input = fgetc(fp);
        while (input != EOF && (isLetter(input) || isDigit(input) || isUnderBar(input))) {
            printf("%c", input);
            input = fgetc(fp);
        }
        printf("\t\t   %c is not allowed\n", ill);
        nextfree = nextid;
        return;
    }
    if (err == illid) {
        printf("  ...Error...      ");
        while (input != EOF && (isLetter(input) || isDigit(input) || isUnderBar(input))) {
            printf("%c", input);
            input = fgetc(fp);
        }
        printf("\t\t   start with digit\n");
        return;
    }
    if (err == longid) {
        printf("  ...Error...      ");
        for (i = nextid; i < nextfree; i++)
            printf("%c", ST[i]);
        printf("\t   too long identifier\n");
        nextfree = nextid;
        return;
    }
}

/* SkipSeperators - skip over strings of spaces, tabs, newlines, . , ; : ? !
                    if illegal seperators, print out error message. */
void SkipSeperators() {
    while (input != EOF && !(isLetter(input) || isDigit(input) || isUnderBar(input))) {
        if (!isSeperator(input)) {
            err = illsp;
            PrintError(err);
        }
        input = fgetc(fp);
    }
}

// 류서영 작성
/* ReadID - Read identifier from the input file the string table ST directly into
            ST (append it to the previous identifier).
            An identifier is a string of letters and digits, starting with a letter.
            If first letter is digit, print out error message. */
void ReadID() {

    if (isDigit(input)) {
        err = illid;
        PrintError(err);
    }
    else {
        while (input != EOF && (isLetter(input) || isDigit(input) || isUnderBar(input))) {
            if (nextfree == STsize) {
                err = overst;
                PrintError(err);
            }
            ST[nextfree] = input;
            nextfree++;
            input = fgetc(fp);
        }
        if ((nextfree - nextid) > 12) {
            err = longid;
            PrintError(err);
            input = fgetc(fp);
        }
    }
}

// 이하경 작성
/* ComputeHS - Compute the hash code of identifier by summing the ordinal values of its
               characters and then taking the sum modulo the size of HT. */
void ComputeHS(int nid, int nfree) {
    int sum = 0;
    char a;
    for (int i = nid; i < nfree; i++) {
        a = ST[i];
        sum += a;
    }
    hashcode = (sum % HTsize) + 1;
}

// 이하경 작성
/* LookupHS - For each identifier, look it up in the hashtable for previous occurrence
              of the identifier. If find a match, set the found flag as true.
              Otherwise false.
              If find a match, save the starting index of ST in same id. */
void LookupHS(int nid, int hscode) {
    HTpointer cur = HT[hscode];
    int i, j;

    found = 0;
    while (cur != NULL && found == 0) {
        found = 1;
        i = cur->index;
        j = nid;
        sameid = i;
        while (ST[i] != '\0' && ST[j] != '\0' && found == 1) {
            if (ST[i] != ST[j])
                found = 0;
            else {
                i++;
                j++;
            }
        }
        cur = cur->next;
    }
}

// 이하경 작성
/* ADDHT -  Add a new identifier to the hash table.
            If list head ht[hashcode] is null, simply add a list element with
            starting index of the identifier in ST.
            If list head is not a null, it adds a new identifier to the head of the chain. */
void ADDHT(int hscode) {
    HTpointer new_node = (HTpointer)malloc(sizeof(HTpointer));
    new_node->index = nextid;
    new_node->next = HT[hscode];
    HT[hscode] = new_node;
}

// 류서영, 이하경 작성
/* MAIN - Read the identifier from the file directly into ST.
          Compute its hashcode.
          Look up the identifier in hashtable HT[hashcode]
          If matched, delete the identifier from ST and print ST-index
          of the matching identifier.
          If not matched, add a new element to the list, pointing to new identifier.
          Print the identifier, its index in ST, and whether it was entered or present.
          Print out the hashtable, and number of characters used up in ST. */
int main() {
    int i;
    PrintHeading();
    initialize();

    while (input != EOF) {
        err = noerror;
        nextid = nextfree;
        SkipSeperators();
        ReadID();
        if (err != illid && err != longid) {
            if (nextfree == STsize) {
                err = overst;
                PrintError(err);
            }
            if (isSeperator(input) == 1 || input==EOF ) {
                ST[nextfree] = '\0';
                nextfree++;
                ComputeHS(nextid, nextfree);
                LookupHS(nextid, hashcode);
                if ((nextfree - nextid) > 1) {
                    if (!found) {
                        printf("%6d             ", nextid);
                        for (i = nextid; i < nextfree - 1; i++)
                            printf("%c", ST[i]);
                        printf("\t\t   (entered)\n");
                        ADDHT(hashcode);
                    }
                    else {
                        printf("%6d             ", nextid);
                        for (i = nextid; i < nextfree - 1; i++)
                            printf("%c", ST[i]);
                        printf("\t\t   (already existed)\n");
                    }
                }
            }
            else
                SkipSeperators();
        }
    }
    PrintHStable();
}