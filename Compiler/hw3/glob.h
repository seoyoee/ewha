/*
glob.h - global variables for the project
programmer - 류서영, 이하경
date - 2022/06/06
*/

#pragma once
#include <stdio.h>
#include <stdlib.h>

#define STsize 1000		// size of string table
#define HTsize 100		// size of hash table

#define FALSE 0
#define TRUE 1

typedef struct HTentry* HTpointer;
typedef struct HTentry {
	int index;			// index of identifier in ST
	int type;			// type of identifier
	int linenum;		// line number of identifier
	HTpointer next;		// pointer to next identifier
} HTentry;

// define error types
enum ERRORtypes { overst, longid, illid, illsp, wrong_st, wrong_funcdef, nosemi, nobracket, nobrace, noround, nooperand, nocomma, nocondition };
typedef enum errorTypes ERRORtypes;
ERRORtypes err;			// error types

HTpointer HT[HTsize];
char ST[STsize];

HTpointer look_id;		// current id
HTpointer look_tmp;		// pointer

int nextid;				// the current identifier 
int nextfree;			// the next available index of ST
int hashcode;			// hash code of identifier
int sameid;				// first index of identifier
int found;				// for the previous occurrence of an identifier

int cLine;				// line number
int cErrors;			// the number of errors detected

extern char* yytext;
extern int yyleng;

void printError(ERRORtypes err);		// extern printerror
void ComputeHS(int nid, int nfree);		// extern ComputeHS
void LookupHS(int nid, int hscode);		// extern LookupHS
void ADDHT(int hscode);					// extern ADDHT
void PrintHStable();					// extern PrintHStable