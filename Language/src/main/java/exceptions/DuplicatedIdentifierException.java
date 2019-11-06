package exceptions;

public class DuplicatedIdentifierException extends Exception{
    DuplicatedIdentifierException(){
        super("Variable is already declared in the scope");
    }

    public DuplicatedIdentifierException(String msg){
        super(msg);
    }
}
