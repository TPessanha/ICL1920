/* Parser.java */
/* Generated by: ParserGeneratorCC: Do not edit this line. Parser.java */
package parser;

import nodes.*;
import nodes.arithmetic.ASTAddition;
import nodes.arithmetic.ASTDivision;
import nodes.arithmetic.ASTMultiplication;
import nodes.arithmetic.ASTSubtraction;
import nodes.logic.ASTAnd;
import nodes.logic.ASTNot;
import nodes.logic.ASTOr;
import nodes.primitives.ASTBoolean;
import nodes.primitives.ASTFloat;
import nodes.primitives.ASTInteger;
import nodes.relation.*;
import state.Binding;

import java.util.ArrayList;
import java.util.List;

public class Parser implements ParserConstants {

  final public ASTNode Start() throws ParseException {//	List<ASTNode> nodes = new ArrayList<>();
 ASTNode node;
    //   (node = Statment() ((<STAT_ENDS> | <EOF>) Statment())* (<EOF>)?
    //   {return node;}
    //   )?
        node = Statment();
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTNode Statment() throws ParseException {ASTNode node;
    switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
    case PRINTLN:{
      node = Println();
      jj_consume_token(SEMICOLON);
      break;
      }
    case SUB:
    case TILDE:
    case LPAR:
    case LCUR:
    case LET:
    case TRUE:
    case FALSE:
    case INTEGER_LITERAL:
    case FLOAT_LITERAL:
    case IDENTIFIER:{
      node = Expression();
      jj_consume_token(SEMICOLON);
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

// EXPERIMENTAL -==-----------------------------------
  final public ASTExpression Expression() throws ParseException {ASTExpression node;
    node = Disjunction();
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Disjunction() throws ParseException {ASTExpression node,node2;
    node = Conjunction();
    label_1:
    while (true) {
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case DISJ:{
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      jj_consume_token(DISJ);
      node2 = Conjunction();
node = new ASTOr(node,node2);
    }
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Conjunction() throws ParseException {ASTExpression node,node2;
    node = Equality();
    label_2:
    while (true) {
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case CONJ:{
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(CONJ);
      node2 = Equality();
node = new ASTAnd(node,node2);
    }
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Equality() throws ParseException {Token op;
    ASTExpression node,node2;
    node = Comparison();
    label_3:
    while (true) {
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case EQEQ:
      case EXCL_EQUALS:{
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case EQEQ:{
        op = jj_consume_token(EQEQ);
        break;
        }
      case EXCL_EQUALS:{
        op = jj_consume_token(EXCL_EQUALS);
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      node2 = Comparison();
switch(op.kind)
      {
       case EQEQ:
        node = new ASTEqual(node,node2);
        break;
          case EXCL_EQUALS:
           node = new ASTNotEqual(node,node2);
           break;
      }
    }
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Comparison() throws ParseException {Token op;
    ASTExpression node,node2;
    node = addSubExpression();
    switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
    case GREATER:
    case LESS:
    case GR_EQUALS:
    case LE_EQUALS:{
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case GREATER:{
        op = jj_consume_token(GREATER);
        break;
        }
      case LESS:{
        op = jj_consume_token(LESS);
        break;
        }
      case GR_EQUALS:{
        op = jj_consume_token(GR_EQUALS);
        break;
        }
      case LE_EQUALS:{
        op = jj_consume_token(LE_EQUALS);
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      node2 = addSubExpression();
switch(op.kind)
      {
       case GREATER:
        node = new ASTGreater(node,node2);
        break;
       case GR_EQUALS:
        node = new ASTGreaterEqual(node,node2);
        break;
          case LESS:
           node = new ASTLess(node,node2);
           break;
          case LE_EQUALS:
           node = new ASTLessEqual(node,node2);
           break;
      }
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      ;
    }
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression addSubExpression() throws ParseException {Token op;
    ASTExpression node, node2;
    node = multDivExpression();
    label_4:
    while (true) {
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case ADD:
      case SUB:{
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case ADD:{
        op = jj_consume_token(ADD);
        break;
        }
      case SUB:{
        op = jj_consume_token(SUB);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      node2 = multDivExpression();
if (op.kind == ADD)
          node = new ASTAddition(node,node2);
     else
      node = new ASTSubtraction(node,node2);
    }
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression multDivExpression() throws ParseException {Token op;
    ASTExpression node, node2;
    node = Cast();
    label_5:
    while (true) {
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case MULT:
      case DIV:{
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        break label_5;
      }
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case MULT:{
        op = jj_consume_token(MULT);
        break;
        }
      case DIV:{
        op = jj_consume_token(DIV);
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      node2 = Cast();
if (op.kind == MULT)
          node = new ASTMultiplication(node,node2);
   else
    node = new ASTDivision(node,node2);
    }
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public Token PrimitiveType() throws ParseException {Token t;
    switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
    case FLOAT:{
      t = jj_consume_token(FLOAT);
      break;
      }
    case INT:{
      t = jj_consume_token(INT);
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Cast() throws ParseException {Token type = null;
    ASTExpression node;
    switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
    case LCUR:{
      jj_consume_token(LCUR);
      type = PrimitiveType();
      jj_consume_token(RCUR);
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      ;
    }
    node = Unary();
if (type==null)
         {if ("" != null) return node;}
        else
         {if ("" != null) return new ASTCast(node,type.image);}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public Token UnaryOperator() throws ParseException {Token t;
    switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
    case SUB:{
      t = jj_consume_token(SUB);
      break;
      }
    case TILDE:{
      t = jj_consume_token(TILDE);
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Unary() throws ParseException {Token op = null;
    ASTExpression node;
    switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
    case SUB:
    case TILDE:{
      op = UnaryOperator();
      break;
      }
    default:
      jj_la1[14] = jj_gen;
      ;
    }
    node = Fact();
if (op==null)
        {if ("" != null) return node;}
    switch(op.kind)
    {
        case SUB:
            node = new ASTNegate(node);
            break;
        case TILDE:
            node = new ASTNot(node);
            break;
    }
    {if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Fact() throws ParseException {ASTExpression node;
    switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
    case TRUE:
    case FALSE:{
      node = Boolean();
      break;
      }
    case IDENTIFIER:{
      node = Identifier();
      break;
      }
    case LET:{
      node = Let();
      break;
      }
    case LPAR:{
      node = parenthesizedExpression();
      break;
      }
    case FLOAT_LITERAL:{
      node = Float();
      break;
      }
    case INTEGER_LITERAL:{
      node = Integer();
      break;
      }
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Identifier() throws ParseException {Token id;
    ASTExpression node;
    id = jj_consume_token(IDENTIFIER);
node = new ASTIdentifier(id.image);
        {if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public Binding Binding() throws ParseException {Token id;
    ASTExpression exp;
    id = jj_consume_token(IDENTIFIER);
    jj_consume_token(EQUALS);
    exp = Expression();
{if ("" != null) return new Binding(id.image,exp);}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public List<Binding> BindingsList() throws ParseException {Binding d;
    List<Binding> list = new ArrayList<>();
    d = Binding();
list.add(d);
    label_6:
    while (true) {
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case COMA:
      case IDENTIFIER:{
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        break label_6;
      }
      switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
      case COMA:{
        jj_consume_token(COMA);
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        ;
      }
      d = Binding();
list.add(d);
    }
{if ("" != null) return list;}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Let() throws ParseException {List<Binding> list;
    ASTExpression body;
    jj_consume_token(LET);
    list = BindingsList();
    jj_consume_token(IN);
    body = Expression();
    jj_consume_token(END);
{if ("" != null) return new ASTLet(list,body);}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Integer() throws ParseException {Token num;
    num = jj_consume_token(INTEGER_LITERAL);
{if ("" != null) return new ASTInteger(Integer.parseInt(num.image));}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Float() throws ParseException {Token num;
    num = jj_consume_token(FLOAT_LITERAL);
{if ("" != null) return new ASTFloat(Float.parseFloat(num.image));}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression Boolean() throws ParseException {Token value;
    switch (jj_ntk == -1 ? jj_ntk_f() : jj_ntk) {
    case TRUE:{
      value = jj_consume_token(TRUE);
      break;
      }
    case FALSE:{
      value = jj_consume_token(FALSE);
      break;
      }
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return new ASTBoolean(Boolean.parseBoolean(value.image));}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTNode Println() throws ParseException {ASTExpression exp;
    jj_consume_token(PRINTLN);
    jj_consume_token(LPAR);
    exp = Expression();
    jj_consume_token(RPAR);
{if ("" != null) return new ASTPrintln(exp);}
    throw new IllegalStateException ("Missing return statement in function");
}

  final public ASTExpression parenthesizedExpression() throws ParseException {ASTExpression node;
    jj_consume_token(LPAR);
    node = Expression();
    jj_consume_token(RPAR);
{if ("" != null) return node;}
    throw new IllegalStateException ("Missing return statement in function");
}

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0xa8004400,0x10000,0x8000,0xc0000,0xc0000,0xf00000,0xf00000,0x600,0x600,0x1800,0x1800,0x0,0x20000000,0x4400,0x4400,0x88000000,0x100,0x100,0x0,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x3cc,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x30,0x0,0x0,0x0,0x38c,0x200,0x0,0xc,};
	}

  /**
   * Constructor with InputStream.
   * @param stream char stream
   */
  public Parser(final Provider stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
   for (int i = 0; i < 19; i++)
     jj_la1[i] = -1;
  }

  /**
   * Constructor with InputStream.
   * @param sDSL String representation to be parsed
   */
  public Parser(final String sDSL) {
	   this(new StringProvider(sDSL));
  }

  /**
   * Reinitialise.
   * @param sDSL String representation to be parsed
   */
  public void ReInit(final String sDSL) {
	  ReInit(new StringProvider(sDSL));
  }
  /**
   * Reinitialise
   * @param stream char stream
   */
  public void ReInit(final Provider stream) {
	if (jj_input_stream == null) {
	  jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	  jj_input_stream.reInit(stream, 1, 1);
  }
	if (token_source == null) {
 token_source = new ParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
   for (int i = 0; i < 19; i++)
     jj_la1[i] = -1;
  }

  /**
   * Constructor with generated Token Manager.
   * @param tm Token manager to use
   */
  public Parser(final ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /**
   * Reinitialise
   * @param tm Token manager to use
   */
  public void ReInit(final ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(final int kind) throws ParseException {
    final Token oldToken = token;
    if (token.next != null)
      token = token.next;
    else {
      token.next = token_source.getNextToken();
      token = token.next;
    }
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


  /**
   * @return the next Token.
   */
  public final Token getNextToken() {
   if (token.next != null)
     token = token.next;
   else
     token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

  /**
   * @param index index to be retrieved
   * @return the specific Token.
   */
  public final Token getToken(final int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next == null)
        t.next = token_source.getNextToken();
      t = t.next;
    }
    return t;
  }

  private int jj_ntk_f() {
    jj_nt = token.next;
    if (jj_nt == null) {
      token.next = token_source.getNextToken();
      jj_ntk = token.next.kind;
      return jj_ntk;
    }
    jj_ntk = jj_nt.kind;
    return jj_ntk;
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /**
   * Generate ParseException.
   * @return new Exception object. Never <code>null</code>
   */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[44];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 44; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage, token_source == null ? null : ParserTokenManager.lexStateNames[token_source.curLexState]);
  }

  /**
   * @return Always <code>false</code>.
   */
  public final boolean trace_enabled() {
    return false;
  }

  /** Enable tracing. */
  public final void enable_tracing() {}

  /** Disable tracing. */
  public final void disable_tracing() {}

}
