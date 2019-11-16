package nodes.primitives;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import values.FloatValue;

public class ASTFloat extends ASTPrimitive<FloatValue> {
	public ASTFloat(float value) {
		super(new FloatValue(value));
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) {
		CodeBlock instructions = new CodeBlock();

		instructions.emit_optimize_float(value.getValue());

		return instructions;
	}
}
