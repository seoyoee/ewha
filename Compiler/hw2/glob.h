/*
glob.h - global variables for the project
programmer - 류서영, 이하경
date - 2022/04/25
*/

#define STsize 1000   // size of string table
#define HTsize 100    // size of hash table

#define FALSE 0
#define TRUE 1

#define isLetter(x) (((x) >= 'a' && (x) <= 'z') || ((x) >= 'A' && (x) <= 'Z'))
#define isDigit(x) ((x) >= '0' && (x) <= '9')
#define isUnderbar(x) ((x) == '_')

typedef struct HTentry* HTpointer;
typedef struct HTentry {
	int index;        // index of identifier in ST
	HTpointer next;   // pointer to next identifier
} HTentry;

// define error types
enum errorTypes { noerror, stover, illid, illchar, illnum };
typedef enum errorTypes ERRORtypes;

HTpointer HT[HTsize];
char ST[STsize];

extern int nextid;       // extern the current identifier 
extern int nextfree;     // extern the next available index of ST
extern int hashcode;     // extern hash code of identifier
extern int sameid;       // extern first index of identifier
extern int found;        // extern for the previous occurrence of an identifier
extern int idx1, idx2;   // extern index of identifier in hash table

extern ERRORtypes err;   // extern error types
extern enum tnumber tn;  // extern token types

extern int cLine;        // extern line number
extern int cErrors;      // extern the number of errors detected

extern char* yytext;
extern int yyleng;

void reporterror(ERRORtypes err);     // extern void reporterror(ERRORtypes err);