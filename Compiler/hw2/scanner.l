%{
/*
scanner.l - lexical analyzer for the Mini C
programmer - 류서영, 이하경
date - 2022/04/25
*/
#include <stdio.h>
#include "tn.h"
#include "glob.h"
%}
%%
"const"			return(TCONST);
"else"			return(TELSE);
"if"			return(TIF);
"int"			return(TINT);
"return"			return(TRETURN);
"void"			return(TVOID);
"while"			return(TWHILE);
"+"			return(TADD);
"-"			return(TSUB);
"*"			return(TMUL);
"/"			return(TDIV);
"%"			return(TMOD);
"="			return(TASSIGN);
"!"			return(TNOT);
"=="			return(TEQUAL);
"!="			return(TNOTEQU);
"<"			return(TLESS);
">"			return(TGREAT);
"<="			return(TLESSEQL);
">="			return(TGREATEQL);
"&&"			return(TAND);
"||"			return(TOR);
"++"			return(TINC);
"--"			return(TDEC);
"+="			return(TADDASSIGN);
"-="			return(TSUBASSIGN);
"*="			return(TMULASSIGN);
"/="			return(TDIVASSIGN);
"%="			return(TMODASSIGN);
"("			return(TOPENSBRAC);
")"			return(TCLOSESBRAC);
","			return(TCOMMA);
"{"			return(TOPENMBRAC);
"}"			return(TCLOSEMBRAC);
"["			return(TOPENLBRAC);
"]"			return(TCLOSELBRAC);
";"			return(TSEMICOL);
[1-9][0-9]*|0			return(TNUMBER);
0[0-7]+				return(TOCT);
0[x|X][0-9A-Fa-f]+			return(THEX);
[0-9]+"."[0-9]+(e[+-]?[0-9]+)?  return(TREALNUM);
[A-Za-z_][A-Za-z0-9_]*	{ SymbolTable(); return(TIDENT);}
[0-9]+[A-Za-z_][A-Za-z0-9_]*	{ SymbolTable(); return(TIDENT);}
[ \t]					;
[\n]		return(TNEWLINE);
"/*"([^*]|\*+[^*/])*\**"*/"			;
"//".*					;
.			{ SymbolTable(); }
%%
int yywrap(){
return 1;
}