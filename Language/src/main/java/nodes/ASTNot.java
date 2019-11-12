package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import state.Environment;
import types.IType;
import values.BooleanValue;
import values.IValue;
import values.NumberValue;

public class ASTNot implements ASTNode {
	private ASTNode node;

	public ASTNot(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue v = node.eval(environment);

		if (v instanceof BooleanValue)
			return ((BooleanValue) v).not();
		throw new IllegalOperatorException("!", v.getTypeName());
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
		return node.typecheck(environment);
	}
}
