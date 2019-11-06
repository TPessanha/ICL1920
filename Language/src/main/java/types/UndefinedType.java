package types;

public class UndefinedType implements IType {
	public final static UndefinedType value = new UndefinedType();

	@Override
	public String getTypeName() {
		return "undefined";
	}

	@Override
	public UndefinedType getType() {
		return value;
	}
}
