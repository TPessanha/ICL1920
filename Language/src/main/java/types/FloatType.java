package types;

public class FloatType extends NumberType {
	public final static FloatType value = new FloatType();

	@Override
	public String getTypeName() {
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
}
