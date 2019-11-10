package values;

import types.IType;

public class CellValue implements IValue<IValue> {
	private IValue value;

	public CellValue(IValue value) {
		this.value = value;
	}

	@Override
	public IValue getValue() {
		return value;
	}

	public void setValue(IValue value) {
		this.value = value;
	}

	@Override
	public String getTypeName() {
		return "cell";
	}

	@Override
	public IType getType() {
		return value.getType();
	}

	@Override
	public IValue negate() {
		return value.negate();
	}

	@Override
	public boolean equals(IValue value) {
		return this.value.equals(value);
	}

	@Override
	public int compareTo(IValue<IValue> value) {
		return this.value.compareTo(value);
	}
}
