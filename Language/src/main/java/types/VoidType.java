package types;

public class VoidType implements IType {
	public final static VoidType value = new VoidType();

	@Override
	public String getName() {
		return "void";
	}

	@Override
	public IType getType() {
		return value;
	}

	@Override
	public String getJVMName() {
		return "V";
	}
}
