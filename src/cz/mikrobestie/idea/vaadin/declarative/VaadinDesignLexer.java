/* The following code was generated by JFlex 1.4.3 on 22.4.16 21:41 */

package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 22.4.16 21:41 from the specification file
 * <tt>C:/DEV/GIT/vaadin-declarative-plugin/src/cz/mikrobestie/idea/vaadin/declarative/design.flex</tt>
 */
public class VaadinDesignLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int IN_TAG_NAME_CLOSE = 4;
  public static final int IN_TAG_ATTRS = 6;
  public static final int IN_COMMENT = 8;
  public static final int IN_TAG_NAME = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4, 4
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\1\1\37\1\0\2\1\22\0\1\36\1\26\1\6\4\0"+
    "\1\7\5\0\1\3\1\0\1\12\12\2\1\5\1\0\1\10\1\13"+
    "\1\11\4\0\1\31\1\27\1\35\11\0\1\30\1\34\3\0\1\32"+
    "\4\0\1\33\5\0\1\5\1\0\1\21\1\23\1\4\1\22\1\20"+
    "\2\4\1\14\3\4\1\17\1\16\1\4\1\24\4\4\1\15\4\4"+
    "\1\25\1\4\uff85\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\0\1\2\1\3\1\4\1\5\1\3"+
    "\1\6\1\7\3\6\1\1\2\10\1\7\1\5\1\11"+
    "\3\12\1\13\2\0\4\6\1\10\1\14\1\15\3\0"+
    "\4\6\1\16\1\17\1\0\1\20\1\21\1\22\1\23"+
    "\12\0\1\24";

  private static int [] zzUnpackAction() {
    int [] result = new int[59];
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
    "\0\0\0\40\0\100\0\140\0\200\0\240\0\300\0\340"+
    "\0\u0100\0\u0120\0\u0140\0\u0100\0\u0160\0\u0180\0\u01a0\0\u01c0"+
    "\0\u01e0\0\u0200\0\u0220\0\u0240\0\u0100\0\u0260\0\u0280\0\u0100"+
    "\0\u0100\0\u02a0\0\u02c0\0\u02e0\0\u0300\0\u0320\0\u0340\0\u0100"+
    "\0\u0100\0\u0100\0\u0360\0\u0380\0\u03a0\0\u03c0\0\u03e0\0\u0400"+
    "\0\u0420\0\u0100\0\u0100\0\u0440\0\u0140\0\u0140\0\u0140\0\u0140"+
    "\0\u0460\0\u0480\0\u04a0\0\u04c0\0\u04e0\0\u0500\0\u0520\0\u0540"+
    "\0\u0560\0\u0580\0\u0100";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[59];
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
    "\1\6\1\7\6\6\1\10\1\11\24\6\2\7\1\11"+
    "\1\12\1\13\1\11\1\13\4\11\1\14\2\11\1\15"+
    "\1\13\1\16\4\13\1\17\2\13\10\11\2\12\1\11"+
    "\1\12\1\13\1\11\1\13\7\11\1\15\1\13\1\16"+
    "\4\13\1\17\2\13\10\11\2\12\1\11\1\12\1\11"+
    "\3\20\1\21\1\22\1\23\1\14\1\24\1\25\12\20"+
    "\10\11\2\12\3\26\1\27\33\26\1\30\10\6\2\0"+
    "\27\6\1\7\6\6\2\0\24\6\2\7\12\0\1\31"+
    "\13\0\1\32\52\0\1\12\34\0\2\12\2\0\1\13"+
    "\1\33\1\13\7\0\12\13\14\0\1\13\1\33\1\13"+
    "\7\0\1\13\1\34\2\13\1\35\5\13\14\0\1\13"+
    "\1\33\1\13\7\0\4\13\1\36\5\13\14\0\1\13"+
    "\1\33\1\13\7\0\10\13\1\37\1\13\15\0\2\20"+
    "\7\0\12\20\12\0\6\21\1\40\31\21\7\22\1\40"+
    "\30\22\12\0\1\41\36\0\1\42\26\0\3\26\1\0"+
    "\33\26\4\0\1\43\37\0\1\44\23\0\1\45\14\0"+
    "\1\13\7\0\12\13\14\0\1\13\1\33\1\13\7\0"+
    "\2\13\1\46\7\13\14\0\1\13\1\33\1\13\7\0"+
    "\5\13\1\47\4\13\14\0\1\13\1\33\1\13\7\0"+
    "\1\13\1\50\10\13\14\0\1\13\1\33\1\13\7\0"+
    "\6\13\1\51\3\13\23\0\1\52\31\0\1\53\64\0"+
    "\1\54\11\0\1\13\1\33\1\13\7\0\3\13\1\55"+
    "\6\13\14\0\1\13\1\33\1\13\7\0\6\13\1\56"+
    "\3\13\14\0\1\13\1\33\1\13\7\0\5\13\1\57"+
    "\4\13\14\0\1\13\1\33\1\13\7\0\11\13\1\60"+
    "\43\0\1\61\40\0\1\62\40\0\1\63\40\0\1\64"+
    "\40\0\1\65\40\0\1\66\15\0\1\67\40\0\1\70"+
    "\40\0\1\71\40\0\1\72\31\0\1\73\26\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1440];
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
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  private static java.io.Reader zzReader = null; // Fake

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\1\1\0\3\1\1\11\2\1\1\11\10\1"+
    "\1\11\2\1\2\11\2\0\4\1\3\11\3\0\4\1"+
    "\2\11\1\0\4\1\12\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[59];
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

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */

  private int commentStart = -1;

  public VaadinDesignLexer() {
    this(null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public VaadinDesignLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 106) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
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
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
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
    return zzBufferArray != null ? zzBufferArray[zzStartRead+pos]:zzBuffer.charAt(zzStartRead+pos);
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
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char[] zzBufferArrayL = zzBufferArray;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
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
              zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 18: 
          { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_META;
          }
        case 21: break;
        case 5: 
          { return TokenType.BAD_CHARACTER;
          }
        case 22: break;
        case 19: 
          { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_BODY;
          }
        case 23: break;
        case 12: 
          { yybegin(YYINITIAL); return VDTypes.EL_CLOSE_LEFT;
          }
        case 24: break;
        case 16: 
          { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_HTML;
          }
        case 25: break;
        case 20: 
          { return VDTypes.DOCTYPE_DECL;
          }
        case 26: break;
        case 14: 
          { yybegin(YYINITIAL); zzStartRead = commentStart; return VDTypes.COMMENT;
          }
        case 27: break;
        case 1: 
          { return VDTypes.ATTR_NAME;
          }
        case 28: break;
        case 3: 
          { return TokenType.WHITE_SPACE;
          }
        case 29: break;
        case 8: 
          { return VDTypes.ATTR_VALUE;
          }
        case 30: break;
        case 17: 
          { yybegin(IN_TAG_ATTRS); return VDTypes.TAG_HEAD;
          }
        case 31: break;
        case 2: 
          { return VDTypes.TEXT;
          }
        case 32: break;
        case 4: 
          { yybegin(IN_TAG_NAME); return VDTypes.EL_LEFT;
          }
        case 33: break;
        case 13: 
          { yybegin(YYINITIAL); return VDTypes.EL_CLOSE_RIGHT;
          }
        case 34: break;
        case 7: 
          { yybegin(YYINITIAL); return VDTypes.EL_RIGHT;
          }
        case 35: break;
        case 11: 
          { yybegin(IN_TAG_NAME_CLOSE); return VDTypes.EL_CLOSE_LEFT;
          }
        case 36: break;
        case 15: 
          { yybegin(IN_COMMENT); commentStart = zzStartRead;
          }
        case 37: break;
        case 9: 
          { return VDTypes.EQ;
          }
        case 38: break;
        case 6: 
          { yybegin(IN_TAG_ATTRS); return VDTypes.COMPONENT_NAME;
          }
        case 39: break;
        case 10: 
          { 
          }
        case 40: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
