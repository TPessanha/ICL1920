package nodes;

import exceptions.IllegalOperatorException;
import state.Environment;
import types.BooleanType;
import types.IType;
import types.NumberType;
import types.UndefinedType;
import values.BooleanValue;
import values.IValue;
import values.NumberValue;

public abstract class ASTRelation extends ASTBinaryOperation {
	protected ASTRelation(ASTNode lNode, ASTNode rNode, String operator) {
		super(lNode, rNode, operator);
	}

	@Override
	public IValue doOperation(IValue v1, IValue v2) throws Exception {
		if (v1 instanceof NumberValue && v2 instanceof NumberValue)
			return basicOperation(v1, v2);
		else if ((operator.equals("==") || operator.equals("!=")) && v1.getType().equals(v2.getType()))
			return basicOperation(v1, v2);
		else
			throw new IllegalOperatorException(operator, v1.getTypeName(), v2.getTypeName());
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = lNode.typecheck(environment);
		IType t2 = rNode.typecheck(environment);

		if (t1 instanceof NumberType && t2 instanceof NumberType)
			return BooleanType.value;
		else if ((operator.equals("==") || operator.equals("!=")) &&
			t1.getType().equals(t2.getType())) {
			return BooleanType.value;
		} else
			return UndefinedType.value;
	}

	public abstract BooleanValue basicOperation(IValue v1, IValue v2);
}
