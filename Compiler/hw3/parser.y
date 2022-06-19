%{

/*
parser.y - yacc source for MiniC
programmer - 류서영, 이하경
date - 2022/06/06
*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <malloc.h>

#include "glob.h"

int type_int = 0;
int type_void = 0;
int type_float = 0;
int type_char = 0;

extern int cLine;
extern int cErrors;

void line(int);
extern yylex();
extern yyerror(char* s);

%}

%token TEOF TIDENT TNUMBER TCONST TELSE TIF TINT TFLOAT TCHAR TSTRING TRETURN TVOID TWHILE
%token TADDASSIGN TSUBASSIGN TMULASSIGN TDIVASSIGN TMODASSIGN
%token TOR TAND TEQUAL TNOTEQU TGREAT TLESS TGREATE TLESSE TINC TDEC
%token TPLUS TMINUS TMULTIPLY TDIVIDE TMOD TNOT TASSIGN TLPAREN TRPAREN TCOMMA TSEMICOLON
%token TLBRACKET TRBRACKET TLBRACE TRBRACE
%nonassoc LOWER_THAN_ELSE
%nonassoc TELSE

%%

mini_c              : translation_unit
                    ;

translation_unit    : external_dcl
                    | translation_unit external_dcl
                    ;

external_dcl        : function_def
                    | declaration
                    | TIDENT TSEMICOLON
                    | TIDENT error
                    {
                        yyerrok;
                        printError(wrong_st);       /* error - wrong statement */
                    }
                    ;

function_def        : function_header compound_st
                    | function_header TSEMICOLON
                    | function_header error
                    {
                        yyerrok;
                        printError(wrong_funcdef);  /* error - wrong function definition */
                    }
                    ;

function_header     : dcl_spec function_name formal_param
                    ;

dcl_spec            : dcl_specifiers
                    ;

dcl_specifiers      : dcl_specifier
                    | dcl_specifiers dcl_specifier
                    ;

dcl_specifier       : type_qualifier
                    | type_specifier
                    ;

type_qualifier      : TCONST
                    ;

type_specifier      : TINT  
                    {
                        type_int = 1;      /* type : integer */
                        type_void = 0;
                        type_float = 0;
                        type_char = 0;
                    }

                    | TVOID
                    {
                        type_int = 0;
                        type_void = 1;      /* type : void */
                        type_float = 0;
                        type_char = 0;
                    }

                    | TFLOAT
                    {
                        type_int = 0;
                        type_void = 0;
                        type_float = 1;     /* type : float */
                        type_char = 0;
                    }

                    | TCHAR
                    {
                        type_int = 0;
                        type_void = 0;
                        type_float = 0;
                        type_char = 1;      /* type : char */
                    }
                    ;

function_name       : TIDENT
                    {
                        if (look_id->type == 0 || look_id->type == 13)
                        {
                            if (type_int == 1) look_id->type = 5;   /* return int function name */
                            if (type_void == 1) look_id->type = 6;  /* return int function name */
                            if (type_float == 1) look_id->type = 7; /* return int function name */
                            if (type_char == 1) look_id->type = 8;  /* return int function name */
                            type_int = 0;
                            type_void = 0;
                            type_float = 0;
                            type_char = 0;
                            look_tmp = look_id;
                        }
                    }

                    |
                    {
                        yyerrok;
                        printError(wrong_funcdef);  /* error - wrong function definition */
                    }
                    ;

formal_param        : TLPAREN opt_formal_param TRPAREN
                    ;

opt_formal_param    : formal_param_list
                    |
                    ;

formal_param_list   : param_dcl
                    | formal_param_list TCOMMA param_dcl
                    | formal_param_list param_dcl
                    {
                        yyerrok;
                        printError(nocomma);    /* error - no comma */
                    }
                    ;

param_dcl           : dcl_spec declarator
                    {
                        if (look_id->type == 1)
                        {
                            type_int = 0;
                            type_void = 0;
                            type_float = 0;
                            type_char = 0;
                            look_id->type = 12;  /* function parameter */
                        }
                    }
                    ;

compound_st         : TLBRACE compound TRBRACE
                    | TLBRACE compound error
                    {
                        yyerrok;
                        printError(nobrace);    /* error - no brace */
                    }
                    ;

compound            : opt_dcl_list opt_stat_list
                    ;

opt_dcl_list        : declaration_list
                    |
                    ;

declaration_list    : declaration
                    | declaration_list declaration
                    ;

declaration         : dcl_spec init_dcl_list TSEMICOLON
                    {
                        type_int = 0;
                        type_void = 0;
                        type_float = 0;
                        type_char = 0;
                    }

                    | dcl_spec init_dcl_list error
                    {
                        yyerrok;
                        type_int = 0;
                        type_void = 0;
                        type_float = 0;
                        type_char = 0;
                        printError(nosemi);     /* error - no semicolon */
                    }
                    ;

init_dcl_list       : init_declarator
                    | init_dcl_list TCOMMA init_declarator
                    | init_dcl_list init_declarator error
                    {
                        yyerrok;
                        printError(nocomma);    /* error - no comma */
                    }
                    ;

init_declarator     : declarator
                    | declarator TASSIGN TNUMBER
                    | declarator TASSIGN TSTRING
                    ;

declarator          : TIDENT
                    {
                        if (look_id->type == 0)
                        {
                            if (type_int == 1)
                                look_id->type = 1;      /* integer scalar variable */
                            if (type_void == 1)
                                look_id->type = 2;      /* void scalar variable */
                            if (type_float == 1)
                                look_id->type = 3;      /* float scalar variable */
                            if (type_char == 1)
                                look_id->type = 4;      /* char scalar variable */
                        }
                        look_tmp = look_id
                    }

                    | TIDENT TLBRACKET opt_number TRBRACKET
                    {
                        if (look_id->type == 0)
                        {
                            if (type_int == 1)
                                look_id->type = 9;      /* integer array variable */
                            else if (type_float == 1)
                                look_id->type = 10;     /* float array variable */
                            else if (type_char == 1)
                                look_id->type = 11;     /* char array variable */
                        }
                        look_tmp = look_id;
                    }

                    | TIDENT TLBRACKET opt_number error
                    {
                        yyerrok;
                        printError(nobracket);      /* error - no bracket */
                    }
                    ;

opt_number          : TNUMBER
                    |
                    ;

opt_stat_list       : statement_list
                    |
                    ;

statement_list      : statement
                    | statement_list statement
                    | statement_list declaration
                    ;

statement           : compound_st
                    | expression_st
                    | if_st
                    | while_st
                    | return_st
                    ;

expression_st       : opt_expression TSEMICOLON
                    | expression error
                    {
                        yyerrok;
                        printError(nosemi);     /* error - no semicolon */
                    }
                    ;

opt_expression      : expression
                    |
                    ;

if_st               : TIF TLPAREN expression TRPAREN statement %prec LOWER_THAN_ELSE
                    | TIF TLPAREN expression TRPAREN statement TELSE statement
                    | TIF TLPAREN TRPAREN
                    {
                        yyerrok;
                        printError(nocondition);    /* error - no condition */
                    }
                    ;

while_st            : TWHILE TLPAREN expression TRPAREN statement
                    | TWHILE TLPAREN TRPAREN
                    {
                        yyerrok;
                        printError(nocondition);    /* error - no condition */
                    }
                    ;

return_st           : TRETURN opt_expression TSEMICOLON
                    ;

expression          : assignment_exp
                    ;

assignment_exp      : logical_or_exp
                    | unary_exp TASSIGN assignment_exp
                    | unary_exp TADDASSIGN assignment_exp
                    | unary_exp TSUBASSIGN assignment_exp
                    | unary_exp TMULASSIGN assignment_exp
                    | unary_exp TDIVASSIGN assignment_exp
                    | unary_exp TMODASSIGN assignment_exp
                    ;

logical_or_exp      : logical_and_exp
                    | logical_or_exp TOR logical_and_exp
                    ;

logical_and_exp     : equality_exp
                    | logical_and_exp TAND equality_exp
                    ;

equality_exp        : relational_exp
                    | equality_exp TEQUAL relational_exp
                    | equality_exp TNOTEQU relational_exp
                    ;

relational_exp      : additive_exp
                    | relational_exp TGREAT additive_exp
                    | relational_exp TLESS additive_exp
                    | relational_exp TGREATE additive_exp
                    | relational_exp TLESSE additive_exp
                    ;

additive_exp        : multiplicative_exp
                    | additive_exp TPLUS multiplicative_exp
                    | additive_exp TMINUS multiplicative_exp
                    ;

multiplicative_exp  : unary_exp
                    | multiplicative_exp TMULTIPLY unary_exp
                    | multiplicative_exp TDIVIDE unary_exp
                    | multiplicative_exp TMOD unary_exp
                    ;

unary_exp           : postfix_exp
                    | TMINUS unary_exp
                    | TNOT unary_exp
                    | TINC unary_exp
                    | TDEC unary_exp
                    ;

postfix_exp         : primary_exp
                    | postfix_exp TLBRACKET expression TRBRACKET
                    | postfix_exp TLPAREN opt_actual_param TRPAREN
                    | postfix_exp TINC
                    | postfix_exp TDEC
                    ;

opt_actual_param    : actual_param
                    |
                    ;

actual_param        : actual_param_list
                    ;

actual_param_list   : assignment_exp
                    | actual_param_list TCOMMA assignment_exp
                    ;

primary_exp         : TIDENT
                    {
                        if (look_id->type == 0)
                            look_id->type = 13;        /* not defined identifier or function */
                    }
                    | TNUMBER
                    | TSTRING
                    | TLPAREN expression TRPAREN
                    ;

%%

void line(int n) {
    printf("\t %d \t", n);
}