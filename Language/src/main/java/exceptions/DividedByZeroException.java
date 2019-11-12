package exceptions;

public class DividedByZeroException extends Exception {
	public DividedByZeroException() {
		super("Divided by zero");
	}

	public DividedByZeroException(String message) {
		super(message);
	}
}
