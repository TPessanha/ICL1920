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
	public String getJVMType() {
		return "L" + getJavaClass() + ";";
	}

	@Override
	public String getClassName() {
		return "Object";
	}

	@Override
	public String getJavaClass() {
		return "java/lang/Object";
	}

	@Override
	public String toString() {
		return "any";
	}

	@Override
	public boolean equals(IType anotherType)
	{
		return getName().equals(anotherType.getName());
	}
}
