package types;

public interface IType {
	String getName();

	IType getType();

	String getJVMType();

	String getClassName();

	String getJavaClass();

	boolean equals(IType anotherType);

	String returnValue();

	String loadValue();
}
