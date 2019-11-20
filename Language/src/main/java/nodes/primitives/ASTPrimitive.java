package nodes.primitives;

import exceptions.NullTypecheckException;
import nodes.ASTNode;
import state.Environment;
import types.IType;
import values.IValue;

public abstract class ASTPrimitive<v extends IValue> extends ASTNode {
	protected final v value;

	protected ASTPrimitive(v value) {
		this.value = value;
		setType(value.getType());
	}

	@Override
	public v eval(Environment<IValue<?>> environment) {
		return value;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws NullTypecheckException {
		return getType();
	}
}
