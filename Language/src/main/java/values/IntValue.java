package values;

import exceptions.DividedByZeroException;
import types.IType;
import types.IntType;

/**
 * Description:
 */
public class IntValue extends NumberValue implements Comparable<IntValue> {
    public IntValue(int value) {
    	super(value);
    }

    @Override
    public Integer getValue() {
        return value.intValue();
    }

    @Override
    public String getTypeName() {
        return getType().getName();
    }

	@Override
	public IType getType() {
		return IntType.value;
	}

	@Override
    public String toString() {
        return Integer.toString(value.intValue());
    }

    @Override
    public IntValue negate() {
        return new IntValue(-value.intValue());
    }

    @Override
    public boolean equals(IValue anotherInt) {
        return getValue().equals(anotherInt.getValue());
    }

    public IntValue add(NumberValue number) {
        return new IntValue(getValue() + (number.getValue()).intValue());
    }

    public IntValue subtract(NumberValue number) {
        return new IntValue(getValue() - (number.getValue()).intValue());
    }

    public IntValue multiply(NumberValue number) {
        return new IntValue(getValue() * (number.getValue()).intValue());
    }

    public IntValue divide(NumberValue number) throws DividedByZeroException {
    	int v2 = (number.getValue()).intValue();
    	if (v2==0)
    		throw new DividedByZeroException();
        return new IntValue(getValue() / v2);
    }

	@Override
	public int compareTo(IntValue anotherInt) {
		return getValue().compareTo(anotherInt.getValue());
	}
}
