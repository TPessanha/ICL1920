package nodes;

import compiler.CodeBlock;
import values.NumberValue;
import values.IValue;

public class ASTMultiplication extends ASTMath {
	public ASTMultiplication(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode);
	}

	@Override
	protected CodeBlock emitOperation() {
		CodeBlock code = new CodeBlock();
		code.emit_imul();
		code.emit_blank();
		return code;
	}

	@Override
	protected NumberValue doOperation(IValue v1, IValue v2) {
		return ((NumberValue) v1).Multiply((NumberValue) v2);
	}

	@Override
	protected String getOperator() {
		return "*";
	}
}
