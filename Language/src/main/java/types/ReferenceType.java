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
		return "reference@"+ referenceTo.getName().split("@")[0];
	}

	@Override
	public ReferenceType getType() {
		return new ReferenceType<>(referenceTo);
	}

	@Override
	public String getJVMClass() {
		return "Lref_" + referenceTo.getName() + ";";
	}
}
