package exceptions;

public class IllegalOperatorException extends TypeMismatchException {
    public IllegalOperatorException() {
        super("That operator is invalid");
    }

    public IllegalOperatorException(String message) {
        super(message);
    }

    public IllegalOperatorException(String operator, String type1) {
        super("Operator '" + operator + "' cannot be applied to '" + type1 + "'");
    }

    public IllegalOperatorException(String operator, String type1, String type2) {
        super("Operator '" + operator + "' cannot be applied to '" + type1 + "', '" + type2 + "'");
    }
}
