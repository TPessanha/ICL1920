package values;

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
		return null;
	}

	@Override
	public IType getType() {
		return null;
	}

	@Override
	public IValue<Float> negate() {
		return null;
	}

	@Override
	public boolean equals(IValue<Float> value) {
		return false;
	}

	@Override
	public int compareTo(IValue<Float> o) {
		return 0;
	}
}
