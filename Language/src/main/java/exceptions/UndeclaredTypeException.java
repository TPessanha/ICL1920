package exceptions;

public class UndeclaredTypeException extends Exception {
	public UndeclaredTypeException() {
		super("Type is null (Run typecheck to initialize)");
	}

	public UndeclaredTypeException(String message) {
		super(message);
	}
}
