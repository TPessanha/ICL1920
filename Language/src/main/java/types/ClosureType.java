package types;

public class ClosureType extends AnyType {
	public final static ClosureType value = new ClosureType();

	@Override
	public String getName() {
		return "closure";
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
	public String returnValue() {
		return "areturn";
	}
}
