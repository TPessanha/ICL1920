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

}
