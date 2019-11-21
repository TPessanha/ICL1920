package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.NullTypecheckException;
import state.Environment;
import types.IType;
import values.IValue;

public abstract class ASTBinaryOperation extends ASTBinaryNode implements ASTOperation {
	protected final String operator;

	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		IType t1 = lNode.getType();
		IType t2 = rNode.getType();

		CodeBlock code = lNode.compile(environment);
		code.appendCodeBlock(rNode.compile(environment));
		code.appendCodeBlock(emitOperation());
		return code;
	}

	public ASTBinaryOperation(ASTNode lNode, ASTNode rNode, String operator) {
		super(lNode, rNode);
		this.operator = operator;
	}

	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue v1 = lNode.eval(environment);
		IValue v2 = rNode.eval(environment);

		return doOperation(v1, v2);
	}

	protected abstract IValue doOperation(IValue v1, IValue v2) throws Exception;

	public abstract CodeBlock emitOperation() throws NullTypecheckException;

	public String getOperator() {
		return operator;
	}
}
