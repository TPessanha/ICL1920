package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import exceptions.TypeMismatchException;
import state.Environment;
import types.IType;
import types.NumberType;
import types.UndefinedType;
import values.NumberValue;
import values.IValue;

public abstract class ASTMath implements ASTNode {
	protected final ASTNode lNode, rNode;

	protected ASTMath(ASTNode lNode, ASTNode rNode) {
		this.lNode = lNode;
		this.rNode = rNode;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue v1 = lNode.eval(environment);
		IValue v2 = rNode.eval(environment);

		if (v1 instanceof NumberValue && v2 instanceof NumberValue)
			return doOperation(v1, v2);
		throw new IllegalOperatorException(getOperator(), v1.getTypeName(), v2.getTypeName());
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = lNode.compile(environment);
		code.appendCodeBlock(rNode.compile(environment));
		code.appendCodeBlock(emitOperation());
		return code;
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

	abstract CodeBlock emitOperation();

	abstract NumberValue doOperation(IValue v1, IValue v2);

	abstract String getOperator();
}
