package jl2755;

/* The following code was generated by JFlex 1.6.1 */

/* Xi language lexer specification */


import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>/Users/thomasaeyo/Desktop/CS4120/CS4120/PA1/src/jl2755/jflex/xi.flex</tt>
 */
public class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;
  public static final int CHARLITERAL = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\2\1\1\1\62\1\63\1\62\22\0\1\2\1\47\1\57"+
    "\2\0\1\56\1\54\1\6\1\34\1\35\1\53\1\51\1\43\1\52"+
    "\1\0\1\3\1\7\1\10\5\12\1\13\2\10\1\50\1\42\1\46"+
    "\1\44\1\45\2\0\5\14\1\11\21\4\1\61\2\4\1\40\1\15"+
    "\1\41\1\0\1\5\1\0\1\33\1\16\2\4\1\21\1\24\1\4"+
    "\1\32\1\23\2\4\1\20\1\4\1\25\1\17\2\4\1\27\1\22"+
    "\1\26\1\30\1\4\1\31\1\60\2\4\1\36\1\55\1\37\7\0"+
    "\1\62\44\0\1\4\12\0\1\4\4\0\1\4\5\0\27\4\1\0"+
    "\37\4\1\0\u01ca\4\4\0\14\4\16\0\5\4\7\0\1\4\1\0"+
    "\1\4\201\0\5\4\1\0\2\4\2\0\4\4\1\0\1\4\6\0"+
    "\1\4\1\0\3\4\1\0\1\4\1\0\24\4\1\0\123\4\1\0"+
    "\213\4\10\0\246\4\1\0\46\4\2\0\1\4\7\0\47\4\110\0"+
    "\33\4\5\0\3\4\55\0\53\4\25\0\12\5\4\0\2\4\1\0"+
    "\143\4\1\0\1\4\17\0\2\4\7\0\2\4\12\5\3\4\2\0"+
    "\1\4\20\0\1\4\1\0\36\4\35\0\131\4\13\0\1\4\16\0"+
    "\12\5\41\4\11\0\2\4\4\0\1\4\5\0\26\4\4\0\1\4"+
    "\11\0\1\4\3\0\1\4\27\0\31\4\107\0\23\4\121\0\66\4"+
    "\3\0\1\4\22\0\1\4\7\0\12\4\4\0\12\5\1\0\20\4"+
    "\4\0\10\4\2\0\2\4\2\0\26\4\1\0\7\4\1\0\1\4"+
    "\3\0\4\4\3\0\1\4\20\0\1\4\15\0\2\4\1\0\3\4"+
    "\4\0\12\5\2\4\23\0\6\4\4\0\2\4\2\0\26\4\1\0"+
    "\7\4\1\0\2\4\1\0\2\4\1\0\2\4\37\0\4\4\1\0"+
    "\1\4\7\0\12\5\2\0\3\4\20\0\11\4\1\0\3\4\1\0"+
    "\26\4\1\0\7\4\1\0\2\4\1\0\5\4\3\0\1\4\22\0"+
    "\1\4\17\0\2\4\4\0\12\5\25\0\10\4\2\0\2\4\2\0"+
    "\26\4\1\0\7\4\1\0\2\4\1\0\5\4\3\0\1\4\36\0"+
    "\2\4\1\0\3\4\4\0\12\5\1\0\1\4\21\0\1\4\1\0"+
    "\6\4\3\0\3\4\1\0\4\4\3\0\2\4\1\0\1\4\1\0"+
    "\2\4\3\0\2\4\3\0\3\4\3\0\14\4\26\0\1\4\25\0"+
    "\12\5\25\0\10\4\1\0\3\4\1\0\27\4\1\0\20\4\3\0"+
    "\1\4\32\0\2\4\6\0\2\4\4\0\12\5\25\0\10\4\1\0"+
    "\3\4\1\0\27\4\1\0\12\4\1\0\5\4\3\0\1\4\40\0"+
    "\1\4\1\0\2\4\4\0\12\5\1\0\2\4\22\0\10\4\1\0"+
    "\3\4\1\0\51\4\2\0\1\4\20\0\1\4\21\0\2\4\4\0"+
    "\12\5\12\0\6\4\5\0\22\4\3\0\30\4\1\0\11\4\1\0"+
    "\1\4\2\0\7\4\37\0\12\5\21\0\60\4\1\0\2\4\14\0"+
    "\7\4\11\0\12\5\47\0\2\4\1\0\1\4\2\0\2\4\1\0"+
    "\1\4\2\0\1\4\6\0\4\4\1\0\7\4\1\0\3\4\1\0"+
    "\1\4\1\0\1\4\2\0\2\4\1\0\4\4\1\0\2\4\11\0"+
    "\1\4\2\0\5\4\1\0\1\4\11\0\12\5\2\0\4\4\40\0"+
    "\1\4\37\0\12\5\26\0\10\4\1\0\44\4\33\0\5\4\163\0"+
    "\53\4\24\0\1\4\12\5\6\0\6\4\4\0\4\4\3\0\1\4"+
    "\3\0\2\4\7\0\3\4\4\0\15\4\14\0\1\4\1\0\12\5"+
    "\6\0\46\4\1\0\1\4\5\0\1\4\2\0\53\4\1\0\u014d\4"+
    "\1\0\4\4\2\0\7\4\1\0\1\4\1\0\4\4\2\0\51\4"+
    "\1\0\4\4\2\0\41\4\1\0\4\4\2\0\7\4\1\0\1\4"+
    "\1\0\4\4\2\0\17\4\1\0\71\4\1\0\4\4\2\0\103\4"+
    "\45\0\20\4\20\0\125\4\14\0\u026c\4\2\0\21\4\1\0\32\4"+
    "\5\0\113\4\6\0\10\4\7\0\15\4\1\0\4\4\16\0\22\4"+
    "\16\0\22\4\16\0\15\4\1\0\3\4\17\0\64\4\43\0\1\4"+
    "\4\0\1\4\3\0\12\5\46\0\12\5\6\0\130\4\10\0\51\4"+
    "\1\0\1\4\5\0\106\4\12\0\37\4\47\0\12\5\36\4\2\0"+
    "\5\4\13\0\54\4\25\0\7\4\10\0\12\5\46\0\27\4\11\0"+
    "\65\4\53\0\12\5\6\0\12\5\15\0\1\4\135\0\57\4\21\0"+
    "\7\4\4\0\12\5\51\0\36\4\15\0\2\4\12\5\54\4\32\0"+
    "\44\4\34\0\12\5\3\0\3\4\12\5\44\4\153\0\4\4\1\0"+
    "\4\4\3\0\2\4\11\0\300\4\100\0\u0116\4\2\0\6\4\2\0"+
    "\46\4\2\0\6\4\2\0\10\4\1\0\1\4\1\0\1\4\1\0"+
    "\1\4\1\0\37\4\2\0\65\4\1\0\7\4\1\0\1\4\3\0"+
    "\3\4\1\0\7\4\3\0\4\4\2\0\6\4\4\0\15\4\5\0"+
    "\3\4\1\0\7\4\53\0\1\62\1\62\107\0\1\4\15\0\1\4"+
    "\20\0\15\4\145\0\1\4\4\0\1\4\2\0\12\4\1\0\1\4"+
    "\3\0\5\4\6\0\1\4\1\0\1\4\1\0\1\4\1\0\4\4"+
    "\1\0\13\4\2\0\4\4\5\0\5\4\4\0\1\4\64\0\2\4"+
    "\u0a7b\0\57\4\1\0\57\4\1\0\205\4\6\0\4\4\3\0\2\4"+
    "\14\0\46\4\1\0\1\4\5\0\1\4\2\0\70\4\7\0\1\4"+
    "\20\0\27\4\11\0\7\4\1\0\7\4\1\0\7\4\1\0\7\4"+
    "\1\0\7\4\1\0\7\4\1\0\7\4\1\0\7\4\120\0\1\4"+
    "\u01d5\0\2\4\52\0\5\4\5\0\2\4\4\0\126\4\6\0\3\4"+
    "\1\0\132\4\1\0\4\4\5\0\51\4\3\0\136\4\21\0\33\4"+
    "\65\0\20\4\u0200\0\u19b6\4\112\0\u51cd\4\63\0\u048d\4\103\0\56\4"+
    "\2\0\u010d\4\3\0\20\4\12\5\2\4\24\0\57\4\20\0\37\4"+
    "\2\0\106\4\61\0\11\4\2\0\147\4\2\0\4\4\1\0\36\4"+
    "\2\0\2\4\105\0\13\4\1\0\3\4\1\0\4\4\1\0\27\4"+
    "\35\0\64\4\16\0\62\4\34\0\12\5\30\0\6\4\3\0\1\4"+
    "\4\0\12\5\34\4\12\0\27\4\31\0\35\4\7\0\57\4\34\0"+
    "\1\4\12\5\6\0\5\4\1\0\12\4\12\5\5\4\1\0\51\4"+
    "\27\0\3\4\1\0\10\4\4\0\12\5\6\0\27\4\3\0\1\4"+
    "\3\0\62\4\1\0\1\4\3\0\2\4\2\0\5\4\2\0\1\4"+
    "\1\0\1\4\30\0\3\4\2\0\13\4\7\0\3\4\14\0\6\4"+
    "\2\0\6\4\2\0\6\4\11\0\7\4\1\0\7\4\1\0\53\4"+
    "\1\0\4\4\4\0\2\4\132\0\43\4\15\0\12\5\6\0\u2ba4\4"+
    "\14\0\27\4\4\0\61\4\u2104\0\u016e\4\2\0\152\4\46\0\7\4"+
    "\14\0\5\4\5\0\1\4\1\0\12\4\1\0\15\4\1\0\5\4"+
    "\1\0\1\4\1\0\2\4\1\0\2\4\1\0\154\4\41\0\u016b\4"+
    "\22\0\100\4\2\0\66\4\50\0\14\4\164\0\5\4\1\0\207\4"+
    "\23\0\12\5\7\0\32\4\6\0\32\4\13\0\131\4\3\0\6\4"+
    "\2\0\6\4\2\0\6\4\2\0\3\4\43\0\14\4\1\0\32\4"+
    "\1\0\23\4\1\0\2\4\1\0\17\4\2\0\16\4\42\0\173\4"+
    "\u0185\0\35\4\3\0\61\4\57\0\40\4\20\0\21\4\1\0\10\4"+
    "\6\0\46\4\12\0\36\4\2\0\44\4\4\0\10\4\60\0\236\4"+
    "\2\0\12\5\126\0\50\4\10\0\64\4\234\0\u0137\4\11\0\26\4"+
    "\12\0\10\4\230\0\6\4\2\0\1\4\1\0\54\4\1\0\2\4"+
    "\3\0\1\4\2\0\27\4\12\0\27\4\11\0\37\4\141\0\26\4"+
    "\12\0\32\4\106\0\70\4\6\0\2\4\100\0\1\4\17\0\4\4"+
    "\1\0\3\4\1\0\33\4\54\0\35\4\3\0\35\4\43\0\10\4"+
    "\1\0\34\4\33\0\66\4\12\0\26\4\12\0\23\4\15\0\22\4"+
    "\156\0\111\4\u03ba\0\65\4\56\0\12\5\23\0\55\4\40\0\31\4"+
    "\7\0\12\5\11\0\44\4\17\0\12\5\20\0\43\4\3\0\1\4"+
    "\14\0\60\4\16\0\4\4\13\0\12\5\1\4\45\0\22\4\1\0"+
    "\31\4\204\0\57\4\21\0\12\5\13\0\10\4\2\0\2\4\2\0"+
    "\26\4\1\0\7\4\1\0\2\4\1\0\5\4\3\0\1\4\37\0"+
    "\5\4\u011e\0\60\4\24\0\2\4\1\0\1\4\10\0\12\5\246\0"+
    "\57\4\121\0\60\4\24\0\1\4\13\0\12\5\46\0\53\4\25\0"+
    "\12\5\u01d6\0\100\4\12\5\25\0\1\4\u01c0\0\71\4\u0507\0\u0399\4"+
    "\u0c67\0\u042f\4\u33d1\0\u0239\4\7\0\37\4\1\0\12\5\146\0\36\4"+
    "\22\0\60\4\20\0\4\4\14\0\12\5\11\0\25\4\5\0\23\4"+
    "\u0370\0\105\4\13\0\1\4\102\0\15\4\u4060\0\2\4\u0bfe\0\153\4"+
    "\5\0\15\4\3\0\11\4\7\0\12\4\u1766\0\125\4\1\0\107\4"+
    "\1\0\2\4\2\0\1\4\2\0\2\4\2\0\4\4\1\0\14\4"+
    "\1\0\1\4\1\0\7\4\1\0\101\4\1\0\4\4\2\0\10\4"+
    "\1\0\7\4\1\0\34\4\1\0\4\4\1\0\5\4\1\0\1\4"+
    "\3\0\7\4\1\0\u0154\4\2\0\31\4\1\0\31\4\1\0\37\4"+
    "\1\0\31\4\1\0\37\4\1\0\31\4\1\0\37\4\1\0\31\4"+
    "\1\0\37\4\1\0\31\4\1\0\10\4\2\0\62\5\u1000\0\305\4"+
    "\u053b\0\4\4\1\0\33\4\1\0\2\4\1\0\1\4\2\0\1\4"+
    "\1\0\12\4\1\0\4\4\1\0\1\4\1\0\1\4\6\0\1\4"+
    "\4\0\1\4\1\0\1\4\1\0\1\4\1\0\3\4\1\0\2\4"+
    "\1\0\1\4\2\0\1\4\1\0\1\4\1\0\1\4\1\0\1\4"+
    "\1\0\1\4\1\0\2\4\1\0\1\4\2\0\4\4\1\0\7\4"+
    "\1\0\4\4\1\0\4\4\1\0\1\4\1\0\12\4\1\0\21\4"+
    "\5\0\3\4\1\0\5\4\1\0\21\4\u1144\0\ua6d7\4\51\0\u1035\4"+
    "\13\0\336\4\u3fe2\0\u021e\4\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u05f0\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\4\1\5\2\6\7\4"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\1\33\1\34\1\1\1\35"+
    "\1\1\1\36\1\1\1\0\2\4\1\37\5\4\1\40"+
    "\1\41\1\42\1\43\1\0\1\44\1\45\1\46\1\47"+
    "\2\44\1\50\5\44\2\4\1\51\4\4\1\52\3\0"+
    "\1\53\1\54\1\55\3\0\1\56\1\57\1\4\1\60"+
    "\2\4\1\33\1\61\1\62\1\63\1\64\1\4\1\65"+
    "\1\66";

  private static int [] zzUnpackAction() {
    int [] result = new int[101];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\64\0\150\0\234\0\234\0\320\0\u0104\0\234"+
    "\0\234\0\u0138\0\u016c\0\u01a0\0\u01d4\0\u0208\0\u023c\0\u0270"+
    "\0\u02a4\0\234\0\234\0\234\0\234\0\234\0\234\0\234"+
    "\0\234\0\u02d8\0\u030c\0\u0340\0\u0374\0\234\0\234\0\234"+
    "\0\u03a8\0\234\0\234\0\234\0\234\0\u03dc\0\234\0\u0410"+
    "\0\234\0\u0444\0\234\0\u0478\0\u04ac\0\u04e0\0\u0514\0\u0104"+
    "\0\u0548\0\u057c\0\u05b0\0\u05e4\0\u0618\0\234\0\234\0\234"+
    "\0\234\0\u064c\0\234\0\234\0\234\0\234\0\u0680\0\u06b4"+
    "\0\234\0\u06e8\0\u071c\0\u0750\0\u0784\0\u07b8\0\u07ec\0\u0820"+
    "\0\u0104\0\u0854\0\u0888\0\u08bc\0\u08f0\0\234\0\u0924\0\u0958"+
    "\0\u098c\0\234\0\234\0\234\0\u09c0\0\u09f4\0\u0a28\0\u0104"+
    "\0\u0104\0\u0a5c\0\u0104\0\u0a90\0\u0ac4\0\234\0\234\0\234"+
    "\0\234\0\u0104\0\u0af8\0\u0104\0\u0104";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[101];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\2\5\1\6\1\7\1\4\1\10\1\11\1\12"+
    "\1\7\2\12\1\7\1\4\1\13\2\7\1\14\1\7"+
    "\1\15\1\16\1\7\1\17\1\20\1\7\1\21\2\7"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\45\2\7\1\4\1\5\1\46"+
    "\1\47\4\46\1\4\6\46\1\50\41\46\1\51\4\46"+
    "\1\52\1\53\4\52\1\4\6\52\1\54\41\52\1\4"+
    "\4\52\67\0\1\55\64\0\11\7\1\0\16\7\24\0"+
    "\2\7\11\0\2\12\1\0\2\12\54\0\11\7\1\0"+
    "\1\7\1\56\14\7\24\0\2\7\6\0\11\7\1\0"+
    "\2\7\1\57\13\7\24\0\2\7\6\0\11\7\1\0"+
    "\6\7\1\60\1\61\6\7\24\0\2\7\6\0\11\7"+
    "\1\0\15\7\1\62\24\0\2\7\6\0\11\7\1\0"+
    "\11\7\1\63\4\7\24\0\2\7\6\0\11\7\1\0"+
    "\3\7\1\64\12\7\24\0\2\7\6\0\11\7\1\0"+
    "\14\7\1\65\1\7\24\0\2\7\46\0\1\66\63\0"+
    "\1\67\63\0\1\70\63\0\1\71\64\0\1\72\16\0"+
    "\1\46\1\0\4\46\1\0\6\46\1\0\41\46\1\0"+
    "\4\46\1\73\1\0\4\73\1\74\6\73\1\75\7\73"+
    "\1\76\32\73\1\77\1\100\10\0\1\101\55\0\1\73"+
    "\1\0\4\73\1\102\6\73\1\103\7\73\1\104\32\73"+
    "\1\105\1\106\2\0\1\55\1\5\62\55\4\0\11\7"+
    "\1\0\1\7\1\107\14\7\24\0\2\7\6\0\11\7"+
    "\1\0\4\7\1\110\11\7\24\0\2\7\6\0\11\7"+
    "\1\0\10\7\1\111\5\7\24\0\2\7\6\0\11\7"+
    "\1\0\2\7\1\112\13\7\24\0\2\7\6\0\11\7"+
    "\1\0\12\7\1\113\3\7\24\0\2\7\6\0\11\7"+
    "\1\0\10\7\1\114\5\7\24\0\2\7\6\0\11\7"+
    "\1\0\5\7\1\115\10\7\24\0\2\7\47\0\1\116"+
    "\25\0\3\117\1\120\1\121\1\117\56\0\6\117\55\0"+
    "\1\122\63\0\1\123\63\0\1\124\64\0\3\125\1\126"+
    "\1\127\1\125\56\0\6\125\53\0\11\7\1\0\2\7"+
    "\1\130\13\7\24\0\2\7\6\0\11\7\1\0\3\7"+
    "\1\131\12\7\24\0\2\7\6\0\11\7\1\0\4\7"+
    "\1\132\11\7\24\0\2\7\6\0\11\7\1\0\3\7"+
    "\1\133\12\7\24\0\2\7\6\0\11\7\1\0\12\7"+
    "\1\134\3\7\24\0\2\7\6\0\11\7\1\0\2\7"+
    "\1\135\13\7\24\0\2\7\11\0\6\136\56\0\6\137"+
    "\56\0\2\137\1\136\3\137\56\0\6\140\56\0\6\141"+
    "\56\0\2\141\1\140\3\141\53\0\11\7\1\0\3\7"+
    "\1\142\12\7\24\0\2\7\6\0\11\7\1\0\11\7"+
    "\1\143\4\7\24\0\2\7\6\0\11\7\1\0\3\7"+
    "\1\144\12\7\24\0\2\7\6\0\11\7\1\0\7\7"+
    "\1\145\6\7\24\0\2\7\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2860];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\2\11\2\1\2\11\10\1\10\11\4\1\3\11"+
    "\1\1\4\11\1\1\1\11\1\1\1\11\1\1\1\11"+
    "\1\1\1\0\10\1\4\11\1\0\4\11\2\1\1\11"+
    "\14\1\1\11\3\0\3\11\3\0\6\1\4\11\4\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[101];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
  StringBuilder string = new StringBuilder();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
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


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2478) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
              {
                return symbol(sym.EOF);
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn);
            }
          case 55: break;
          case 2: 
            { /* ignore */
            }
          case 56: break;
          case 3: 
            { return symbol(sym.DIV);
            }
          case 57: break;
          case 4: 
            { return symbol(sym.IDENTIFIER, yytext());
            }
          case 58: break;
          case 5: 
            { yybegin(CHARLITERAL);
            }
          case 59: break;
          case 6: 
            { return symbol(sym.INTEGER_LITERAL, new Integer(yytext()));
            }
          case 60: break;
          case 7: 
            { return symbol(sym.LPAREN);
            }
          case 61: break;
          case 8: 
            { return symbol(sym.RPAREN);
            }
          case 62: break;
          case 9: 
            { return symbol(sym.LBRACE);
            }
          case 63: break;
          case 10: 
            { return symbol(sym.RBRACE);
            }
          case 64: break;
          case 11: 
            { return symbol(sym.LBRACK);
            }
          case 65: break;
          case 12: 
            { return symbol(sym.RBRACK);
            }
          case 66: break;
          case 13: 
            { return symbol(sym.SEMICOLON);
            }
          case 67: break;
          case 14: 
            { return symbol(sym.COMMA);
            }
          case 68: break;
          case 15: 
            { return symbol(sym.EQ);
            }
          case 69: break;
          case 16: 
            { return symbol(sym.GT);
            }
          case 70: break;
          case 17: 
            { return symbol(sym.LT);
            }
          case 71: break;
          case 18: 
            { return symbol(sym.NOT);
            }
          case 72: break;
          case 19: 
            { return symbol(sym.COLON);
            }
          case 73: break;
          case 20: 
            { return symbol(sym.PLUS);
            }
          case 74: break;
          case 21: 
            { return symbol(sym.MINUS);
            }
          case 75: break;
          case 22: 
            { return symbol(sym.MULT);
            }
          case 76: break;
          case 23: 
            { return symbol(sym.AND);
            }
          case 77: break;
          case 24: 
            { return symbol(sym.OR);
            }
          case 78: break;
          case 25: 
            { return symbol(sym.MOD);
            }
          case 79: break;
          case 26: 
            { yybegin(STRING); string.setLength(0);
            }
          case 80: break;
          case 27: 
            { string.append( yytext() );
            }
          case 81: break;
          case 28: 
            { throw new RuntimeException("Unterminated string at end of line");
            }
          case 82: break;
          case 29: 
            { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, string.toString());
            }
          case 83: break;
          case 30: 
            { throw new RuntimeException("Unterminated character literal at end of line");
            }
          case 84: break;
          case 31: 
            { return symbol(sym.IF);
            }
          case 85: break;
          case 32: 
            { return symbol(sym.EQEQ);
            }
          case 86: break;
          case 33: 
            { return symbol(sym.GTEQ);
            }
          case 87: break;
          case 34: 
            { return symbol(sym.LTEQ);
            }
          case 88: break;
          case 35: 
            { return symbol(sym.NOTEQ);
            }
          case 89: break;
          case 36: 
            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\"");
            }
          case 90: break;
          case 37: 
            { string.append( '\'' );
            }
          case 91: break;
          case 38: 
            { string.append( '\\' );
            }
          case 92: break;
          case 39: 
            { string.append( '\n' );
            }
          case 93: break;
          case 40: 
            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, yytext().charAt(0));
            }
          case 94: break;
          case 41: 
            { return symbol(sym.INT);
            }
          case 95: break;
          case 42: 
            { return symbol(sym.HIGHMULT);
            }
          case 96: break;
          case 43: 
            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\'');
            }
          case 97: break;
          case 44: 
            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\\');
            }
          case 98: break;
          case 45: 
            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, '\n');
            }
          case 99: break;
          case 46: 
            { return symbol(sym.BOOLEAN);
            }
          case 100: break;
          case 47: 
            { return symbol(sym.ELSE);
            }
          case 101: break;
          case 48: 
            { return symbol(sym.BOOLEAN_LITERAL, true);
            }
          case 102: break;
          case 49: 
            { string.append( parseHex(yytext()) );
            }
          case 103: break;
          case 50: 
            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, yytext());
            }
          case 104: break;
          case 51: 
            { yybegin(YYINITIAL); return symbol(sym.CHARACTER_LITERAL, parseHex(yytext()));
            }
          case 105: break;
          case 52: 
            { return symbol(sym.BOOLEAN_LITERAL, false);
            }
          case 106: break;
          case 53: 
            { return symbol(sym.WHILE);
            }
          case 107: break;
          case 54: 
            { return symbol(sym.RETURN);
            }
          case 108: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Converts an int token code into the name of the
   * token by reflection on the cup symbol class/interface sym
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  private String getTokenName(int token) {
    try {
      java.lang.reflect.Field [] classFields = sym.class.getFields();
      for (int i = 0; i < classFields.length; i++) {
        if (classFields[i].getInt(null) == token) {
          return classFields[i].getName();
        }
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    return "UNKNOWN TOKEN";
  }

  /**
   * Same as next_token but also prints the token to standard out
   * for debugging.
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  public java_cup.runtime.Symbol debug_next_token() throws java.io.IOException {
    java_cup.runtime.Symbol s = next_token();
    System.out.println( "line:" + (yyline+1) + " col:" + (yycolumn+1) + " --"+ yytext() + "--" + getTokenName(s.sym) + "--");
    return s;
  }

  /**
   * Runs the scanner on input files.
   *
   * This main method is the debugging routine for the scanner.
   * It prints debugging information about each returned token to
   * System.out until the end of file is reached, or an error occured.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java Scanner [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          java.nio.charset.Charset.forName(encodingName); // Side-effect: is encodingName valid? 
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        Scanner scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new Scanner(reader);
          while ( !scanner.zzAtEOF ) scanner.debug_next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
