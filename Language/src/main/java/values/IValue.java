package values;

import types.IType;

/**
 * Description:
 */
public interface IValue<T> extends Comparable<IValue<T>> {
    T getValue();

    String getTypeName();

    IType getType();

    String toString();

    boolean equals(IValue value);
}
