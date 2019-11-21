package nodes.arithmetic;

import compiler.CodeBlock;
import exceptions.NullTypecheckException;
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
	public CodeBlock emitOperation() throws NullTypecheckException {
		CodeBlock code = new CodeBlock();
		code.emit_add(((NumberType) getType()).getConversionLiteral());
		code.emit_blank();
		return code;
	}

	@Override
	public IValue basicOperation(IValue v1, IValue v2) {
		return ((NumberValue) v1).add((NumberValue) v2);
	}

	@Override
	public String getOperator() {
		return operator;
	}
}
