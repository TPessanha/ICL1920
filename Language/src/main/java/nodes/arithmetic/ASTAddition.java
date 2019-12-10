package nodes.arithmetic;

import compiler.CodeBlock;
import exceptions.UndeclaredTypeException;
import nodes.ASTNode;
import types.NumberType;
import values.IValue;
import values.NumberValue;

public class ASTAddition extends ASTArithmetic {
	public final static String operator = "+";

	public ASTAddition(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() throws UndeclaredTypeException {
		CodeBlock code = new CodeBlock();
		code.emit_add(((NumberType) getType()).getConversionLiteral());
		code.emit_blank();
		return code;
	}

	@Override
	public IValue doOperation(IValue v1, IValue v2) {
		return ((NumberValue) v1).add((NumberValue) v2);
	}

}
