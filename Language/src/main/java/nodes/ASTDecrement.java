package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import state.Environment;
import types.IType;
import values.IValue;
import values.IntValue;
import values.NumberValue;

public class ASTDecrement implements Node {

    private Node node;

    public ASTDecrement(Node node) {
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
