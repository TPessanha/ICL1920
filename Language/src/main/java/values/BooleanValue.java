package values;

import types.BooleanType;
import types.IType;

/**
 * Description:
 */
public class BooleanValue implements IValue<Boolean>, Comparable<IValue<Boolean>> {
    private final boolean value;
    public final static BooleanValue FALSE = new BooleanValue(false);
    public final static BooleanValue TRUE = new BooleanValue(true);

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public String getTypeName() {
    	return getType().getName();
    }

	@Override
	public IType getType() {
		return BooleanType.value;
	}

	@Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public int compareTo(IValue anotherBoolean) {
        return getValue().compareTo((Boolean) anotherBoolean.getValue());
    }

    @Override
    public boolean equals(IValue anotherBoolean) {
        return getValue().equals(anotherBoolean.getValue());
    }

	public BooleanValue not() {
		return new BooleanValue(!value);
	}
}
