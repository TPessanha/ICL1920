package values;

import nodes.ASTNode;
import state.Environment;
import types.ClosureType;
import types.IType;

public class ClosureValue implements IValue<Closure> {
	private Closure value;

	public ClosureValue(String id, Environment environment, ASTNode body) {
		value = new Closure(id,environment,body);
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
