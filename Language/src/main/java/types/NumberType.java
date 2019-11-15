package types;

public abstract class NumberType implements IType {
	@Override
	public abstract String getTypeName();

	@Override
	public abstract NumberType getType();

	public abstract int getPriorityLevel();

	public abstract String getConversionLiteral();
}
