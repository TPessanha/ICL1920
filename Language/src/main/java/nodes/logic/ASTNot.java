package nodes.logic;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import nodes.ASTNode;
import nodes.ASTOperation;
import state.Environment;
import types.IType;
import values.BooleanValue;
import values.IValue;

public class ASTNot extends ASTOperation {
	private static final String operator = "~";
	private ASTNode node;

	public ASTNot(ASTNode node)
	{
		super(operator);
		this.node = node;
	}
	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue v = node.eval(environment);

		if (v instanceof BooleanValue)
			return ((BooleanValue) v).not();
		throw new IllegalOperatorException(operator, v.getTypeName());
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = node.compile(environment);
		code.emit_iconst(1);
		code.emit_ixor();
		code.emit_blank();
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		return setType(node.typecheck(environment));
	}
}
