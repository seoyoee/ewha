/*
parser.y - yacc source for MiniC
programmer - 류서영, 이하경
date - 2022/06/06
*/

#include <stdio.h>
#include <stdlib.h>
#include "tn.h"
#include "glob.h"

extern int cLine;
extern int cErrors;

/*
ComputeHS() - Hash Table의 hashcode를 계산하는 함수
*/
void ComputeHS(int nid, int nfree) {
	int i;
	int sum = 0;

	for (i = nid; i < nfree - 1; i++) {
		sum += (int)ST[i];
	}
	hashcode = (sum % HTsize);
}

/*
LookupHS() - Hash Table에 똑같은 identifier가
			 저장되어 있는지 찾는 함수
*/
void LookupHS(int nid, int hscode) {
	HTpointer here;
	int i, j, k;
	int htid = 0, stid = 0;
	found = FALSE;

	if (HT[hscode] != NULL) {
		here = HT[hscode];
		while (here != NULL && found == FALSE) {
			found = TRUE;
			i = here->index;
			j = nid;
			sameid = i;
			for (k = i; ST[k] != '\0'; k++) htid++;
			for (k = j; ST[k] != '\0'; k++) stid++;
			if (htid != stid) found = FALSE;
			while (ST[i] != '\0' && ST[j] != '\0' && found == TRUE) {
				if (ST[i] != ST[j])
					found = FALSE;
				else {
					i++;
					j++;
				}
			}
			if (found == TRUE)
				return;
			here = here->next;
		}
	}
}

/*
ADDHT() - Hash Table에 identifier가 저장된
		  String Table의 index 값을 저장하는 함수
*/
void ADDHT(int hscode) {
	HTpointer tmp = (HTpointer)malloc(sizeof(HT));
	if (HT[hscode] == NULL) {
		tmp->type = 0;
		tmp->index = nextid;
		tmp->linenum = cLine;
		tmp->next = NULL;
		HT[hscode] = tmp;
	}
	else {
		tmp->type = 0;
		tmp->index = nextid;
		tmp->linenum = cLine;
		tmp->next = HT[hscode];
		HT[hscode] = tmp;
	}
	look_id = tmp;
}

void PrintHStable() {
	HTpointer here;
	int i, j;
	printf("\n\t[Hash Table : Identifier Information]\n\n");
	for (i = 0; i < HTsize; i++) {
		if (HT[i] != NULL) {
			here = HT[i];
			do {
				printf("\tHash Code %4d : (", i);
				for (j = here->index; ST[j] != '\0'; j++)
					printf("%c", ST[j]);
				printf(" : ");
				switch (here->type) {
				case 1:
					printf("integer scalar variable, line: %d)\n", here->linenum);
					break;
				case 2:
					printf("void scalar variable, line: %d)\n", here->linenum);
					break;
				case 3:
					printf("float scalar variable, line: %d)\n", here->linenum);
					break;
				case 4:
					printf("char scalar variable, line: %d)\n", here->linenum);
					break;
				case 5:
					printf("function name, return type = int, line: %d)\n", here->linenum);
					break;
				case 6:
					printf("function name, return type = void, line: %d)\n", here->linenum);
					break;
				case 7:
					printf("function name, return type = float, line: %d)\n", here->linenum);
					break;
				case 8:
					printf("function name, return type = char, line: %d)\n", here->linenum);
					break;
				case 9:
					printf("integer array variable, line: %d)\n", here->linenum);
					break;
				case 10:
					printf("float array variable, line: %d)\n", here->linenum);
					break;
				case 11:
					printf("char array variable, line: %d)\n", here->linenum);
					break;
				case 12:
					printf("function parameter, line: %d)\n", here->linenum);
					break;
				case 13:
					printf("not defined identifier or function, line: %d)\n", here->linenum);
					break;
				}
				here = here->next;
			} while (here != NULL);
		}
	}
	printf("\n\t<  %d characters are used in the string table  > \n\n", nextfree - 1);
}