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
	public String getJVMType() {
		return "I";
	}

	@Override
	public String getClassName() {
		return "Integer";
	}

	@Override
	public String getJavaClass() {
		return "java/lang/Integer";
	}

	public String getConversionLiteral(){
		return "i";
	}

	@Override
	public String toString() {
		return "int";
	}
}
