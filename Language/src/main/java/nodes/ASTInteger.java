package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import types.IntType;
import values.IntValue;
import values.IValue;

public class ASTInteger implements ASTNode {
    private final IntValue value;

    public ASTInteger(int value) {
        this.value = new IntValue(value);
    }

    @Override
    public IValue<?> eval(Environment<IValue<?>> environment) {
        return value;
    }

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock instructions = new CodeBlock();

		instructions.emit_sint(value.getValue());

		return instructions;
	}

	@Override
	public IType typecheck(Environment<IType> environment){
		return IntType.value;
	}
}
