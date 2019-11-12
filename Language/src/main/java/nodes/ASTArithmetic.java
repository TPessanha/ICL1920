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
	protected ASTArithmetic(ASTNode lNode, ASTNode rNode, String operator) {
		super(lNode, rNode, operator);
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = lNode.typecheck(environment);
		IType t2 = rNode.typecheck(environment);
		if (t1 instanceof NumberType && t2 instanceof NumberType)
			return t1.getType();
		else
			return UndefinedType.value;
	}

	public IValue doOperation(IValue v1, IValue v2) throws IllegalOperatorException, DividedByZeroException {
		if (v1 instanceof NumberValue && v2 instanceof NumberValue)
			return basicOperation(v1, v2);
		throw new IllegalOperatorException(operator, v1.getTypeName(), v2.getTypeName());
	}

	public abstract IValue basicOperation(IValue v1, IValue v2) throws DividedByZeroException;
}
