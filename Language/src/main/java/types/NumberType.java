package types;

public abstract class NumberType extends AnyType {
	public abstract int getPriorityLevel();

	public abstract String getConversionLiteral();
}
