package values;

/**
 * Description:
 */
public abstract class NumberValue<T> implements Value<T>{
    protected java.lang.Number value;

    public abstract NumberValue Add(NumberValue value);

    public abstract NumberValue Subtract(NumberValue value);

    public abstract NumberValue Multiply(NumberValue value);

    public abstract NumberValue Divide(NumberValue value);
}
