package types;

public class FloatType extends NumberType {
	public final static FloatType value = new FloatType();

	@Override
	public String getName() {
		return "float";
	}

	@Override
	public FloatType getType() {
		return value;
	}

	@Override
	public int getPriorityLevel() {
		return 4;
	}

	@Override
	public String getConversionLiteral() {
		return "f";
	}

	@Override
	public String getJVMType() {
		return "F";
	}

	@Override
	public String getClassName() {
		return "Float";
	}

	@Override
	public String getJavaClass() {
		return "java/lang/Float";
	}

	@Override
	public String toString() {
		return "float";
	}

	@Override
	public String returnValue() {
		return "freturn";
	}

	@Override
	public String loadValue() {
		return "fload";
	}
}
