package nodes.arithmetic;

import compiler.CodeBlock;
import exceptions.DividedByZeroException;
import exceptions.UndeclaredTypeException;
import nodes.ASTNode;
import types.NumberType;
import values.IValue;
import values.NumberValue;

public class ASTDivision extends ASTArithmetic {
	public final static String operator = "/";

	public ASTDivision(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() throws UndeclaredTypeException {
		CodeBlock code = new CodeBlock();
		code.emit_div(((NumberType) getType()).getConversionLiteral());
		code.emit_blank();
		return code;
	}

	@Override
	public IValue doOperation(IValue v1, IValue v2) throws DividedByZeroException {
		return ((NumberValue) v1).divide((NumberValue) v2);
	}
}
