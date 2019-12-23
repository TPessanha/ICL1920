package values;

import nodes.ASTNode;
import state.Environment;
import state.Parameter;
import types.ClosureType;
import types.IType;

import java.util.List;

public class FunctionValue implements IValue<Closure> {
	private Closure value;

	public FunctionValue(List<Parameter> parameters, Environment environment, ASTNode body) {
		value = new Closure(parameters, environment, body);
	}

	public FunctionValue(Closure value) {
		this.value = value;
	}

	public List<Parameter> getParameters() {
		return value.parameters;
	}

	public ASTNode getBody()
	{
		return value.body;
	}

	public Environment getEnvironment()
	{
		return value.environment;
	}

	@Override
	public Closure getValue() {
		return value;
	}

	@Override
	public String getTypeName() {
		return getType().getName();
	}

	@Override
	public IType getType() {
		return ClosureType.value;
	}

	@Override
	public boolean equals(IValue value) {
		return getValue().equals(value);
	}
}
