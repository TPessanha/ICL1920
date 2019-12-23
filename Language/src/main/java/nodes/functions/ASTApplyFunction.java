package nodes.functions;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import compiler.classes.ClosureInterface;
import exceptions.IllegalTypeException;
import nodes.ASTExpression;
import nodes.ASTNode;
import state.Environment;
import types.FunctionType;
import types.IType;
import values.FunctionValue;
import values.IValue;

import java.util.ArrayList;
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
		FunctionValue function = (FunctionValue) (functionID.eval(environment));

		Environment<IValue<?>> localScope = function.getEnvironment().beginScope();

		for (int i = 0; i < arguments.size(); i++)
			localScope.associate(function.getParameters().get(i).getIdentifier(), arguments.get(i).eval(environment));

		IValue result = function.getBody().eval(localScope);

		localScope.endScope();
		return result;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		FunctionType fType = (FunctionType) functionID.getType();

		CodeBlock code = functionID.compile(environment);
		code.emit_checkcast(ClosureInterface.getInterfaceName(fType));

		int counter = 1;
		for (ASTNode arg : arguments) {
			code.emit_comment("[[e" + (counter++) + "]]");
			code.append(arg.compile(environment));
		}

		String call =
			ClosureInterface.getInterfaceName(fType) + "/" + ClosureInterface.getMethodCall(fType) + " " + (fType
				.getParameters().size() + 1);
		code.emit_invokeinterface(call);
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType type = functionID.typecheck(environment);

		if (!(type instanceof FunctionType))
			throw new IllegalTypeException("Expected a function to be called");

		FunctionType fType = (FunctionType) type;
		List<IType> arguments = getCall(environment);
		FunctionType got = new FunctionType(fType.getReturnType(), arguments);
		if (!parameterCompare(fType.getParameters(), arguments)) {
			throw new IllegalTypeException("Expected: " + fType.toString() + "\nGot: " + got.toString());
		}

		return setType(fType.getReturnType());
	}

	private boolean parameterCompare(List<IType> first, List<IType> snd) {
		if (first.size() != snd.size())
			return false;
		for (int i = 0; i < first.size(); i++) {
			if (!first.get(i).equals(snd.get(i)))
				return false;
		}
		return true;
	}

	private List<IType> getCall(Environment<IType> environment) throws Exception {
		List<IType> params = new ArrayList<>();
		for (ASTNode arg : arguments) {
			params.add(arg.typecheck(environment));
		}
		return params;
	}
}
