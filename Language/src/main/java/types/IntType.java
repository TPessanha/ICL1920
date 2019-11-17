package types;

public class IntType extends NumberType{
	public final static IntType value = new IntType();

	@Override
	public String getName() {
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
	public String getJVMClass() {
		return "I";
	}

	public String getConversionLiteral(){
		return "i";
	}
}
