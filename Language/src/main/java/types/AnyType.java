package types;

public class AnyType implements IType {
	public static final AnyType value = new AnyType();

	@Override
	public String getName() {
		return "any";
	}

	@Override
	public IType getType() {
		return value;
	}

	@Override
	public String getJVMClass() {
		return "L" + getClassName() + ";";
	}

	@Override
	public String getClassName() {
		return "java/lang/Object";
	}

	@Override
	public String getJavaClass() {
		return getClassName();
	}
}
