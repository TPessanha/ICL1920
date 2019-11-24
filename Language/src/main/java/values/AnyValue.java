package values;

import types.AnyType;
import types.IType;

public class AnyValue<t> implements IValue<t> {
	protected t value;

	public AnyValue(t value) {
		this.value = value;
	}

	@Override
	public t getValue() {
		return value;
	}

	@Override
	public String getTypeName() {
		return "any";
	}

	@Override
	public IType getType() {
		return AnyType.value;
	}

	@Override
	public boolean equals(IValue<t> value) {
		return this.value.equals(value);
	}
}
