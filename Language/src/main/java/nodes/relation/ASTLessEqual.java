package nodes.relation;

import compiler.CodeBlock;
import nodes.ASTExpression;
import values.BooleanValue;
import values.IValue;

public class ASTLessEqual extends ASTRelation {
	private static final String operator = "<=";

	public ASTLessEqual(ASTExpression lNode, ASTExpression rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public BooleanValue basicOperation(IValue v1, IValue v2) {
		float f1 = ((Number) v1.getValue()).floatValue();
		float f2 = ((Number) v2.getValue()).floatValue();

		if (f1 <= f2)
			return BooleanValue.TRUE;
		return BooleanValue.FALSE;
	}

	@Override
	protected CodeBlock floatJumpCondition(String label) {
		CodeBlock code = new CodeBlock();
		code.emit_if_greater(label);
		return code;
	}

	@Override
	protected CodeBlock intJumpCondition(String label) {
		CodeBlock code = new CodeBlock();
		code.emit_int_compare_greater(label);
		return code;
	}
}
