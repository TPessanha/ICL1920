package exceptions;

public class RepeatedTypeInitException extends Exception {
	public RepeatedTypeInitException()
	{
		super("A type for this node has already been set");
	}
}
