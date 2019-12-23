package types;

public class ReferenceType<type extends IType> extends AnyType {
	public static final ReferenceType value = new ReferenceType(AnyType.value);
	private type referenceTo;

	public ReferenceType(type referenceTo) {
		this.referenceTo = referenceTo;
	}

	public type getReferenceType() {
		return referenceTo;
	}

	@Override
	public String getName() {
		return "reference2" + referenceTo.getName().split("2")[0];
	}

	@Override
	public ReferenceType getType() {
		return this;
	}

	@Override
	public String getJVMType() {
		return AnyType.value.getJVMType();
	}

	@Override
	public String getClassName() {
		return getName();
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean equals(ReferenceType other)
	{
		return getName().equals(other.getName());
	}
}
