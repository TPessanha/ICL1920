package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.DividedByZeroException;
import exceptions.IllegalOperatorException;
import state.Environment;
import types.IType;
import types.NumberType;
import types.UndefinedType;
import values.NumberValue;
import values.IValue;

public abstract class ASTArithmetic extends ASTBinaryOperation {
	protected ASTArithmetic(ASTExpression lNode, ASTExpression rNode, String operator) {
		super(lNode, rNode, operator);
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = lNode.typecheck(environment);
		IType t2 = rNode.typecheck(environment);
		if (t1 instanceof NumberType && t2 instanceof NumberType) {
			int priority1 = ((NumberType) t1).getPriorityLevel();
			int priority2 = ((NumberType) t2).getPriorityLevel();

			if (priority1 > priority2)
				return setType(t1.getType());
			return setType(t2.getType());
		} else
//			throw new IllegalOperatorException(operator,t1.getTypeName(),t2.getTypeName());
			return setType(UndefinedType.value);
	}

	public abstract IValue basicOperation(IValue v1, IValue v2) throws DividedByZeroException;
}
