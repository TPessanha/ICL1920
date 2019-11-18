package types;

public class ReferenceType<type extends IType> implements IType {
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
	public String getJVMClass() {
		return ObjectType.value.getJVMClass();
	}

	@Override
	public String getClassName() {
		return getName();
	}
}
