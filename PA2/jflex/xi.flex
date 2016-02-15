/* Xi language lexer specification */

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
  public int origLine;
  public int origCol;

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
Comment = "//" {InputCharacter}* {LineTerminator}

/* identifiers */
Identifier = [:letter:] ([:letter:]|[:digit:]|"_"|"'")*

/* integer literals */
IntegerLiteral = (0 | [1-9][0-9]*)

/* hexadecimal literals */
HexLiteral = ( ([0-9]|[A-F]) ([0-9]|[A-F]) )
PrintableHexLiteral = ( [2-6]([0-9]|[A-F]) ) | ( "7"([0-9]|[A-E]) )

/* string and character literals */
SingleCharacter = [^\n\'\\\"]

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "bool"                         { return symbol(sym.BOOLEAN); }
  "else"                         { return symbol(sym.ELSE); }
  "if"                           { return symbol(sym.IF); }
  "int"                          { return symbol(sym.INT); }
  "length"                       { return symbol(sym.LENGTH); }
  "return"                       { return symbol(sym.RETURN); }
  "use"                          { return symbol(sym.USE); }
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
  "_"                            { return symbol(sym.UNDERSCORE); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); origLine = yyline+1; origCol = yycolumn+1; }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); origLine = yyline+1; origCol = yycolumn+1; }

  /* numeric literals */
  "-9223372036854775808"         { return symbol(sym.INTEGER_LITERAL, new Long(Long.MIN_VALUE)); }
  {IntegerLiteral}               { return symbol(sym.INTEGER_LITERAL, new Long(yytext())); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(sym.IDENTIFIER, yytext()); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, origLine, origCol, string.toString()); } // empty string
  
  {SingleCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\n"                          { string.append( "\\n" ); }
  "\\'"                          { string.append( "\\'" ); }
  "\\\\"                         { string.append( "\\" ); }
  \\[x]{PrintableHexLiteral}     { string.append( parseHex(yytext()) ); }
  \\[x]{HexLiteral}              { string.append( yytext() ); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, yytext().charAt(0)); }
  
  /* escape sequences */
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, "\\n"); }
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, "\\'"); }
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, "\\"); }
  \\[x]{PrintableHexLiteral}\'   { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, parseHex(yytext().substring(0, yylength()-1))); }  
  \\[x]{HexLiteral}\'            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, origLine, origCol, yytext().substring(0, yylength()-1)); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(sym.EOF); }