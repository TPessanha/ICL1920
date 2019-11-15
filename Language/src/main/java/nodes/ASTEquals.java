package nodes;

import compiler.CodeBlock;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import exceptions.NullTypecheckException;
import state.Environment;
import types.IType;
import types.NumberType;
import values.BooleanValue;
import values.IValue;
import values.NumberValue;

public class ASTEquals extends ASTRelation {
	public final static String operator = "==";

	public ASTEquals(ASTExpression lNode, ASTExpression rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() throws NullTypecheckException {
		IType t1 = lNode.getType();
		IType t2 = rNode.getType();

		CodeBlock code = new CodeBlock();
		switch (t1.getTypeName())
		{
			case "int":
				code.appendCodeBlock(integerCompare());
				break;
			case "float":
				code.appendCodeBlock(floatCompare());
				break;
		}

		return code;
	}

	private CodeBlock floatCompare() {
		String l1 = Compiler.generateUniqueLabel();
		String l2 = Compiler.generateUniqueLabel();

		CodeBlock code = new CodeBlock();
		code.emit_float_compare();
		code.emit_if_not_equal(l1);
		code.emit_boolean(true);
		code.emit_goto(l2);
		code.emit_label(l1);
		code.emit_boolean(false);
		code.emit_label(l2);

		return code;
	}

	private CodeBlock integerCompare() {
		String l1 = Compiler.generateUniqueLabel();
		String l2 = Compiler.generateUniqueLabel();

		CodeBlock code = new CodeBlock();
		code.emit_int_compare_not_equal(l1);
		code.emit_boolean(true);
		code.emit_goto(l2);
		code.emit_label(l1);
		code.emit_boolean(false);
		code.emit_label(l2);
		return code;
	}

	@Override
	public BooleanValue basicOperation(IValue v1, IValue v2) {
		if (v1 instanceof NumberValue && v2 instanceof NumberValue)
		{
			float f1 = ((Number) v1.getValue()).floatValue();
			float f2 = ((Number) v2.getValue()).floatValue();

			if (f1 == f2)
				return BooleanValue.TRUE;
			return BooleanValue.FALSE;
		}
		else
		{
			if (v1.getValue().equals(v2.getValue()))
				return BooleanValue.TRUE;
			return BooleanValue.FALSE;
		}
	}
}
