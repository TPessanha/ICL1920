package types;

public class BooleanType implements IType {
	public final static BooleanType value = new BooleanType();

	@Override
	public String getTypeName() {
		return "boolean";
	}

	@Override
	public BooleanType getType() {
		return value;
	}
}
