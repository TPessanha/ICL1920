package exceptions;

public class UndeclaredException extends Exception {
    public UndeclaredException() {
        super("Variable is undeclared");
    }

    public UndeclaredException(String message) {
        super(message);
    }
}
