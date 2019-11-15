package types;

public class IntType extends NumberType{
	public final static IntType value = new IntType();

	@Override
	public String getTypeName() {
		return "int";
	}

	@Override
	public IntType getType(){
		return value;
	}

	@Override
	public int getPriorityLevel() {
		return 2;
	}

	@Override
	public String getJVMType() {
		return "I";
	}

	public String getConversionLiteral(){
		return "i";
	}
}
