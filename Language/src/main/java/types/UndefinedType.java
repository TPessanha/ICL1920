package types;

public class UndefinedType extends AnyType {
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
	public String getJVMNType() {
		return null;
	}

	@Override
	public String getClassName() {
		return null;
	}

	@Override
	public String getJavaClass() {
		return null;
	}
}
