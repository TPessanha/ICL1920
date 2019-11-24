package nodes.primitives;

import exceptions.NullTypecheckException;
import nodes.ASTExpression;
import state.Environment;
import types.IType;
import values.IValue;

public abstract class ASTPrimitive<v extends IValue> extends ASTExpression {
	protected final v value;

	protected ASTPrimitive(v value) {
		super(value.getType());
		this.value = value;
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
