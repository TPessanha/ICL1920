package types;

public class BooleanType extends AnyType implements PrimitiveType {
	public final static BooleanType value = new BooleanType();

	@Override
	public String getName() {
		return "boolean";
	}

	@Override
	public BooleanType getType() {
		return value;
	}

	@Override
	public String getJVMType() {
		return "Z";
	}

	@Override
	public String getClassName() {
		return "Boolean";
	}

	@Override
	public String getJavaClass() {
		return "java/lang/Boolean";
	}

	@Override
	public String toString() {
		return "boolean";
	}

	@Override
	public String returnValue() {
		return "ireturn";
	}

	@Override
	public String loadValue() {
		return "iload";
	}
}
