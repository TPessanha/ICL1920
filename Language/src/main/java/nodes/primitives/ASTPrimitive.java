package nodes.primitives;

import nodes.ASTExpression;
import state.Environment;
import types.IType;
import values.FloatValue;
import values.IValue;

public abstract class ASTPrimitive<v extends IValue> extends ASTExpression {
	protected final v value;

	protected ASTPrimitive(v value) {
		this.value = value;
	}

	@Override
	public v eval(Environment<IValue<?>> environment) {
		return value;
	}

	@Override
	public IType typecheck(Environment<IType> environment) {
		return setType(value.getType());
	}
}
