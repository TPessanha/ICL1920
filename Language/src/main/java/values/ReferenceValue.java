package values;

import types.IType;
import types.ReferenceType;

public class ReferenceValue<type extends IValue> extends AnyValue<type> {
	public ReferenceValue(type value) {
		super(value);
	}

	public void setValue(type value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "reference - > " + "(" + value.getTypeName() + ")";
	}

	@Override
	public String getTypeName() {
		return getType().getName();
	}

	@Override
	public IType getType() {
		return new ReferenceType<>(value.getType());
	}

	@Override
	public boolean equals(IValue anotherReference) {
		return getValue().equals(anotherReference.getValue());
	}
}
