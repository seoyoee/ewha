/*
symtable.c - symbol table
programmer - 류서영, 이하경
date - 2022/04/25
*/

#include <stdio.h>
#include <stdlib.h>
#include "tn.h"
#include "glob.h"

int nextid = 0;          // the current identifier 
int nextfree = 0;        // the next available index of ST
int hashcode;            // hash code of identifier
int sameid;              // first index of identifier
int found;               // for the previous occurrence of an identifier
int idx1, idx2;          // index of identifier in hash table

ERRORtypes err;          // error types

/*
ComputeHS() - Hash Table의 hashcode를 계산하는 함수
*/
void ComputeHS(int nid, int nfree) {
	int sum = 0;
	char a;
	for (int i = nid; i < nfree; i++) {
		a = ST[i];
		sum += a;
	}
	hashcode = (sum % HTsize) + 1;
}

/*
LookupHS() - Hash Table에 똑같은 identifier가
             저장되어 있는지 찾는 함수
*/
void LookupHS(int nid, int hscode) {
	//hashcode에 해당하는 HT의 리스트를 탐색하여 해당 identifier가 이미 HT에 있는지 없는지 여부를 판단
	//판단 결과는 found 변수에 저장
	HTentry* node = HT[hscode]; // hascode에 해당하는 HT의 리스트의 첫번째 노드
	int len1 = 0;
	int len2 = 0;
	char ch1, ch2;
	int same;

	found = 0;

	while (node != NULL) {
		idx1 = node->index; // 현재 HT[hscode]에 존재하는 identifier의 첫 문자의 ST 인덱스 
		idx2 = nid; // HT에 추가하고자 하는 identifier의 첫 문자의 ST 인덱스
		ch1 = ST[idx1]; // 현재 HT[hscode]에 존재하는 identifier의 첫 문자
		ch2 = ST[idx2]; // HT에 추가하고자 하는 identifier의 첫 문자
		same = 0; // same이 0이면 ch1과 ch2가 같지 않음 의미, 1이면 같음 의미

		while (ch1 != '\0' && ch2 != '\0') {
			if (ch1 == ch2) { // 두 문자가 같은 경우
				same = 1;
				ch1 = ST[++idx1]; // 다음 문자
				ch2 = ST[++idx2]; // 다음 문자
				if (ch1 != '\0') { len1++; }
				if (ch2 != '\0') { len2++; }
			}
			else { // 두 문자가 같지 않은 경우
				same = 0;
				break;
			}
		}

		if (same == 1 && (len1==len2)) { // identifier가 이미 HT에 있는 경우
			idx1 = node->index; // 이미 존재하는 identifier의 ST index를 전달
			found = 1;
			break;
		}
		node = node->next; // 다음 노드
	}
}

/*
ADDHT() - Hash Table에 identifier가 저장된
          String Table의 index 값을 저장하는 함수
*/
void ADDHT(int hscode) {
	HTpointer new_node = (HTpointer)malloc(sizeof(HTpointer));
	new_node->index = nextid;
	new_node->next = HT[hscode];
	HT[hscode] = new_node;
}

/*
SymbolTable() - token에 error가 있는지 검사하고 error가 없으면
			    ComputeHS, LookupHS, ADDHT 함수를 실행하는 함수
*/
void SymbolTable() {
	int i;
	err = noerror;

	if (nextfree == STsize) {
		err = stover;
		reporterror(err);
	}

	if (yyleng > 12) {
		err = illid;
		reporterror(err);
		return 1;
	}

	if (isDigit(yytext[0])) {
		err = illid;
		reporterror(err);
		return 1;
	}
	nextid = nextfree;

	for (i = 0; yytext[i] != '\0'; i++) {
		if (nextfree == STsize) {
			err = stover;
			reporterror(err);
			return 1;
		}

		if (!isLetter(yytext[i]) && !isDigit(yytext[i]) && !isUnderbar(yytext[i])) {
			err = illchar;
			reporterror(err);
			return 1;
		}

		if (err == noerror) {

			ST[nextfree] = yytext[i];
			nextfree++;
		}
	}

	ST[nextfree++] = '\0';
	ComputeHS(nextid, nextfree);
	LookupHS(nextid, hashcode);

	if (!found)
				ADDHT(hashcode);
	else {
		for (int i = nextfree - 1; i >= nextid; i--) {
			ST[i] = '\0';
		}
		nextfree = nextid;
	}
}
	

