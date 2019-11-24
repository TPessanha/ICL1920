package values;

import types.IType;

/**
 * Description:
 */
public interface IValue<T> {
    T getValue();

    String getTypeName();

    IType getType();

    String toString();

    boolean equals(IValue<T> value);
}
