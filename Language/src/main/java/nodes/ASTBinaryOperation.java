package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.UndeclaredTypeException;
import state.Environment;
import values.IValue;

public abstract class ASTBinaryOperation extends ASTBinaryNode implements ASTOperation {
	protected final String operator;

	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = lNode.compile(environment);
		code.append(rNode.compile(environment));
		code.append(emitOperation());
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

	public abstract CodeBlock emitOperation() throws UndeclaredTypeException;

	public String getOperator() {
		return operator;
	}
}
