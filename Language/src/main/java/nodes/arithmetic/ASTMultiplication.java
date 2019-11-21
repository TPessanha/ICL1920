package nodes.arithmetic;

import compiler.CodeBlock;
import exceptions.NullTypecheckException;
import nodes.ASTNode;
import types.NumberType;
import values.IValue;
import values.NumberValue;

public class ASTMultiplication extends ASTArithmetic {
	public final static String operator = "*";

	public ASTMultiplication(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() throws NullTypecheckException {
		CodeBlock code = new CodeBlock();
		code.emit_mul(((NumberType) getType()).getConversionLiteral());
		code.emit_blank();
		return code;
	}

	@Override
	public IValue doOperation(IValue v1, IValue v2) {
		return ((NumberValue) v1).multiply((NumberValue) v2);
	}
}
