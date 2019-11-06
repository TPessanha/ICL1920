package types;

public class NumberType implements IType {
	public final static NumberType value = new NumberType();

	@Override
	public String getTypeName() {
		return "number";
	}

	@Override
	public NumberType getType() {
		return value;
	}
}
