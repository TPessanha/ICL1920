package nodes.logic;

import compiler.CodeBlock;
import nodes.ASTExpression;
import values.BooleanValue;
import values.IValue;

public class ASTOr extends ASTLogic {
	private static final String operator = "||";

	public ASTOr(ASTExpression lNode, ASTExpression rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() {
		CodeBlock code = new CodeBlock();
		code.emit_ior();
		code.emit_blank();
		return code;
	}

	@Override
	public IValue basicOperation(IValue v1, IValue v2) throws Exception {
		boolean b1 = ((BooleanValue) v1).getValue();
		boolean b2 = ((BooleanValue) v2).getValue();

		return new BooleanValue(b1 || b2);
	}
}
