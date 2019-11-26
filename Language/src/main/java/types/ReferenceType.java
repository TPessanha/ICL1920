package types;

public class ReferenceType<type extends IType> extends AnyType {
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
	public String getJVMName() {
		return AnyType.value.getJVMName();
	}

	@Override
	public String getClassName() {
		return getName();
	}

}
