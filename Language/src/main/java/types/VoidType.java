package types;

public class VoidType extends AnyType {
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
	public String getJVMType() {
		return "V";
	}

	@Override
	public String getClassName() {
		return "V";
	}

	@Override
	public String getJavaClass() {
		return "java/lang/void";
	}

	@Override
	public String toString() {
		return "void";
	}


}
