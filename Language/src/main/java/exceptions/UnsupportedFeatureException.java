package exceptions;

public class UnsupportedFeatureException extends Exception {
	public UnsupportedFeatureException(String msg) {
		super(msg);
	}

	public UnsupportedFeatureException()
	{
		super("This feature is not yet implemented");
	}
}
