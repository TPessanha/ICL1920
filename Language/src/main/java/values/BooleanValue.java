package values;

import types.BooleanType;
import types.IType;

/**
 * Description:
 */
public class BooleanValue extends AnyValue<Boolean> implements Comparable<BooleanValue> {
//    private final boolean value;
    public final static BooleanValue FALSE = new BooleanValue(false);
    public final static BooleanValue TRUE = new BooleanValue(true);

    public BooleanValue(boolean value) {
    	super(value);
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
    public boolean equals(IValue anotherBoolean) {
        return getValue().equals(anotherBoolean.getValue());
    }

	public BooleanValue not() {
		return new BooleanValue(!value);
	}

	@Override
	public int compareTo(BooleanValue anotherBoolean) {
		return getValue().compareTo(anotherBoolean.getValue());
	}
}
