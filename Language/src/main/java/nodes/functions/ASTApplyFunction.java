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

public class ASTApplyFunction extends ASTExpression {
	private ASTNode functionID;
	private List<ASTNode> arguments;

	public ASTApplyFunction(ASTNode functionID, List<ASTNode> arguments) {
		this.functionID = functionID;
		this.arguments = arguments;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		ClosureValue function = (ClosureValue) (functionID.eval(environment));

		Environment<IValue<?>> localScope = function.getEnvironment().beginScope();

		for (int i = 0; i < arguments.size(); i++)
			localScope.associate(function.getParameters().get(i),arguments.get(i).eval(environment));

		IValue result = function.getBody().eval(localScope);

		localScope.endScope();
		return result;
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
