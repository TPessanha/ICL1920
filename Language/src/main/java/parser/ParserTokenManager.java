/* ParserTokenManager.java */
/* Generated by: ParserGeneratorCC: Do not edit this line. ParserTokenManager.java */
package parser;

/** Token Manager. */
@SuppressWarnings ("unused")
public class ParserTokenManager implements ParserConstants {
private final int jjStopStringLiteralDfa_0(int pos, long active0){
   switch (pos)
   {
      case 0:
         if ((active0 & 0x1000L) != 0x0L)
            return 3;
         if ((active0 & 0xffe00000000L) != 0x0L)
         {
            jjmatchedKind = 46;
            return 1;
         }
         return -1;
      case 1:
         if ((active0 & 0x24800000000L) != 0x0L)
            return 1;
         if ((active0 & 0xdb600000000L) != 0x0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 46;
               jjmatchedPos = 1;
            }
            return 1;
         }
         return -1;
      case 2:
         if ((active0 & 0x14600000000L) != 0x0L)
            return 1;
         if ((active0 & 0xcb000000000L) != 0x0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 2;
            return 1;
         }
         return -1;
      case 3:
         if ((active0 & 0xca000000000L) != 0x0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 3;
            return 1;
         }
         if ((active0 & 0x1000000000L) != 0x0L)
            return 1;
         return -1;
      case 4:
         if ((active0 & 0xca000000000L) != 0x0L)
            return 1;
         return -1;
      case 5:
         if ((active0 & 0x40000000000L) != 0x0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 5;
            return 1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case '!':
         jjmatchedKind = 13;
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case '&':
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case '(':
         return jjStopAtPos(0, 29);
      case ')':
         return jjStopAtPos(0, 30);
      case '*':
         return jjStopAtPos(0, 11);
      case '+':
         return jjStopAtPos(0, 9);
      case ',':
         return jjStopAtPos(0, 8);
      case '-':
         jjmatchedKind = 10;
         return jjMoveStringLiteralDfa1_0(0x2000000L);
      case '.':
         return jjStopAtPos(0, 7);
      case '/':
         return jjStartNfaWithStates_0(0, 12, 3);
      case ':':
         jjmatchedKind = 28;
         return jjMoveStringLiteralDfa1_0(0x40000L);
      case ';':
         jjmatchedKind = 26;
         return jjMoveStringLiteralDfa1_0(0x8000000L);
      case '<':
         jjmatchedKind = 22;
         return jjMoveStringLiteralDfa1_0(0x1000000L);
      case '=':
         jjmatchedKind = 17;
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case '>':
         jjmatchedKind = 21;
         return jjMoveStringLiteralDfa1_0(0x800000L);
      case 'a':
         return jjMoveStringLiteralDfa1_0(0x20000000000L);
      case 'e':
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 'f':
         return jjMoveStringLiteralDfa1_0(0xa000000000L);
      case 'i':
         return jjMoveStringLiteralDfa1_0(0x4800000000L);
      case 'l':
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 'n':
         return jjMoveStringLiteralDfa1_0(0x10000000000L);
      case 'p':
         return jjMoveStringLiteralDfa1_0(0xc0000000000L);
      case 't':
         return jjMoveStringLiteralDfa1_0(0x1000000000L);
      case '{':
         return jjStopAtPos(0, 31);
      case '|':
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case '}':
         return jjStopAtPos(0, 32);
      case '~':
         return jjStopAtPos(0, 14);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case '&':
         if ((active0 & 0x8000L) != 0x0L)
            return jjStopAtPos(1, 15);
         break;
      case ';':
         if ((active0 & 0x8000000L) != 0x0L)
            return jjStopAtPos(1, 27);
         break;
      case '=':
         if ((active0 & 0x40000L) != 0x0L)
            return jjStopAtPos(1, 18);
         else if ((active0 & 0x80000L) != 0x0L)
            return jjStopAtPos(1, 19);
         else if ((active0 & 0x100000L) != 0x0L)
            return jjStopAtPos(1, 20);
         else if ((active0 & 0x800000L) != 0x0L)
            return jjStopAtPos(1, 23);
         else if ((active0 & 0x1000000L) != 0x0L)
            return jjStopAtPos(1, 24);
         break;
      case '>':
         if ((active0 & 0x2000000L) != 0x0L)
            return jjStopAtPos(1, 25);
         break;
      case 'a':
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000000L);
      case 'e':
         return jjMoveStringLiteralDfa2_0(active0, 0x10200000000L);
      case 'l':
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000000L);
      case 'n':
         if ((active0 & 0x800000000L) != 0x0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x4400000000L);
      case 'r':
         return jjMoveStringLiteralDfa2_0(active0, 0xc1000000000L);
      case 's':
         if ((active0 & 0x20000000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 41, 1);
         break;
      case '|':
         if ((active0 & 0x10000L) != 0x0L)
            return jjStopAtPos(1, 16);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 'd':
         if ((active0 & 0x400000000L) != 0x0L)
            return jjStartNfaWithStates_0(2, 34, 1);
         break;
      case 'i':
         return jjMoveStringLiteralDfa3_0(active0, 0xc0000000000L);
      case 'l':
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000000L);
      case 'o':
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000000L);
      case 't':
         if ((active0 & 0x200000000L) != 0x0L)
            return jjStartNfaWithStates_0(2, 33, 1);
         else if ((active0 & 0x4000000000L) != 0x0L)
            return jjStartNfaWithStates_0(2, 38, 1);
         break;
      case 'u':
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L);
      case 'w':
         if ((active0 & 0x10000000000L) != 0x0L)
            return jjStartNfaWithStates_0(2, 40, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 'a':
         return jjMoveStringLiteralDfa4_0(active0, 0x8000000000L);
      case 'e':
         if ((active0 & 0x1000000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 36, 1);
         break;
      case 'n':
         return jjMoveStringLiteralDfa4_0(active0, 0xc0000000000L);
      case 's':
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 'e':
         if ((active0 & 0x2000000000L) != 0x0L)
            return jjStartNfaWithStates_0(4, 37, 1);
         break;
      case 't':
         if ((active0 & 0x8000000000L) != 0x0L)
            return jjStartNfaWithStates_0(4, 39, 1);
         else if ((active0 & 0x80000000000L) != 0x0L)
         {
            jjmatchedKind = 43;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 'l':
         return jjMoveStringLiteralDfa6_0(active0, 0x40000000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 'n':
         if ((active0 & 0x40000000000L) != 0x0L)
            return jjStartNfaWithStates_0(6, 42, 1);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 19;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if (curChar == 42)
                     { jjCheckNAddTwoStates(9, 10); }
                  else if (curChar == 47)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0x0L)
                  {
                     if (kind > 44)
                        kind = 44;
                     { jjCheckNAddStates(3, 5); }
                  }
                  else if (curChar == 47)
                     { jjAddStates(6, 7); }
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 2:
                  if (curChar == 47)
                     { jjAddStates(6, 7); }
                  break;
               case 4:
                  if ((0xffffffffffffdbffL & l) != 0x0L)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 5:
                  if ((0x2400L & l) != 0x0L && kind > 5)
                     kind = 5;
                  break;
               case 6:
                  if (curChar == 10 && kind > 5)
                     kind = 5;
                  break;
               case 7:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 8:
                  if (curChar == 42)
                     { jjCheckNAddTwoStates(9, 10); }
                  break;
               case 9:
                  if ((0xfffffbffffffffffL & l) != 0x0L)
                     { jjCheckNAddTwoStates(9, 10); }
                  break;
               case 10:
                  if (curChar == 42)
                     { jjCheckNAddStates(8, 10); }
                  break;
               case 11:
                  if ((0xffff7bffffffffffL & l) != 0x0L)
                     { jjCheckNAddTwoStates(12, 10); }
                  break;
               case 12:
                  if ((0xfffffbffffffffffL & l) != 0x0L)
                     { jjCheckNAddTwoStates(12, 10); }
                  break;
               case 13:
                  if (curChar == 47 && kind > 6)
                     kind = 6;
                  break;
               case 14:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  { jjCheckNAddStates(3, 5); }
                  break;
               case 15:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 44)
                     kind = 44;
                  { jjCheckNAdd(15); }
                  break;
               case 16:
                  if ((0x3ff000000000000L & l) != 0x0L)
                     { jjCheckNAddTwoStates(16, 17); }
                  break;
               case 17:
                  if (curChar == 46)
                     { jjCheckNAdd(18); }
                  break;
               case 18:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 45)
                     kind = 45;
                  { jjCheckNAdd(18); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 1:
                  if ((0x7fffffe87fffffeL & l) == 0x0L)
                     break;
                  if (kind > 46)
                     kind = 46;
                  { jjCheckNAdd(1); }
                  break;
               case 4:
                  { jjAddStates(0, 2); }
                  break;
               case 9:
                  { jjCheckNAddTwoStates(9, 10); }
                  break;
               case 11:
               case 12:
                  { jjCheckNAddTwoStates(12, 10); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjAddStates(0, 2); }
                  break;
               case 9:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjCheckNAddTwoStates(9, 10); }
                  break;
               case 11:
               case 12:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjCheckNAddTwoStates(12, 10); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      i = jjnewStateCnt;
      jjnewStateCnt = startsAt;
      startsAt = 19 - jjnewStateCnt;
      if (i == startsAt)
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, "\56", "\54", "\53", "\55", "\52",
"\57", "\41", "\176", "\46\46", "\174\174", "\75", "\72\75", "\75\75", "\41\75",
"\76", "\74", "\76\75", "\74\75", "\55\76", "\73", "\73\73", "\72", "\50", "\51",
"\173", "\175", "\154\145\164", "\145\156\144", "\151\156", "\164\162\165\145",
"\146\141\154\163\145", "\151\156\164", "\146\154\157\141\164", "\156\145\167", "\141\163",
"\160\162\151\156\164\154\156", "\160\162\151\156\164", null, null, null, null, null, };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = im == null ? input_stream.getImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   t.image = curTokenImage;

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}
static final int[] jjnextStates = {
   4, 5, 7, 15, 16, 17, 3, 8, 10, 11, 13,
};

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken()
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop:
  for (;;)
  {
   try
   {
      curChar = input_stream.beginToken();
   }
   catch(final Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try {
     input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0x0L)
         curChar = input_stream.beginToken();
   }
   catch (final java.io.IOException e1) {
     continue EOFLoop;
   }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try {
     input_stream.readChar();
     input_stream.backup(1);
   }
   catch (final java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.getImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.getImage();
   }
   throw new TokenMgrException(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrException.LEXICAL_ERROR);
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public ParserTokenManager(SimpleCharStream stream){
    input_stream = stream;
  }

  /** Constructor. */
  public ParserTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */

  public void ReInit(SimpleCharStream stream)
  {


    jjmatchedPos =
    jjnewStateCnt =
    0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 19; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream, int lexState)
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 1 || lexState < 0)
      throw new TokenMgrException("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrException.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }


/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
};
static final long[] jjtoToken = {
   0x7fffffffff81L,
};
static final long[] jjtoSkip = {
   0x7eL,
};
static final long[] jjtoSpecial = {
   0x0L,
};
static final long[] jjtoMore = {
   0x0L,
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[19];
    private final int[] jjstateSet = new int[2 * 19];
    private final StringBuilder jjimage = new StringBuilder();
    private StringBuilder image = jjimage;
    private int jjimageLen;
    private int lengthOfMatch;
    protected int curChar;
}
