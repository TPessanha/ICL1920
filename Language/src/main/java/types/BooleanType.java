package types;

public class BooleanType implements IType {
	public final static BooleanType value = new BooleanType();

	@Override
	public String getName() {
		return "boolean";
	}

	@Override
	public BooleanType getType() {
		return value;
	}

	@Override
	public String getJVMClass() {
		return "Z";
	}

	@Override
	public String getClassName() {
		return "Z";
	}
}
