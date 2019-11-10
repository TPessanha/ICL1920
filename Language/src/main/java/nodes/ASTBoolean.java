package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.TypeMismatchException;
import state.Environment;
import types.BooleanType;
import types.IType;
import values.BooleanValue;
import values.IValue;

public class ASTBoolean implements ASTNode {
	private BooleanValue value;

	public ASTBoolean(boolean value) {
		this.value = new BooleanValue(value);
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		return value;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock instructions = new CodeBlock();

		instructions.emit_sint(value.getValue() ? 1 : 0);

		return instructions;
	}

	@Override
	public IType typecheck() throws TypeMismatchException {
		return BooleanType.value;
	}
}
