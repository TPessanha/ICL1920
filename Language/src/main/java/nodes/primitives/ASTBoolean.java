package nodes.primitives;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import values.BooleanValue;

public class ASTBoolean extends ASTPrimitive<BooleanValue> {
	public ASTBoolean(boolean value) {
		super(new BooleanValue(value));
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) {
		CodeBlock instructions = new CodeBlock();

		instructions.emit_boolean(value.getValue());

		return instructions;
	}
}
