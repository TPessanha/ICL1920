package nodes.functions;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import nodes.ASTExpression;
import nodes.ASTNode;
import state.Environment;
import types.IType;
import values.ClosureValue;
import values.IValue;

import java.util.List;

public class ASTFunction extends ASTExpression {
	private final List<String> parameters;
	private final ASTNode body;

	public ASTFunction(List<String> parameters, ASTNode body) {
		this.parameters = parameters;
		this.body = body;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		return new ClosureValue(parameters, environment, body);
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		return null;
	}
}
