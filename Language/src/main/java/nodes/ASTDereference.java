package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import types.ReferenceType;
import values.IValue;
import values.ReferenceValue;

public class ASTDereference extends ASTExpression {
	private final ASTNode reference;

	public ASTDereference(ASTNode reference) {
		this.reference = reference;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		ReferenceValue r = (ReferenceValue) reference.eval(environment);
		return r.getValue();
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t = reference.typecheck(environment);

		return ((ReferenceType) t).getReferenceType();
	}
}
