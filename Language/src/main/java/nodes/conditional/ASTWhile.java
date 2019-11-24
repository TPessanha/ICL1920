package nodes.conditional;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IncompatibleTypeException;
import nodes.ASTNode;
import nodes.ASTStatement;
import state.Environment;
import types.BooleanType;
import types.IType;
import values.IValue;

public class ASTWhile extends ASTStatement {
	ASTNode condition, body;

	public ASTWhile(ASTNode condition, ASTNode body) {
		super();
		this.condition = condition;
		this.body = body;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		while ((Boolean) condition.eval(environment).getValue()) {
			body.eval(environment);
		}

		return getValue();
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType condType = condition.typecheck(environment);

		if (!(condType instanceof BooleanType))
			throw new IncompatibleTypeException(BooleanType.value,condType);

		body.typecheck(environment);

		return getType();
	}
}
