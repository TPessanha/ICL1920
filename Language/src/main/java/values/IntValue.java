package values;

import types.IType;
import types.IntType;

/**
 * Description:
 */
public class IntValue extends NumberValue<Integer> {
    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value.intValue();
    }

    @Override
    public String getTypeName() {
        return "int";
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
    public boolean equals(IValue<Integer> anotherInt) {
        return getValue().equals(anotherInt.getValue());
    }

    @Override
    public int compareTo(IValue<Integer> anotherInt) {
        return getValue().compareTo(anotherInt.getValue());
    }

    public IntValue Add(NumberValue number) {
        return new IntValue(getValue() + ((Number) number.getValue()).intValue());
    }

    public IntValue Subtract(NumberValue number) {
        return new IntValue(getValue() - ((Number) number.getValue()).intValue());
    }

    public IntValue Multiply(NumberValue number) {
        return new IntValue(getValue() * ((Number) number.getValue()).intValue());
    }

    public IntValue Divide(NumberValue number) {
        return new IntValue(getValue() / ((Number) number.getValue()).intValue());
    }
}
