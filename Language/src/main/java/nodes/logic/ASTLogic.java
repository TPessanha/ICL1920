package nodes.logic;

import exceptions.IllegalOperatorException;
import nodes.ASTBinaryOperation;
import nodes.ASTNode;
import state.Environment;
import types.BooleanType;
import types.IType;
import values.IValue;

public abstract class ASTLogic extends ASTBinaryOperation {
	public ASTLogic(ASTNode lNode, ASTNode rNode, String operator) {
		super(lNode, rNode, operator);
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = lNode.typecheck(environment);
		IType t2 = rNode.typecheck(environment);

		if (t1 instanceof BooleanType && t2 instanceof BooleanType)
			return setType(BooleanType.value);
		throw new IllegalOperatorException(getOperator(), t1.getName(), t2.getName());
	}

	@Override
	public abstract IValue doOperation(IValue v1, IValue v2) throws Exception;
}
