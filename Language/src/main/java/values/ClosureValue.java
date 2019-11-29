package values;

import nodes.ASTNode;
import state.Environment;
import types.ClosureType;
import types.IType;

import java.util.List;

public class ClosureValue implements IValue<Closure> {
	private Closure value;

	public ClosureValue(List<String> parameters, Environment environment, ASTNode body) {
		value = new Closure(parameters, environment, body);
	}

	public ClosureValue(Closure value) {
		this.value = value;
	}

	public List<String> getParameters() {
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
