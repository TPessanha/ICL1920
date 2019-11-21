package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import state.Environment;
import types.IType;
import values.IValue;
import values.NumberValue;

public class ASTNegate extends ASTNode {
    private Node node;

    public ASTNegate(Node node)
    {
        this.node = node;
    }

    @Override
    public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
        IValue v = node.eval(environment);

        if (v instanceof NumberValue)
            return ((NumberValue) v).negate();
        else
            throw new IllegalOperatorException("-", v.getTypeName());
    }

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = node.compile(environment);
		code.emit_ineg();
		code.emit_blank();
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		return setType(node.typecheck(environment));
	}
}
