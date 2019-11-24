package exceptions;

public class IllegalTypeException extends TypeMismatchException {
	public IllegalTypeException(String msg) {
		super(msg);
	}

	public IllegalTypeException() {
		super("The used type cannot be assign to this node");
	}
}
