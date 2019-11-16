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
	public String getJVMName() {
		return "Ljava/lang/String;";
	}
}
