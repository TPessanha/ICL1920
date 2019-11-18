package values;

import exceptions.DividedByZeroException;

/**
 * Description:
 */
public abstract class NumberValue<T> implements IValue<T>, Comparable<IValue<T>> {
    protected java.lang.Number value;

    public abstract NumberValue add(NumberValue number);

    public abstract NumberValue subtract(NumberValue number);

    public abstract NumberValue multiply(NumberValue number);

    public abstract NumberValue divide(NumberValue number) throws DividedByZeroException;

    public abstract NumberValue negate();
}
