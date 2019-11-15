package exceptions;

public class IllegalCastException extends Exception {
	public IllegalCastException() {
		super("Can't cast from type 1 to type 2");
	}

	public IllegalCastException(String type1, String type2) {
		super("Can 't cast from " + type1 + " to " + type2);
	}
}
