package values;

import exceptions.DividedByZeroException;

/**
 * Description:
 */
public abstract class NumberValue extends AnyValue<Number> {
//    protected java.lang.Number value;

	public NumberValue(Number value) {
		super(value);
	}

	public abstract NumberValue add(NumberValue number);

    public abstract NumberValue subtract(NumberValue number);

    public abstract NumberValue multiply(NumberValue number);

    public abstract NumberValue divide(NumberValue number) throws DividedByZeroException;

    public abstract NumberValue negate();
}
