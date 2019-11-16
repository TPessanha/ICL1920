package nodes.logic;

import exceptions.IllegalOperatorException;
import nodes.ASTBinaryOperation;
import nodes.ASTExpression;
import state.Environment;
import types.BooleanType;
import types.IType;
import types.UndefinedType;
import values.BooleanValue;
import values.IValue;

public abstract class ASTLogic extends ASTBinaryOperation {
	public ASTLogic(ASTExpression lNode, ASTExpression rNode, String operator) {
		super(lNode, rNode, operator);
	}

	@Override
	public IValue doOperation(IValue v1, IValue v2) throws Exception {
		if (v1 instanceof BooleanValue && v2 instanceof BooleanValue) {
			return basicOperation(v1, v2);
		}
		throw new IllegalOperatorException(operator, v1.getTypeName(), v2.getTypeName());
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = lNode.typecheck(environment);
		IType t2 = rNode.typecheck(environment);

		if (t1 instanceof BooleanType && t2 instanceof BooleanType)
			return setType(BooleanType.value);
		return UndefinedType.value;
	}
}
