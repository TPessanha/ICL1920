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
	public String getJVMClass() {
		return "F";
	}
}
