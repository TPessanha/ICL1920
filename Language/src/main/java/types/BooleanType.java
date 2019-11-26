package types;

public class BooleanType extends AnyType {
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
	public String getJVMName() {
		return "Z";
	}

	@Override
	public String getClassName() {
		return "Boolean";
	}

	@Override
	public String getJavaClass() {
		return "java/lang/Boolean";
	}
}
