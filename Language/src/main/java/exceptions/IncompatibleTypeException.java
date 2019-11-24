package exceptions;

import types.IType;

public class IncompatibleTypeException extends TypeMismatchException {
	public IncompatibleTypeException(String msg) {
		super(msg);
	}

	public IncompatibleTypeException(IType expected, IType actual) {
		this(expected.getName(), actual.getName());
	}

	public IncompatibleTypeException(String expected, String actual) {
		super("Incompatible type:\nRequired : " + expected + "\nFound: " + actual);
	}
}
