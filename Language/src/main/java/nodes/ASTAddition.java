package nodes;

import compiler.CodeBlock;
import values.NumberValue;
import values.IValue;

public class ASTAddition extends ASTMath {

	public ASTAddition(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode);
	}

	@Override
	protected CodeBlock emitOperation() {
		CodeBlock code = new CodeBlock();
		code.emit_iadd();
		code.emit_blank();
		return code;
	}

	@Override
	protected NumberValue doOperation(IValue v1, IValue v2) {
		return ((NumberValue) v1).Add((NumberValue) v2);
	}

	@Override
	protected String getOperator() {
		return "+";
	}
}
