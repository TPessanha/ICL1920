package values;

/**
 * Description:
 */
public abstract class NumberValue<T> implements IValue<T> {
    protected java.lang.Number value;

    public abstract NumberValue Add(NumberValue number);

    public abstract NumberValue Subtract(NumberValue number);

    public abstract NumberValue Multiply(NumberValue number);

    public abstract NumberValue Divide(NumberValue number);
}
