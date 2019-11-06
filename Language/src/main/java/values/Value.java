package values;

import types.IType;

/**
 * Description:
 */
public interface Value<T> extends Comparable<Value<T>> {
    T getValue();

    String getTypeName();

    IType getType();

    String toString();

    Value<T> negate();

    boolean equals(Value<T> value);
}
