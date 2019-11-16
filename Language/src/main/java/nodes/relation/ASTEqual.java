package nodes.relation;

import compiler.CodeBlock;
import nodes.ASTExpression;
import values.BooleanValue;
import values.IValue;
import values.NumberValue;

public class ASTEqual extends ASTRelation {
	public final static String operator = "==";

	public ASTEqual(ASTExpression lNode, ASTExpression rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public BooleanValue basicOperation(IValue v1, IValue v2) {
		if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
			float f1 = ((Number) v1.getValue()).floatValue();
			float f2 = ((Number) v2.getValue()).floatValue();

			if (f1 == f2)
				return BooleanValue.TRUE;
			return BooleanValue.FALSE;
		} else {
			if (v1.getValue().equals(v2.getValue()))
				return BooleanValue.TRUE;
			return BooleanValue.FALSE;
		}
	}

	@Override
	protected CodeBlock floatJumpCondition(String label) {
		CodeBlock code = new CodeBlock();
		code.emit_if_not_equal(label);
		return code;
	}

	@Override
	protected CodeBlock intJumpCondition(String label) {
		CodeBlock code = new CodeBlock();
		code.emit_int_compare_not_equal(label);
		return code;
	}
}
