package types;

public class UndefinedType implements IType {
	public final static UndefinedType value = new UndefinedType();

	@Override
	public String getName() {
		return "undefined";
	}

	@Override
	public UndefinedType getType() {
		return value;
	}

	@Override
	public String getJVMClass() {
		return null;
	}
}
