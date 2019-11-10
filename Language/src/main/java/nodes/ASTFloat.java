package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.FloatType;
import types.IType;
import values.FloatValue;
import values.IValue;

public class ASTFloat implements ASTNode {
	private final FloatValue value;

	public ASTFloat(float value) {
		this.value = new FloatValue(value);
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) {
		return value;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock instructions = new CodeBlock();

		instructions.emit_float(value.getValue());

		return instructions;
	}

	@Override
	public IType typecheck(Environment<IType> environment) {
		return FloatType.value;
	}
}
