package types;

public class VoidType implements IType {
	public final static VoidType value = new VoidType();

	@Override
	public String getTypeName() {
		return "void";
	}

	@Override
	public IType getType() {
		return value;
	}

	@Override
	public String getJVMType() {
		return "V";
	}
}
