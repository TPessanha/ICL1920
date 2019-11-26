package types;

public class StringType extends AnyType {
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
	public String getJVMType() {
		return "L" + getJavaClass() + ";";
	}

	@Override
	public String getClassName() {
		return "String";
	}

	@Override
	public String getJavaClass() {
		return "java/lang/String";
	}
}
