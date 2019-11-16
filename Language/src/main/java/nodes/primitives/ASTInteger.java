package nodes.primitives;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import nodes.primitives.ASTPrimitive;
import values.IntValue;

public class ASTInteger extends ASTPrimitive<IntValue> {

	public ASTInteger(int value) {
		super(new IntValue(value));
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) {
		CodeBlock instructions = new CodeBlock();

		instructions.emit_optimize_int(value.getValue());

		return instructions;
	}
}
