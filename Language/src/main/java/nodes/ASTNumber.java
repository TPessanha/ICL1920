package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import types.IntType;
import values.IntValue;
import values.Value;

public class ASTNumber implements ASTNode {
    private final IntValue number;

    public ASTNumber(int value) {
        this.number = new IntValue(value);
    }

    @Override
    public Value<?> eval(Environment<Value<?>> environment) {
        return number;
    }

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock instructions = new CodeBlock();

		instructions.emit_sint(number.getValue());

		return instructions;
	}

	@Override
	public IType typecheck() {
		return IntType.value;
	}
}
