package types;

public class StringType implements IType {
	public final static StringType value = new StringType();

	@Override
	public String getTypeName() {
		return "String";
	}

	@Override
	public IType getType() {
		return value;
	}

	@Override
	public String getJVMType() {
		return "Ljava/lang/String;";
	}
}
