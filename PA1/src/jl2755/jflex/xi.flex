/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2015  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* Java 1.2 language lexer specification */

/* Use together with unicode.flex for Unicode preprocesssing */
/* and java12.cup for a Java 1.2 parser                      */

/* Note that this lexer specification is not tuned for speed.
   It is in fact quite slow on integer and floating point literals, 
   because the input is read twice and the methods used to parse
   the numbers are not very fast. 
   For a production quality application (e.g. a Java compiler) 
   this could be optimized */


import java_cup.runtime.*;

%%

%public
%class Scanner

%unicode

%line
%column

%cup
%cupdebug

%{  
  StringBuilder string = new StringBuilder();
<<<<<<< HEAD
=======

>>>>>>> 0917ea3ab252f94179e8fd628be07b55470fdedf
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }
%}

/* main character classes */
LineTerminator = \n
InputCharacter = [^\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = "//" {InputCharacter}* {LineTerminator}

/* identifiers */
Identifier = [:letter:]([:letter:]|[:digit:]|"_"|"'")*

/* integer literals */
IntegerLiteral = 0 | [1-9][0-9]*
HexLiteral = ([0-6]([0-9]|[A-F])) | ("7"([0-9]|[A-E]))

/* string and character literals */
SingleCharacter = [^\n\'\\\"]

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "bool"                         { return symbol(sym.BOOLEAN); }
  "else"                         { return symbol(sym.ELSE); }
  "if"                           { return symbol(sym.IF); }
  "return"                       { return symbol(sym.RETURN); }
  "while"                        { return symbol(sym.WHILE); }
  
  /* boolean literals */
  "true"                         { return symbol(sym.BOOLEAN_LITERAL, true); }
  "false"                        { return symbol(sym.BOOLEAN_LITERAL, false); }
  
  
  /* separators */
  "("                            { return symbol(sym.LPAREN); }
  ")"                            { return symbol(sym.RPAREN); }
  "{"                            { return symbol(sym.LBRACE); }
  "}"                            { return symbol(sym.RBRACE); }
  "["                            { return symbol(sym.LBRACK); }
  "]"                            { return symbol(sym.RBRACK); }
  ";"                            { return symbol(sym.SEMICOLON); }
  ","                            { return symbol(sym.COMMA); }
  
  /* operators */
  "="                            { return symbol(sym.EQ); }
  ">"                            { return symbol(sym.GT); }
  "<"                            { return symbol(sym.LT); }
  "!"                            { return symbol(sym.NOT); }
  ":"                            { return symbol(sym.COLON); }
  "=="                           { return symbol(sym.EQEQ); }
  "<="                           { return symbol(sym.LTEQ); }
  ">="                           { return symbol(sym.GTEQ); }
  "!="                           { return symbol(sym.NOTEQ); }
  "+"                            { return symbol(sym.PLUS); }
  "-"                            { return symbol(sym.MINUS); }
  "*"                            { return symbol(sym.MULT); }
  "/"                            { return symbol(sym.DIV); }
  "&"                            { return symbol(sym.AND); }
  "|"                            { return symbol(sym.OR); }
  "%"                            { return symbol(sym.MOD); }
  "*>>"                          { return symbol(sym.HIGHMULT); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
  {IntegerLiteral}               { return symbol(sym.INTEGER_LITERAL, new Integer(yytext())); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(sym.IDENTIFIER, yytext()); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, string.toString()); } // empty string
  
  {SingleCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\n"                          { string.append( '\n' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[xX]{HexLiteral}             { string.append( yytext() ); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, yytext().charAt(0)); }
  
  /* escape sequences */
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\n'); }
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\''); }
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\\'); }
  \\[xX]{HexLiteral}             { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, yytext()); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(sym.EOF); }