package nodes;

import compiler.CodeBlock;
import values.NumberValue;
import values.IValue;

public class ASTAddition extends ASTArithmetic {
	public final static String operator = "+";

	public ASTAddition(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() {
		CodeBlock code = new CodeBlock();
		code.emit_iadd();
		code.emit_blank();
		return code;
	}
	@Override
	public IValue basicOperation(IValue v1, IValue v2) {
		return ((NumberValue) v1).add((NumberValue) v2);
	}
}
