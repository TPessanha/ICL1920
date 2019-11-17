package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import types.ReferenceType;
import values.IValue;
import values.ReferenceValue;

public class ASTReference extends ASTExpression {
	private final ASTNode referenceTo;

	public ASTReference(ASTExpression referenceTo) {
		this.referenceTo = referenceTo;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		return new ReferenceValue<>(referenceTo.eval(environment));
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		return setType(new ReferenceType<>(referenceTo.typecheck(environment)));
	}
}
