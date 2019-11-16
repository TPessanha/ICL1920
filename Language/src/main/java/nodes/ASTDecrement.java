package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import state.Environment;
import types.IType;
import values.IntValue;
import values.NumberValue;
import values.IValue;

public class ASTDecrement implements ASTNode {

    private ASTNode node;

    public ASTDecrement(ASTNode node) {
        this.node = node;

    }

    @Override
    public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
        IValue value = node.eval(environment);

        if (value instanceof NumberValue)
            return (NumberValue)((NumberValue) value).subtract(new IntValue(1));
        throw new IllegalOperatorException("++",value.getTypeName());
    }

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> environment) {
		return null;
	}
}
