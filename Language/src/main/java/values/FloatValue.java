package values;

import exceptions.DividedByZeroException;
import types.FloatType;
import types.IType;

public class FloatValue extends NumberValue<Float> {

	public FloatValue(float value) {
		this.value = value;
	}

	@Override
	public FloatValue add(NumberValue number) {
		return new FloatValue(getValue() + ((Number) number.getValue()).floatValue());
	}

	@Override
	public FloatValue subtract(NumberValue number) {
		return new FloatValue(getValue() - ((Number) number.getValue()).floatValue());
	}

	@Override
	public FloatValue multiply(NumberValue number) {
		return new FloatValue(getValue() * ((Number) number.getValue()).floatValue());
	}

	@Override
	public FloatValue divide(NumberValue number) throws DividedByZeroException {
		float v2 = ((Number) number.getValue()).intValue();
		if (v2 == 0)
			throw new DividedByZeroException();
		return new FloatValue(getValue() / v2);
	}

	@Override
	public Float getValue() {
		return value.floatValue();
	}

	@Override
	public String getTypeName() {
		return "float";
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
	public int compareTo(IValue anotherFloat) {
		return getValue().compareTo((Float) anotherFloat.getValue());
	}
}
