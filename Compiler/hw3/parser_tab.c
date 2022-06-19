
/*  A Bison parser, made from parser.y with Bison version GNU Bison version 1.24
  */

#define YYBISON 1  /* Identify Bison output.  */

#define	TEOF	258
#define	TIDENT	259
#define	TNUMBER	260
#define	TCONST	261
#define	TELSE	262
#define	TIF	263
#define	TINT	264
#define	TFLOAT	265
#define	TCHAR	266
#define	TSTRING	267
#define	TRETURN	268
#define	TVOID	269
#define	TWHILE	270
#define	TADDASSIGN	271
#define	TSUBASSIGN	272
#define	TMULASSIGN	273
#define	TDIVASSIGN	274
#define	TMODASSIGN	275
#define	TOR	276
#define	TAND	277
#define	TEQUAL	278
#define	TNOTEQU	279
#define	TGREAT	280
#define	TLESS	281
#define	TGREATE	282
#define	TLESSE	283
#define	TINC	284
#define	TDEC	285
#define	TPLUS	286
#define	TMINUS	287
#define	TMULTIPLY	288
#define	TDIVIDE	289
#define	TMOD	290
#define	TNOT	291
#define	TASSIGN	292
#define	TLPAREN	293
#define	TRPAREN	294
#define	TCOMMA	295
#define	TSEMICOLON	296
#define	TLBRACKET	297
#define	TRBRACKET	298
#define	TLBRACE	299
#define	TRBRACE	300
#define	LOWER_THAN_ELSE	301

#line 1 "parser.y"


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


#ifndef YYLTYPE
typedef
  struct yyltype
    {
      int timestamp;
      int first_line;
      int first_column;
      int last_line;
      int last_column;
      char *text;
   }
  yyltype;

#define YYLTYPE yyltype
#endif

#ifndef YYSTYPE
#define YYSTYPE int
#endif
#include <stdio.h>

#ifndef __cplusplus
#ifndef __STDC__
#define const
#endif
#endif



#define	YYFINAL		174
#define	YYFLAG		-32768
#define	YYNTBASE	47

#define YYTRANSLATE(x) ((unsigned)(x) <= 301 ? yytranslate[x] : 93)

static const char yytranslate[] = {     0,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
     2,     2,     2,     2,     2,     1,     2,     3,     4,     5,
     6,     7,     8,     9,    10,    11,    12,    13,    14,    15,
    16,    17,    18,    19,    20,    21,    22,    23,    24,    25,
    26,    27,    28,    29,    30,    31,    32,    33,    34,    35,
    36,    37,    38,    39,    40,    41,    42,    43,    44,    45,
    46
};

#if YYDEBUG != 0
static const short yyprhs[] = {     0,
     0,     2,     4,     7,     9,    11,    14,    17,    20,    23,
    26,    30,    32,    34,    37,    39,    41,    43,    45,    47,
    49,    51,    53,    54,    58,    60,    61,    63,    67,    70,
    73,    77,    81,    84,    86,    87,    89,    92,    96,   100,
   102,   106,   110,   112,   116,   120,   122,   127,   132,   134,
   135,   137,   138,   140,   143,   146,   148,   150,   152,   154,
   156,   159,   162,   164,   165,   171,   179,   183,   189,   193,
   197,   199,   201,   205,   209,   213,   217,   221,   225,   227,
   231,   233,   237,   239,   243,   247,   249,   253,   257,   261,
   265,   267,   271,   275,   277,   281,   285,   289,   291,   294,
   297,   300,   303,   305,   310,   315,   318,   321,   323,   324,
   326,   328,   332,   334,   336,   338
};

static const short yyrhs[] = {    48,
     0,    49,     0,    48,    49,     0,    50,     0,    66,     0,
     4,    41,     0,     4,     1,     0,    51,    62,     0,    51,
    41,     0,    51,     1,     0,    52,    57,    58,     0,    53,
     0,    54,     0,    53,    54,     0,    55,     0,    56,     0,
     6,     0,     9,     0,    14,     0,    10,     0,    11,     0,
     4,     0,     0,    38,    59,    39,     0,    60,     0,     0,
    61,     0,    60,    40,    61,     0,    60,    61,     0,    52,
    69,     0,    44,    63,    45,     0,    44,    63,     1,     0,
    64,    71,     0,    65,     0,     0,    66,     0,    65,    66,
     0,    52,    67,    41,     0,    52,    67,     1,     0,    68,
     0,    67,    40,    68,     0,    67,    68,     1,     0,    69,
     0,    69,    37,     5,     0,    69,    37,    12,     0,     4,
     0,     4,    42,    70,    43,     0,     4,    42,    70,     1,
     0,     5,     0,     0,    72,     0,     0,    73,     0,    72,
    73,     0,    72,    66,     0,    62,     0,    74,     0,    76,
     0,    77,     0,    78,     0,    75,    41,     0,    79,     1,
     0,    79,     0,     0,     8,    38,    79,    39,    73,     0,
     8,    38,    79,    39,    73,     7,    73,     0,     8,    38,
    39,     0,    15,    38,    79,    39,    73,     0,    15,    38,
    39,     0,    13,    75,    41,     0,    80,     0,    81,     0,
    87,    37,    80,     0,    87,    16,    80,     0,    87,    17,
    80,     0,    87,    18,    80,     0,    87,    19,    80,     0,
    87,    20,    80,     0,    82,     0,    81,    21,    82,     0,
    83,     0,    82,    22,    83,     0,    84,     0,    83,    23,
    84,     0,    83,    24,    84,     0,    85,     0,    84,    25,
    85,     0,    84,    26,    85,     0,    84,    27,    85,     0,
    84,    28,    85,     0,    86,     0,    85,    31,    86,     0,
    85,    32,    86,     0,    87,     0,    86,    33,    87,     0,
    86,    34,    87,     0,    86,    35,    87,     0,    88,     0,
    32,    87,     0,    36,    87,     0,    29,    87,     0,    30,
    87,     0,    92,     0,    88,    42,    79,    43,     0,    88,
    38,    89,    39,     0,    88,    29,     0,    88,    30,     0,
    90,     0,     0,    91,     0,    80,     0,    91,    40,    80,
     0,     4,     0,     5,     0,    12,     0,    38,    79,    39,
     0
};

#endif

#if YYDEBUG != 0
static const short yyrline[] = { 0,
    40,    43,    44,    47,    48,    49,    50,    57,    58,    59,
    66,    69,    72,    73,    76,    77,    80,    83,    91,    99,
   107,   116,   132,   139,   142,   143,   146,   147,   148,   155,
   168,   169,   176,   179,   180,   183,   184,   187,   195,   206,
   207,   208,   215,   216,   217,   220,   236,   250,   257,   258,
   261,   262,   265,   266,   267,   270,   271,   272,   273,   274,
   277,   278,   285,   286,   289,   290,   291,   298,   299,   306,
   309,   312,   313,   314,   315,   316,   317,   318,   321,   322,
   325,   326,   329,   330,   331,   334,   335,   336,   337,   338,
   341,   342,   343,   346,   347,   348,   349,   352,   353,   354,
   355,   356,   359,   360,   361,   362,   363,   366,   367,   370,
   373,   374,   377,   382,   383,   384
};

static const char * const yytname[] = {   "$","error","$undefined.","TEOF","TIDENT",
"TNUMBER","TCONST","TELSE","TIF","TINT","TFLOAT","TCHAR","TSTRING","TRETURN",
"TVOID","TWHILE","TADDASSIGN","TSUBASSIGN","TMULASSIGN","TDIVASSIGN","TMODASSIGN",
"TOR","TAND","TEQUAL","TNOTEQU","TGREAT","TLESS","TGREATE","TLESSE","TINC","TDEC",
"TPLUS","TMINUS","TMULTIPLY","TDIVIDE","TMOD","TNOT","TASSIGN","TLPAREN","TRPAREN",
"TCOMMA","TSEMICOLON","TLBRACKET","TRBRACKET","TLBRACE","TRBRACE","LOWER_THAN_ELSE",
"mini_c","translation_unit","external_dcl","function_def","function_header",
"dcl_spec","dcl_specifiers","dcl_specifier","type_qualifier","type_specifier",
"function_name","formal_param","opt_formal_param","formal_param_list","param_dcl",
"compound_st","compound","opt_dcl_list","declaration_list","declaration","init_dcl_list",
"init_declarator","declarator","opt_number","opt_stat_list","statement_list",
"statement","expression_st","opt_expression","if_st","while_st","return_st",
"expression","assignment_exp","logical_or_exp","logical_and_exp","equality_exp",
"relational_exp","additive_exp","multiplicative_exp","unary_exp","postfix_exp",
"opt_actual_param","actual_param","actual_param_list","primary_exp",""
};
#endif

static const short yyr1[] = {     0,
    47,    48,    48,    49,    49,    49,    49,    50,    50,    50,
    51,    52,    53,    53,    54,    54,    55,    56,    56,    56,
    56,    57,    57,    58,    59,    59,    60,    60,    60,    61,
    62,    62,    63,    64,    64,    65,    65,    66,    66,    67,
    67,    67,    68,    68,    68,    69,    69,    69,    70,    70,
    71,    71,    72,    72,    72,    73,    73,    73,    73,    73,
    74,    74,    75,    75,    76,    76,    76,    77,    77,    78,
    79,    80,    80,    80,    80,    80,    80,    80,    81,    81,
    82,    82,    83,    83,    83,    84,    84,    84,    84,    84,
    85,    85,    85,    86,    86,    86,    86,    87,    87,    87,
    87,    87,    88,    88,    88,    88,    88,    89,    89,    90,
    91,    91,    92,    92,    92,    92
};

static const short yyr2[] = {     0,
     1,     1,     2,     1,     1,     2,     2,     2,     2,     2,
     3,     1,     1,     2,     1,     1,     1,     1,     1,     1,
     1,     1,     0,     3,     1,     0,     1,     3,     2,     2,
     3,     3,     2,     1,     0,     1,     2,     3,     3,     1,
     3,     3,     1,     3,     3,     1,     4,     4,     1,     0,
     1,     0,     1,     2,     2,     1,     1,     1,     1,     1,
     2,     2,     1,     0,     5,     7,     3,     5,     3,     3,
     1,     1,     3,     3,     3,     3,     3,     3,     1,     3,
     1,     3,     1,     3,     3,     1,     3,     3,     3,     3,
     1,     3,     3,     1,     3,     3,     3,     1,     2,     2,
     2,     2,     1,     4,     4,     2,     2,     1,     0,     1,
     1,     3,     1,     1,     1,     3
};

static const short yydefact[] = {     0,
     0,    17,    18,    20,    21,    19,     1,     2,     4,     0,
    23,    12,    13,    15,    16,     5,     7,     6,     3,    10,
     9,    35,     8,    46,     0,     0,    40,    43,    14,     0,
     0,    52,    34,    36,    50,    26,    11,    39,    46,     0,
    38,     0,     0,    32,    31,   113,   114,     0,   115,    64,
     0,     0,     0,     0,     0,     0,    56,    33,    51,    53,
    57,     0,    58,    59,    60,     0,    71,    72,    79,    81,
    83,    86,    91,    94,    98,   103,    37,    49,     0,     0,
     0,    25,    27,    41,    42,    44,    45,     0,     0,    63,
     0,   101,   102,    99,   100,     0,    55,    54,    61,    62,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,   106,
   107,   109,     0,    48,    47,    30,    24,     0,    29,    67,
     0,    70,    69,     0,   116,    80,    94,    82,    84,    85,
    87,    88,    89,    90,    92,    93,    95,    96,    97,    74,
    75,    76,    77,    78,    73,   111,     0,   108,   110,     0,
    28,    64,    64,   105,     0,   104,    65,    68,   112,    64,
    66,     0,     0,     0
};

static const short yydefgoto[] = {   172,
     7,     8,     9,    10,    30,    12,    13,    14,    15,    25,
    37,    81,    82,    83,    57,    31,    32,    33,    16,    26,
    27,    28,    79,    58,    59,    60,    61,    62,    63,    64,
    65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
    75,   157,   158,   159,    76
};

static const short yypact[] = {    92,
    28,-32768,-32768,-32768,-32768,-32768,    92,-32768,-32768,    27,
    17,   166,-32768,-32768,-32768,-32768,-32768,-32768,-32768,-32768,
-32768,   166,-32768,    -4,   -26,     9,-32768,     2,-32768,    33,
    22,   114,   166,-32768,    37,   166,-32768,-32768,    32,    33,
-32768,    43,    19,-32768,-32768,-32768,-32768,    48,-32768,   178,
    57,   178,   178,   178,   178,   178,-32768,-32768,    79,-32768,
-32768,    56,-32768,-32768,-32768,    29,-32768,    89,    91,    24,
   192,    -5,   103,   186,     3,-32768,-32768,-32768,     8,    33,
    75,    67,-32768,-32768,-32768,-32768,-32768,   149,    80,-32768,
   162,-32768,-32768,-32768,-32768,    77,-32768,-32768,-32768,-32768,
   178,   178,   178,   178,   178,   178,   178,   178,   178,   178,
   178,   178,   178,   178,   178,   178,   178,   178,   178,-32768,
-32768,   178,   178,-32768,-32768,-32768,-32768,   166,-32768,-32768,
    85,-32768,-32768,    86,-32768,    91,-32768,    24,   192,   192,
    -5,    -5,    -5,    -5,   103,   103,-32768,-32768,-32768,-32768,
-32768,-32768,-32768,-32768,-32768,-32768,    94,-32768,    90,    98,
-32768,   127,   127,-32768,   178,-32768,   138,-32768,-32768,   127,
-32768,   134,   147,-32768
};

static const short yypgoto[] = {-32768,
-32768,   141,-32768,-32768,     0,-32768,   137,-32768,-32768,-32768,
-32768,-32768,-32768,   -62,   150,-32768,-32768,-32768,    13,-32768,
   -15,    71,-32768,-32768,-32768,   -58,-32768,   112,-32768,-32768,
-32768,   -48,  -100,-32768,    63,    66,   -24,   119,   -10,   -49,
-32768,-32768,-32768,-32768,-32768
};


#define	YYLAST		227


static const short yytable[] = {    11,
    98,    90,    92,    93,    94,    95,    11,    96,   124,    38,
    42,    36,    39,   150,   151,   152,   153,   154,   155,   129,
    24,   156,    44,    86,    84,   109,   110,    20,    17,   100,
    87,   120,   121,   -22,    34,    80,    39,    35,    43,   131,
   122,    78,   134,    85,   123,    77,   103,   104,    40,    41,
   125,   137,   137,   137,   137,   137,   137,   137,   137,   137,
   137,   147,   148,   149,   169,   161,    45,    21,    18,   -63,
    22,    97,     2,    35,   160,     3,     4,     5,   139,   140,
     6,    80,    46,    47,     2,    88,    48,     3,     4,     5,
    49,    50,     6,    51,    91,     1,    99,     2,   145,   146,
     3,     4,     5,   167,   168,     6,   128,    52,    53,   101,
    54,   171,   102,   127,    55,   135,    56,    46,    47,   -64,
   132,    48,    22,   162,   163,    49,    50,    80,    51,   165,
    46,    47,   164,   173,    48,   111,   112,   113,    49,    50,
   166,    51,    52,    53,   170,    54,   174,    19,    29,    55,
   126,    56,    46,    47,   -64,    52,    53,    22,    54,    23,
    49,    89,    55,   136,    56,    46,    47,   138,     0,     0,
    22,     2,     0,    49,     3,     4,     5,    52,    53,     6,
    54,    46,    47,     0,    55,     0,    56,   130,     0,    49,
    52,    53,     0,    54,     0,     0,     0,    55,     0,    56,
   133,   114,   115,   116,   117,   118,    52,    53,     0,    54,
     0,     0,     0,    55,     0,    56,   105,   106,   107,   108,
     0,     0,   119,   141,   142,   143,   144
};

static const short yycheck[] = {     0,
    59,    50,    52,    53,    54,    55,     7,    56,     1,     1,
    26,    38,     4,   114,   115,   116,   117,   118,   119,    82,
     4,   122,     1,     5,    40,    31,    32,     1,     1,     1,
    12,    29,    30,    38,    22,    36,     4,    42,    37,    88,
    38,     5,    91,     1,    42,    33,    23,    24,    40,    41,
    43,   101,   102,   103,   104,   105,   106,   107,   108,   109,
   110,   111,   112,   113,   165,   128,    45,    41,    41,    41,
    44,    59,     6,    42,   123,     9,    10,    11,   103,   104,
    14,    82,     4,     5,     6,    38,     8,     9,    10,    11,
    12,    13,    14,    15,    38,     4,    41,     6,   109,   110,
     9,    10,    11,   162,   163,    14,    40,    29,    30,    21,
    32,   170,    22,    39,    36,    39,    38,     4,     5,    41,
    41,     8,    44,    39,    39,    12,    13,   128,    15,    40,
     4,     5,    39,     0,     8,    33,    34,    35,    12,    13,
    43,    15,    29,    30,     7,    32,     0,     7,    12,    36,
    80,    38,     4,     5,    41,    29,    30,    44,    32,    10,
    12,    50,    36,   101,    38,     4,     5,   102,    -1,    -1,
    44,     6,    -1,    12,     9,    10,    11,    29,    30,    14,
    32,     4,     5,    -1,    36,    -1,    38,    39,    -1,    12,
    29,    30,    -1,    32,    -1,    -1,    -1,    36,    -1,    38,
    39,    16,    17,    18,    19,    20,    29,    30,    -1,    32,
    -1,    -1,    -1,    36,    -1,    38,    25,    26,    27,    28,
    -1,    -1,    37,   105,   106,   107,   108
};
/* -*-C-*-  Note some compilers choke on comments on `#line' lines.  */
#line 3 "bison.simple"

/* Skeleton output parser for bison,
   Copyright (C) 1984, 1989, 1990 Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.  */

/* As a special exception, when this file is copied by Bison into a
   Bison output file, you may use that output file without restriction.
   This special exception was added by the Free Software Foundation
   in version 1.24 of Bison.  */

#ifndef alloca
#ifdef __GNUC__
#define alloca __builtin_alloca
#else /* not GNU C.  */
#if (!defined (__STDC__) && defined (sparc)) || defined (__sparc__) || defined (__sparc) || defined (__sgi)
#include <alloca.h>
#else /* not sparc */
#if defined (MSDOS) && !defined (__TURBOC__)
#include <malloc.h>
#else /* not MSDOS, or __TURBOC__ */
#if defined(_AIX)
#include <malloc.h>
 #pragma alloca
#else /* not MSDOS, __TURBOC__, or _AIX */
#ifdef __hpux
#ifdef __cplusplus
extern "C" {
void *alloca (unsigned int);
};
#else /* not __cplusplus */
void *alloca ();
#endif /* not __cplusplus */
#endif /* __hpux */
#endif /* not _AIX */
#endif /* not MSDOS, or __TURBOC__ */
#endif /* not sparc.  */
#endif /* not GNU C.  */
#endif /* alloca not defined.  */

/* This is the parser code that is written into each bison parser
  when the %semantic_parser declaration is not specified in the grammar.
  It was written by Richard Stallman by simplifying the hairy parser
  used when %semantic_parser is specified.  */

/* Note: there must be only one dollar sign in this file.
   It is replaced by the list of actions, each action
   as one case of the switch.  */

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		-2
#define YYEOF		0
#define YYACCEPT	return(0)
#define YYABORT 	return(1)
#define YYERROR		goto yyerrlab1
/* Like YYERROR except do call yyerror.
   This remains here temporarily to ease the
   transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  */
#define YYFAIL		goto yyerrlab
#define YYRECOVERING()  (!!yyerrstatus)
#define YYBACKUP(token, value) \
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    { yychar = (token), yylval = (value);			\
      yychar1 = YYTRANSLATE (yychar);				\
      YYPOPSTACK;						\
      goto yybackup;						\
    }								\
  else								\
    { yyerror ("syntax error: cannot back up"); YYERROR; }	\
while (0)

#define YYTERROR	1
#define YYERRCODE	256

#ifndef YYPURE
#define YYLEX		yylex()
#endif

#ifdef YYPURE
#ifdef YYLSP_NEEDED
#ifdef YYLEX_PARAM
#define YYLEX		yylex(&yylval, &yylloc, YYLEX_PARAM)
#else
#define YYLEX		yylex(&yylval, &yylloc)
#endif
#else /* not YYLSP_NEEDED */
#ifdef YYLEX_PARAM
#define YYLEX		yylex(&yylval, YYLEX_PARAM)
#else
#define YYLEX		yylex(&yylval)
#endif
#endif /* not YYLSP_NEEDED */
#endif

/* If nonreentrant, generate the variables here */

#ifndef YYPURE

int	yychar;			/*  the lookahead symbol		*/
YYSTYPE	yylval;			/*  the semantic value of the		*/
				/*  lookahead symbol			*/

#ifdef YYLSP_NEEDED
YYLTYPE yylloc;			/*  location data for the lookahead	*/
				/*  symbol				*/
#endif

int yynerrs;			/*  number of parse errors so far       */
#endif  /* not YYPURE */

#if YYDEBUG != 0
int yydebug;			/*  nonzero means print parse trace	*/
/* Since this is uninitialized, it does not stop multiple parsers
   from coexisting.  */
#endif

/*  YYINITDEPTH indicates the initial size of the parser's stacks	*/

#ifndef	YYINITDEPTH
#define YYINITDEPTH 200
#endif

/*  YYMAXDEPTH is the maximum size the stacks can grow to
    (effective only if the built-in stack extension method is used).  */

#if YYMAXDEPTH == 0
#undef YYMAXDEPTH
#endif

#ifndef YYMAXDEPTH
#define YYMAXDEPTH 10000
#endif

/* Prevent warning if -Wstrict-prototypes.  */
#ifdef __GNUC__
int yyparse (void);
#endif

#if __GNUC__ > 1		/* GNU C and GNU C++ define this.  */
#define __yy_memcpy(FROM,TO,COUNT)	__builtin_memcpy(TO,FROM,COUNT)
#else				/* not GNU C or C++ */
#ifndef __cplusplus

/* This is the most reliable way to avoid incompatibilities
   in available built-in functions on various systems.  */
static void
__yy_memcpy (from, to, count)
     char *from;
     char *to;
     int count;
{
  register char *f = from;
  register char *t = to;
  register int i = count;

  while (i-- > 0)
    *t++ = *f++;
}

#else /* __cplusplus */

/* This is the most reliable way to avoid incompatibilities
   in available built-in functions on various systems.  */
static void
__yy_memcpy (char *from, char *to, int count)
{
  register char *f = from;
  register char *t = to;
  register int i = count;

  while (i-- > 0)
    *t++ = *f++;
}

#endif
#endif

#line 192 "bison.simple"

/* The user can define YYPARSE_PARAM as the name of an argument to be passed
   into yyparse.  The argument should have type void *.
   It should actually point to an object.
   Grammar actions can access the variable by casting it
   to the proper pointer type.  */

#ifdef YYPARSE_PARAM
#define YYPARSE_PARAM_DECL void *YYPARSE_PARAM;
#else
#define YYPARSE_PARAM
#define YYPARSE_PARAM_DECL
#endif

int
yyparse(YYPARSE_PARAM)
     YYPARSE_PARAM_DECL
{
  register int yystate;
  register int yyn;
  register short *yyssp;
  register YYSTYPE *yyvsp;
  int yyerrstatus;	/*  number of tokens to shift before error messages enabled */
  int yychar1 = 0;		/*  lookahead token as an internal (translated) token number */

  short	yyssa[YYINITDEPTH];	/*  the state stack			*/
  YYSTYPE yyvsa[YYINITDEPTH];	/*  the semantic value stack		*/

  short *yyss = yyssa;		/*  refer to the stacks thru separate pointers */
  YYSTYPE *yyvs = yyvsa;	/*  to allow yyoverflow to reallocate them elsewhere */

#ifdef YYLSP_NEEDED
  YYLTYPE yylsa[YYINITDEPTH];	/*  the location stack			*/
  YYLTYPE *yyls = yylsa;
  YYLTYPE *yylsp;

#define YYPOPSTACK   (yyvsp--, yyssp--, yylsp--)
#else
#define YYPOPSTACK   (yyvsp--, yyssp--)
#endif

  int yystacksize = YYINITDEPTH;

#ifdef YYPURE
  int yychar;
  YYSTYPE yylval;
  int yynerrs;
#ifdef YYLSP_NEEDED
  YYLTYPE yylloc;
#endif
#endif

  YYSTYPE yyval;		/*  the variable used to return		*/
				/*  semantic values from the action	*/
				/*  routines				*/

  int yylen;

#if YYDEBUG != 0
  if (yydebug)
    fprintf(stderr, "Starting parse\n");
#endif

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY;		/* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */

  yyssp = yyss - 1;
  yyvsp = yyvs;
#ifdef YYLSP_NEEDED
  yylsp = yyls;
#endif

/* Push a new state, which is found in  yystate  .  */
/* In all cases, when you get here, the value and location stacks
   have just been pushed. so pushing a state here evens the stacks.  */
yynewstate:

  *++yyssp = yystate;

  if (yyssp >= yyss + yystacksize - 1)
    {
      /* Give user a chance to reallocate the stack */
      /* Use copies of these so that the &'s don't force the real ones into memory. */
      YYSTYPE *yyvs1 = yyvs;
      short *yyss1 = yyss;
#ifdef YYLSP_NEEDED
      YYLTYPE *yyls1 = yyls;
#endif

      /* Get the current used size of the three stacks, in elements.  */
      int size = yyssp - yyss + 1;

#ifdef yyoverflow
      /* Each stack pointer address is followed by the size of
	 the data in use in that stack, in bytes.  */
#ifdef YYLSP_NEEDED
      /* This used to be a conditional around just the two extra args,
	 but that might be undefined if yyoverflow is a macro.  */
      yyoverflow("parser stack overflow",
		 &yyss1, size * sizeof (*yyssp),
		 &yyvs1, size * sizeof (*yyvsp),
		 &yyls1, size * sizeof (*yylsp),
		 &yystacksize);
#else
      yyoverflow("parser stack overflow",
		 &yyss1, size * sizeof (*yyssp),
		 &yyvs1, size * sizeof (*yyvsp),
		 &yystacksize);
#endif

      yyss = yyss1; yyvs = yyvs1;
#ifdef YYLSP_NEEDED
      yyls = yyls1;
#endif
#else /* no yyoverflow */
      /* Extend the stack our own way.  */
      if (yystacksize >= YYMAXDEPTH)
	{
	  yyerror("parser stack overflow");
	  return 2;
	}
      yystacksize *= 2;
      if (yystacksize > YYMAXDEPTH)
	yystacksize = YYMAXDEPTH;
      yyss = (short *) alloca (yystacksize * sizeof (*yyssp));
      __yy_memcpy ((char *)yyss1, (char *)yyss, size * sizeof (*yyssp));
      yyvs = (YYSTYPE *) alloca (yystacksize * sizeof (*yyvsp));
      __yy_memcpy ((char *)yyvs1, (char *)yyvs, size * sizeof (*yyvsp));
#ifdef YYLSP_NEEDED
      yyls = (YYLTYPE *) alloca (yystacksize * sizeof (*yylsp));
      __yy_memcpy ((char *)yyls1, (char *)yyls, size * sizeof (*yylsp));
#endif
#endif /* no yyoverflow */

      yyssp = yyss + size - 1;
      yyvsp = yyvs + size - 1;
#ifdef YYLSP_NEEDED
      yylsp = yyls + size - 1;
#endif

#if YYDEBUG != 0
      if (yydebug)
	fprintf(stderr, "Stack size increased to %d\n", yystacksize);
#endif

      if (yyssp >= yyss + yystacksize - 1)
	YYABORT;
    }

#if YYDEBUG != 0
  if (yydebug)
    fprintf(stderr, "Entering state %d\n", yystate);
#endif

  goto yybackup;
 yybackup:

/* Do appropriate processing given the current state.  */
/* Read a lookahead token if we need one and don't already have one.  */
/* yyresume: */

  /* First try to decide what to do without reference to lookahead token.  */

  yyn = yypact[yystate];
  if (yyn == YYFLAG)
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* yychar is either YYEMPTY or YYEOF
     or a valid token in external form.  */

  if (yychar == YYEMPTY)
    {
#if YYDEBUG != 0
      if (yydebug)
	fprintf(stderr, "Reading a token: ");
#endif
      yychar = YYLEX;
    }

  /* Convert token to internal form (in yychar1) for indexing tables with */

  if (yychar <= 0)		/* This means end of input. */
    {
      yychar1 = 0;
      yychar = YYEOF;		/* Don't call YYLEX any more */

#if YYDEBUG != 0
      if (yydebug)
	fprintf(stderr, "Now at end of input.\n");
#endif
    }
  else
    {
      yychar1 = YYTRANSLATE(yychar);

#if YYDEBUG != 0
      if (yydebug)
	{
	  fprintf (stderr, "Next token is %d (%s", yychar, yytname[yychar1]);
	  /* Give the individual parser a way to print the precise meaning
	     of a token, for further debugging info.  */
#ifdef YYPRINT
	  YYPRINT (stderr, yychar, yylval);
#endif
	  fprintf (stderr, ")\n");
	}
#endif
    }

  yyn += yychar1;
  if (yyn < 0 || yyn > YYLAST || yycheck[yyn] != yychar1)
    goto yydefault;

  yyn = yytable[yyn];

  /* yyn is what to do for this token type in this state.
     Negative => reduce, -yyn is rule number.
     Positive => shift, yyn is new state.
       New state is final state => don't bother to shift,
       just return success.
     0, or most negative number => error.  */

  if (yyn < 0)
    {
      if (yyn == YYFLAG)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }
  else if (yyn == 0)
    goto yyerrlab;

  if (yyn == YYFINAL)
    YYACCEPT;

  /* Shift the lookahead token.  */

#if YYDEBUG != 0
  if (yydebug)
    fprintf(stderr, "Shifting token %d (%s), ", yychar, yytname[yychar1]);
#endif

  /* Discard the token being shifted unless it is eof.  */
  if (yychar != YYEOF)
    yychar = YYEMPTY;

  *++yyvsp = yylval;
#ifdef YYLSP_NEEDED
  *++yylsp = yylloc;
#endif

  /* count tokens shifted since error; after three, turn off error status.  */
  if (yyerrstatus) yyerrstatus--;

  yystate = yyn;
  goto yynewstate;

/* Do the default action for the current state.  */
yydefault:

  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;

/* Do a reduction.  yyn is the number of a rule to reduce with.  */
yyreduce:
  yylen = yyr2[yyn];
  if (yylen > 0)
    yyval = yyvsp[1-yylen]; /* implement default value of the action */

#if YYDEBUG != 0
  if (yydebug)
    {
      int i;

      fprintf (stderr, "Reducing via rule %d (line %d), ",
	       yyn, yyrline[yyn]);

      /* Print the symbols being reduced, and their result.  */
      for (i = yyprhs[yyn]; yyrhs[i] > 0; i++)
	fprintf (stderr, "%s ", yytname[yyrhs[i]]);
      fprintf (stderr, " -> %s\n", yytname[yyr1[yyn]]);
    }
#endif


  switch (yyn) {

case 7:
#line 51 "parser.y"
{
                        yyerrok;
                        printError(wrong_st);       /* error - wrong statement */
                    ;
    break;}
case 10:
#line 60 "parser.y"
{
                        yyerrok;
                        printError(wrong_funcdef);  /* error - wrong function definition */
                    ;
    break;}
case 18:
#line 84 "parser.y"
{
                        type_int = 1;      /* type : integer */
                        type_void = 0;
                        type_float = 0;
                        type_char = 0;
                    ;
    break;}
case 19:
#line 92 "parser.y"
{
                        type_int = 0;
                        type_void = 1;      /* type : void */
                        type_float = 0;
                        type_char = 0;
                    ;
    break;}
case 20:
#line 100 "parser.y"
{
                        type_int = 0;
                        type_void = 0;
                        type_float = 1;     /* type : float */
                        type_char = 0;
                    ;
    break;}
case 21:
#line 108 "parser.y"
{
                        type_int = 0;
                        type_void = 0;
                        type_float = 0;
                        type_char = 1;      /* type : char */
                    ;
    break;}
case 22:
#line 117 "parser.y"
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
                    ;
    break;}
case 23:
#line 133 "parser.y"
{
                        yyerrok;
                        printError(wrong_funcdef);  /* error - wrong function definition */
                    ;
    break;}
case 29:
#line 149 "parser.y"
{
                        yyerrok;
                        printError(nocomma);    /* error - no comma */
                    ;
    break;}
case 30:
#line 156 "parser.y"
{
                        if (look_id->type == 1)
                        {
                            type_int = 0;
                            type_void = 0;
                            type_float = 0;
                            type_char = 0;
                            look_id->type = 12;  /* function parameter */
                        }
                    ;
    break;}
case 32:
#line 170 "parser.y"
{
                        yyerrok;
                        printError(nobrace);    /* error - no brace */
                    ;
    break;}
case 38:
#line 188 "parser.y"
{
                        type_int = 0;
                        type_void = 0;
                        type_float = 0;
                        type_char = 0;
                    ;
    break;}
case 39:
#line 196 "parser.y"
{
                        yyerrok;
                        type_int = 0;
                        type_void = 0;
                        type_float = 0;
                        type_char = 0;
                        printError(nosemi);     /* error - no semicolon */
                    ;
    break;}
case 42:
#line 209 "parser.y"
{
                        yyerrok;
                        printError(nocomma);    /* error - no comma */
                    ;
    break;}
case 46:
#line 221 "parser.y"
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
                    ;
    break;}
case 47:
#line 237 "parser.y"
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
                    ;
    break;}
case 48:
#line 251 "parser.y"
{
                        yyerrok;
                        printError(nobracket);      /* error - no bracket */
                    ;
    break;}
case 62:
#line 279 "parser.y"
{
                        yyerrok;
                        printError(nosemi);     /* error - no semicolon */
                    ;
    break;}
case 67:
#line 292 "parser.y"
{
                        yyerrok;
                        printError(nocondition);    /* error - no condition */
                    ;
    break;}
case 69:
#line 300 "parser.y"
{
                        yyerrok;
                        printError(nocondition);    /* error - no condition */
                    ;
    break;}
case 113:
#line 378 "parser.y"
{
                        if (look_id->type == 0)
                            look_id->type = 13;        /* not defined identifier or function */
                    ;
    break;}
}
   /* the action file gets copied in in place of this dollarsign */
#line 487 "bison.simple"

  yyvsp -= yylen;
  yyssp -= yylen;
#ifdef YYLSP_NEEDED
  yylsp -= yylen;
#endif

#if YYDEBUG != 0
  if (yydebug)
    {
      short *ssp1 = yyss - 1;
      fprintf (stderr, "state stack now");
      while (ssp1 != yyssp)
	fprintf (stderr, " %d", *++ssp1);
      fprintf (stderr, "\n");
    }
#endif

  *++yyvsp = yyval;

#ifdef YYLSP_NEEDED
  yylsp++;
  if (yylen == 0)
    {
      yylsp->first_line = yylloc.first_line;
      yylsp->first_column = yylloc.first_column;
      yylsp->last_line = (yylsp-1)->last_line;
      yylsp->last_column = (yylsp-1)->last_column;
      yylsp->text = 0;
    }
  else
    {
      yylsp->last_line = (yylsp+yylen-1)->last_line;
      yylsp->last_column = (yylsp+yylen-1)->last_column;
    }
#endif

  /* Now "shift" the result of the reduction.
     Determine what state that goes to,
     based on the state we popped back to
     and the rule number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTBASE] + *yyssp;
  if (yystate >= 0 && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTBASE];

  goto yynewstate;

yyerrlab:   /* here on detecting error */

  if (! yyerrstatus)
    /* If not already recovering from an error, report this error.  */
    {
      ++yynerrs;

#ifdef YYERROR_VERBOSE
      yyn = yypact[yystate];

      if (yyn > YYFLAG && yyn < YYLAST)
	{
	  int size = 0;
	  char *msg;
	  int x, count;

	  count = 0;
	  /* Start X at -yyn if nec to avoid negative indexes in yycheck.  */
	  for (x = (yyn < 0 ? -yyn : 0);
	       x < (sizeof(yytname) / sizeof(char *)); x++)
	    if (yycheck[x + yyn] == x)
	      size += strlen(yytname[x]) + 15, count++;
	  msg = (char *) malloc(size + 15);
	  if (msg != 0)
	    {
	      strcpy(msg, "parse error");

	      if (count < 5)
		{
		  count = 0;
		  for (x = (yyn < 0 ? -yyn : 0);
		       x < (sizeof(yytname) / sizeof(char *)); x++)
		    if (yycheck[x + yyn] == x)
		      {
			strcat(msg, count == 0 ? ", expecting `" : " or `");
			strcat(msg, yytname[x]);
			strcat(msg, "'");
			count++;
		      }
		}
	      yyerror(msg);
	      free(msg);
	    }
	  else
	    yyerror ("parse error; also virtual memory exceeded");
	}
      else
#endif /* YYERROR_VERBOSE */
	yyerror("parse error");
    }

  goto yyerrlab1;
yyerrlab1:   /* here on error raised explicitly by an action */

  if (yyerrstatus == 3)
    {
      /* if just tried and failed to reuse lookahead token after an error, discard it.  */

      /* return failure if at end of input */
      if (yychar == YYEOF)
	YYABORT;

#if YYDEBUG != 0
      if (yydebug)
	fprintf(stderr, "Discarding token %d (%s).\n", yychar, yytname[yychar1]);
#endif

      yychar = YYEMPTY;
    }

  /* Else will try to reuse lookahead token
     after shifting the error token.  */

  yyerrstatus = 3;		/* Each real token shifted decrements this */

  goto yyerrhandle;

yyerrdefault:  /* current state does not do anything special for the error token. */

#if 0
  /* This is wrong; only states that explicitly want error tokens
     should shift them.  */
  yyn = yydefact[yystate];  /* If its default is to accept any token, ok.  Otherwise pop it.*/
  if (yyn) goto yydefault;
#endif

yyerrpop:   /* pop the current state because it cannot handle the error token */

  if (yyssp == yyss) YYABORT;
  yyvsp--;
  yystate = *--yyssp;
#ifdef YYLSP_NEEDED
  yylsp--;
#endif

#if YYDEBUG != 0
  if (yydebug)
    {
      short *ssp1 = yyss - 1;
      fprintf (stderr, "Error: state stack now");
      while (ssp1 != yyssp)
	fprintf (stderr, " %d", *++ssp1);
      fprintf (stderr, "\n");
    }
#endif

yyerrhandle:

  yyn = yypact[yystate];
  if (yyn == YYFLAG)
    goto yyerrdefault;

  yyn += YYTERROR;
  if (yyn < 0 || yyn > YYLAST || yycheck[yyn] != YYTERROR)
    goto yyerrdefault;

  yyn = yytable[yyn];
  if (yyn < 0)
    {
      if (yyn == YYFLAG)
	goto yyerrpop;
      yyn = -yyn;
      goto yyreduce;
    }
  else if (yyn == 0)
    goto yyerrpop;

  if (yyn == YYFINAL)
    YYACCEPT;

#if YYDEBUG != 0
  if (yydebug)
    fprintf(stderr, "Shifting error token, ");
#endif

  *++yyvsp = yylval;
#ifdef YYLSP_NEEDED
  *++yylsp = yylloc;
#endif

  yystate = yyn;
  goto yynewstate;
}
#line 387 "parser.y"


void line(int n) {
    printf("\t %d \t", n);
}