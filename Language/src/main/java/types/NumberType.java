package types;

public abstract class NumberType extends AnyType implements PrimitiveType {
	public abstract int getPriorityLevel();

	public abstract String getConversionLiteral();
}
