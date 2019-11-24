package types;

public class StringType implements IType {
	public final static StringType value = new StringType();

	@Override
	public String getName() {
		return "String";
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
		return "java/lang/String";
	}

	@Override
	public String getJavaClass() {
		return getClassName();
	}
}
