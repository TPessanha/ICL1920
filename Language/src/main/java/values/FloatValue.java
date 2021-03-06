package values;

import exceptions.DividedByZeroException;
import types.FloatType;
import types.IType;

public class FloatValue extends NumberValue implements Comparable<FloatValue> {
	public FloatValue(float value) {
		super(value);
	}

	@Override
	public FloatValue add(NumberValue number) {
		return new FloatValue(getValue() + (number.getValue()).floatValue());
	}

	@Override
	public FloatValue subtract(NumberValue number) {
		return new FloatValue(getValue() - (number.getValue()).floatValue());
	}

	@Override
	public FloatValue multiply(NumberValue number) {
		return new FloatValue(getValue() * (number.getValue()).floatValue());
	}

	@Override
	public FloatValue divide(NumberValue number) throws DividedByZeroException {
		float v2 = (number.getValue()).intValue();
		if (v2 == 0)
			throw new DividedByZeroException();
		return new FloatValue(getValue() / v2);
	}

	@Override
	public String toString() {
		return Float.toString(value.floatValue());
	}

	@Override
	public Float getValue() {
		return value.floatValue();
	}

	@Override
	public String getTypeName() {
		return getType().getName();
	}

	@Override
	public IType getType() {
		return FloatType.value;
	}

	@Override
	public FloatValue negate() {
		return new FloatValue(-value.floatValue());
	}

	@Override
	public boolean equals(IValue anotherFloat) {
		return getValue().equals(anotherFloat.getValue());
	}

	@Override
	public int compareTo(FloatValue anotherFloat) {
		return getValue().compareTo(anotherFloat.getValue());
	}
}
