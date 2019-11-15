package exceptions;

public class NullTypecheckException extends Exception {
	public NullTypecheckException() {
		super("Type is null (Run typecheck to initialize)");
	}

	public NullTypecheckException(String message) {
		super(message);
	}
}
