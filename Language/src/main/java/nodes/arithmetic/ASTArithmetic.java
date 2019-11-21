package nodes.arithmetic;

import exceptions.IllegalOperatorException;
import nodes.ASTAsType;
import nodes.ASTBinaryOperation;
import nodes.ASTNode;
import state.Environment;
import types.IType;
import types.NumberType;
import values.IValue;

public abstract class ASTArithmetic extends ASTBinaryOperation {
	protected ASTArithmetic(ASTNode lNode, ASTNode rNode, String operator) {
		super(lNode, rNode, operator);
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = lNode.typecheck(environment);
		IType t2 = rNode.typecheck(environment);
		if (t1 instanceof NumberType && t2 instanceof NumberType) {
			int priority1 = ((NumberType) t1).getPriorityLevel();
			int priority2 = ((NumberType) t2).getPriorityLevel();

			if (priority1 > priority2) {
				rNode = new ASTAsType(rNode, lNode.getType());
				return setType(t1.getType());
			} else {
				lNode = new ASTAsType(lNode, rNode.getType());
				return setType(t2.getType());
			}
		} else
			throw new IllegalOperatorException(getOperator(), t1.getName(), t2.getName());
	}

	@Override
	protected abstract IValue doOperation(IValue v1, IValue v2) throws Exception;
}
