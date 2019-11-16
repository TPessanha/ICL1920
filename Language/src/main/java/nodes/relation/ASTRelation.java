package nodes.relation;

import nodes.ASTBinaryOperation;
import nodes.ASTExpression;
import state.Environment;
import types.BooleanType;
import types.IType;
import types.NumberType;
import types.UndefinedType;
import values.BooleanValue;
import values.IValue;

public abstract class ASTRelation extends ASTBinaryOperation {
	protected ASTRelation(ASTExpression lNode, ASTExpression rNode, String operator) {
		super(lNode, rNode, operator);
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = lNode.typecheck(environment);
		IType t2 = rNode.typecheck(environment);

		if (t1.equals(t2) || t1 instanceof NumberType && t2 instanceof NumberType)
			return setType(BooleanType.value);
		return UndefinedType.value;
	}

	public abstract BooleanValue basicOperation(IValue v1, IValue v2);
}
