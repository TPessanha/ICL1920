package nodes.primitives;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import values.IntValue;

public class ASTInteger extends ASTPrimitive<IntValue> {
	public ASTInteger(int value) {
		super(new IntValue(value));
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) {
		CodeBlock code = new CodeBlock();

		code.emit_optimize_int(value.getValue());
		code.emit_blank();

		return code;
	}
}
