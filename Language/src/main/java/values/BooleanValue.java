package values;

import types.BooleanType;
import types.IType;

/**
 * Description:
 */
public class BooleanValue implements IValue<Boolean> {
    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public String getTypeName() {
        return "boolean";
    }

	@Override
	public IType getType() {
		return BooleanType.value;
	}

	@Override
    public String toString() {
        return java.lang.Boolean.toString(value);
    }

    @Override
    public IValue<Boolean> negate() {
        return new BooleanValue(!value);
    }

    @Override
    public int compareTo(IValue<Boolean> anotherBoolean) {
        return getValue().compareTo(anotherBoolean.getValue());
    }

    @Override
    public boolean equals(IValue<Boolean> anotherBoolean) {
        return getValue().equals(anotherBoolean.getValue());
    }
}
