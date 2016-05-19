/* Xi language lexer specification */

package jl2755;

import java_cup.runtime.*;
import jl2755.exceptions.LexicalError;

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
  public int origLine;
  public int origCol;
  public int currCol;

  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }

  private Symbol symbol(int type, int row, int column, Object value) {
    return new Symbol(type, row, column, value);
  }

  /*
   * Returns the ASCII character converted from hex.
   *
   * @param hex the string to be converted to ASCII
   * @precondition hex must be of the form "\x" followed
   * by two hex digits. Additionally, hex must be printable
   */
  private char parseHex(String hex) {
    int num = Integer.decode("0" + hex.substring(1));
    return (char) num; 
  }

  /*
   * Returns the current line number of the scanner
   */
  public int yyline() {
    return yyline + 1;
  }

  /*
   * Returns the current column number of the scanner
   */
  public int yycolumn() {
    return yycolumn + 1;
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = "//" {InputCharacter}* {LineTerminator}?

/* identifiers */
Identifier = [:letter:] ([:letter:]|[:digit:]|"_"|"'")*

/* integer literals */
IntegerLiteral = (0 | [1-9][0-9]*)

/* hexadecimal literals */
HexLiteral = ( ([0-9]|[A-F]) ([0-9]|[A-F]) )
PrintableHexLiteral = ( [2-6]([0-9]|[A-F]) ) | ( "7"([0-9]|[A-E]) )

/* string and character literals */
SingleCharacter = [^\'\\\"\b\t\f\n\r]

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "bool"                         { return symbol(sym.BOOL,"bool"); }
  "break"                        { return symbol(sym.BREAK,"break"); }
  "class"                        { return symbol(sym.CLASS,"class"); }
  "continue"                     { return symbol(sym.CONTINUE,"continue"); }
  "else"                         { return symbol(sym.ELSE,"else"); }
  "extends"                      { return symbol(sym.EXTENDS,"extends"); }
  "if"                           { return symbol(sym.IF,"if"); }
  "int"                          { return symbol(sym.INT,"int"); }
  "length"                       { return symbol(sym.LENGTH,"length"); }
  "new"                          { return symbol(sym.NEW,"new"); }
  "null"                         { return symbol(sym.NULL,"null"); }
  "return"                       { return symbol(sym.RETURN,"return"); }
  "this"                         { return symbol(sym.THIS,"this"); }
  "_to_"                         { return symbol(sym.TO,"_to_"); }
  "_from_"                       { return symbol(sym.FROM,"_from_"); }
  "use"                          { return symbol(sym.USE,"use"); }
  "while"                        { return symbol(sym.WHILE,"while"); }

  
  /* boolean literals */
  "true"                         { return symbol(sym.TRUE,Boolean.TRUE); }
  "false"                        { return symbol(sym.FALSE,Boolean.FALSE); }
  
  
  /* separators */
  "("                            { return symbol(sym.OPEN_PAREN,"("); }
  ")"                            { return symbol(sym.CLOSE_PAREN,")"); }
  "{"                            { return symbol(sym.OPEN_BRACE,"{"); }
  "}"                            { return symbol(sym.CLOSE_BRACE,"}"); }
  "["                            { return symbol(sym.OPEN_BRACKET,"["); }
  "]"                            { return symbol(sym.CLOSE_BRACKET,"]"); }
  ";"                            { return symbol(sym.SEMICOLON,";"); }
  ","                            { return symbol(sym.COMMA,","); }
  
  /* operators */
  "."                            { return symbol(sym.DOT,"."); }
  "="                            { return symbol(sym.GETS,"="); }
  ">"                            { return symbol(sym.GT,">"); }
  "<"                            { return symbol(sym.LT,"<"); }
  "!"                            { return symbol(sym.NOT,"!"); }
  ":"                            { return symbol(sym.COLON,":"); }
  "=="                           { return symbol(sym.EQUAL,"=="); }
  "<="                           { return symbol(sym.LEQ,"<="); }
  ">="                           { return symbol(sym.GEQ,">="); }
  "!="                           { return symbol(sym.NOT_EQUAL,"!="); }
  "+"                            { return symbol(sym.PLUS,"+"); }
  "++"                           { return symbol(sym.INC,"++"); }
  "--"                           { return symbol(sym.DEC,"--"); }
  "-"                            { return symbol(sym.MINUS,"-"); }
  "*"                            { return symbol(sym.TIMES,"*"); }
  "/"                            { return symbol(sym.DIVIDE,"/"); }
  "&"                            { return symbol(sym.AND,"&"); }
  "|"                            { return symbol(sym.OR,"|"); }
  "%"                            { return symbol(sym.MODULO,"%"); }
  "*>>"                          { return symbol(sym.HIGH_MULT,"*>>"); }
  "_"                            { return symbol(sym.UNDERSCORE,"_"); }

  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); origLine = yyline+1; origCol = yycolumn+1; currCol = yycolumn+1; }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); origLine = yyline+1; origCol = yycolumn+1; }

  /* numeric literals */
  {IntegerLiteral}               { return symbol(sym.INTEGER_LITERAL, yytext()); }

  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(sym.IDENTIFIER, yytext()); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, origLine, origCol, string.toString()); } // empty string
  
  {SingleCharacter}              { string.append( yytext() ); currCol++; }
  
  /* escape sequences */
  "'"                            { string.append( "'" ); currCol++; }
  "\\b"                          { string.append( "\\b" ); currCol++; }
  "\\t"                          { string.append( "\\t" ); currCol++; }
  "\\f"                          { string.append( "\\f" ); currCol++; }
  "\\n"                          { string.append( "\\n" ); currCol++; }
  "\\r"                          { string.append( "\\r" ); currCol++; }
  "\\'"                          { string.append( "\\'" ); currCol++; }
  "\\\\"                         { string.append( "\\\\" ); currCol++; }
  "\\\""                         { string.append( "\\\"" ); currCol++; }
  \\[x]{PrintableHexLiteral}     { string.append( parseHex(yytext()) ); currCol++; }
  \\[x]{HexLiteral}              { string.append( yytext() ); currCol = currCol+4; }
  
  /* error cases */
  {LineTerminator}               { throw new LexicalError(yyline(), currCol+1, "Illegal input \"\\n\""); }

  [^]                            { throw new LexicalError(yyline(), currCol+1, "Illegal input \"" + yytext() + "\""); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, yytext().substring(0,1)); }
  
  /* escape sequences */
  "\""\'                         { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, "\""); }
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, "\\n"); }
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, "\\'"); }
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, "\\\\"); }
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, "\\\""); }
  \\[x]{PrintableHexLiteral}\'   { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, parseHex(yytext().substring(0, yylength()))); }  
  \\[x]{HexLiteral}\'            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, yytext().substring(0, yylength())); }
  
  /* error cases */
  \'                             { throw new LexicalError(origLine, origCol, "Illegal input \"\'\""); }
  {LineTerminator}               { throw new LexicalError(yyline(), yycolumn(), "Illegal input \"\\n\""); }
}

/* error fallback */
[^]                              { throw new LexicalError(yyline(), yycolumn(), "Illegal input \"" + yytext() + "\""); }
<<EOF>>                          { return symbol(sym.EOF); }
