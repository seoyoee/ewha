%{
/*
scanner.l 
programmer - 류서영, 이하경
date - 2022/06/06
*/
#include <stdio.h>
#include <stdlib.h>
#include "tn.h"
#include "glob.h"

extern int cLine;
extern int cErrors;

int i;
%}

%s CMT

%%

"const"			return(TCONST);
"else"			return(TELSE);
"if"			return(TIF);
"int"			return(TINT);
"float"			return(TFLOAT);
"char"			return(TCHAR);
"return"			return(TRETURN);
"void"			return(TVOID);
"while"			return(TWHILE);

"("			return(TLPAREN);
")"			return(TRPAREN);
"{"			return(TLBRACE);
"}"			return(TRBRACE);
"["			return(TLBRACKET);
"]"			return(TRBRACKET);
","			return(TCOMMA);
";"			return(TSEMICOLON);

"+"			return(TPLUS);
"-"			return(TMINUS);
"*"			return(TMULTIPLY);
"/"			return(TDIVIDE);
"%"			return(TMOD);

"="			return(TASSIGN);
"=="			return(TEQUAL);
"!="			return(TNOTEQU);
"<"			return(TLESS);
">"			return(TGREAT);
"<="			return(TLESSE);
">="			return(TGREATE);

"!"			return(TNOT);
"&&"			return(TAND);
"||"			return(TOR);
"++"			return(TINC);
"--"			return(TDEC);
"+="			return(TADDASSIGN);
"-="			return(TSUBASSIGN);
"*="			return(TMULASSIGN);
"/="			return(TDIVASSIGN);
"%="			return(TMODASSIGN);

0[0-7]+ 						|
[+-]?[0-9]+						|
0(x|X)[0-9A-Fa-f]+				|
[0-9]+"."[0-9]+(e[+-]?[0-9]+)?	return(TNUMBER);

[A-Za-z_][A-Za-z0-9_]*	{ 
	if (nextfree >= STsize) {
		printError(overst);
		exit(1);
	}
	nextid = nextfree;
	nextfree += (yyleng+1);
	for (i=0;i<yyleng;i++)
		{ST[nextid+i] = yytext[i];}
	ComputeHS(nextid, nextfree);
	LookupHS(nextid, hashcode);
	if (!found) {
		ADDHT(hashcode);}
	else{
		for(i=nextid;i<nextfree-1;i++){
			ST[i] = '\0';}
		nextfree = nextid;}
	return(TIDENT);
}
	
[0-9]+[A-Za-z_][A-Za-z0-9_]*	{printError(illid);}
"/*"([^*]|\*+[^*/])*\**"*/"			{ 
					for (i=0;i<yyleng;i++)
						if (yytext[i] == '\n') cLine++;}
\"(\\.|[^\"])*\"	{return TSTRING;}
"//".*				;
[ \t]				;
"\n"				cLine++;
.					{printError(illsp);}
%%
int yywrap()
{
	return 1;
}