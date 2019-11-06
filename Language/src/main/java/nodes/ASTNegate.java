package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import state.Environment;
import types.IType;
import values.NumberValue;
import values.Value;

public class ASTNegate implements ASTNode{

    private ASTNode node;

    public ASTNegate(ASTNode node)
    {
        this.node = node;
    }

    @Override
    public Value<?> eval(Environment<Value<?>> environment) throws Exception {
        Value v = node.eval(environment);

        if (v instanceof NumberValue)
            return (NumberValue) v.negate();
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
	public IType typecheck() throws Exception {
		return null;
	}
}
