package values;

import types.FloatType;
import types.IType;

public class FloatValue extends NumberValue<Float> {

	public FloatValue(float value) {
		this.value = value;
	}

	@Override
	public FloatValue Add(NumberValue number) {
		return new FloatValue(getValue() + ((Number) number.getValue()).floatValue());
	}

	@Override
	public FloatValue Subtract(NumberValue number) {
		return new FloatValue(getValue() - ((Number) number.getValue()).floatValue());
	}

	@Override
	public FloatValue Multiply(NumberValue number) {
		return new FloatValue(getValue() * ((Number) number.getValue()).floatValue());
	}

	@Override
	public FloatValue Divide(NumberValue number) {
		return new FloatValue(getValue() / ((Number) number.getValue()).floatValue());
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
	public IValue<Float> negate() {
		return new FloatValue(-value.floatValue());
	}

	@Override
	public boolean equals(IValue<Float> anotherFloat) {
		return getValue().equals(anotherFloat.getValue());
	}

	@Override
	public int compareTo(IValue<Float> anotherFloat) {
		return getValue().compareTo(anotherFloat.getValue());
	}
}
