/*
reporterror.c - error reporting
programmer - 류서영, 이하경
date - 2022/06/06
*/

#include <stdio.h>
#include <stdlib.h>
#include "tn.h"
#include "glob.h"

extern int cLine;
extern int cErrors;

// yyerror() - error 함수
yyerror(s)
char* s;
{

}

/*
printError() - error type 별로 출력하는 함수
*/
void printError(ERRORtypes err) {

	switch (err) {
	case overst:
		printf("\t%d\t**Error**\tOver flow\n", cLine);
		cErrors++;
		exit(0);
		break;
	case longid:
		printf("\t%d\t**Error**\tToo Long Identifier\n", cLine);
		cErrors++;
		break;
	case illid:
		printf("\t%d\t**Error**\tIllegal Identifier\n", cLine);
		cErrors++;
		break;
	case illsp:
		printf("\t%d\t**Error**\tIllegal Character\n", cLine);
		cErrors++;
		break;
	case wrong_st:
		printf("\t%d\t**Error**\tWrong Statement\n", cLine);
		cErrors++;
		break;
	case wrong_funcdef:
		printf("\t%d\t**Error**\tWrong Function Definition\n", cLine);
		cErrors++;
		break;
	case nosemi:
		printf("\t%d\t**Error**\tNo Semicolon\n", cLine-1);
		cErrors++;
		break;
	case nobracket:	// 대괄호
		printf("\t%d\t**Error**\tNo Bracket\n", cLine);
		cErrors++;
		break;
	case nobrace:		// 중괄호
		printf("\t%d\t**Error**\tNo Brace\n", cLine);
		cErrors++;
		break;
	case noround:		// 소괄호
		printf("\t%d\t**Error**\tNo Round Bracket\n", cLine);
		cErrors++;
		break;
	case nocomma:
		printf("\t%d\t**Error**\tNo Comma\n", cLine);
		cErrors++;
		break;
	case nocondition:
		printf("\t%d\t**Error**\tNo Condition\n", cLine);
		cErrors++;
		break;
	}
}