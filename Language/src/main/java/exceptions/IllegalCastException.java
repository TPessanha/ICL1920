package exceptions;

import types.IType;

public class IllegalCastException extends TypeMismatchException {
	public IllegalCastException() {
		super("Can't cast from type 1 to type 2");
	}

	public IllegalCastException(String fromType, String toType) {
		super("Can 't cast from " + fromType + " to " + toType);
	}

	public IllegalCastException(IType fromType, IType toType) {
		this(fromType.getName(), toType.getName());
	}
}
