package values;

import types.IType;
import types.VoidType;

public class VoidValue implements IValue<VoidType> {
	@Override
	public VoidType getValue() {
		return null;
	}

	@Override
	public String getTypeName() {
		return VoidType.value.getName();
	}

	@Override
	public IType getType() {
		return VoidType.value;
	}

	@Override
	public boolean equals(IValue value) {
		return value.getValue() == null;
	}

	@Override
	public int compareTo(IValue<VoidType> o) {
		return -1;
	}
}
