package types;

public class ObjectType implements IType {
	public static final ObjectType value = new ObjectType();

	@Override
	public String getName() {
		return "object";
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
}
