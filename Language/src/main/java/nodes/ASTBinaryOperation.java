package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import values.IValue;

public abstract class ASTBinaryOperation implements ASTNode {
	public final String operator;
	protected final ASTNode lNode, rNode;

	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = lNode.compile(environment);
		code.appendCodeBlock(rNode.compile(environment));
		code.appendCodeBlock(emitOperation());
		return code;
	}

	public ASTBinaryOperation(ASTNode lNode, ASTNode rNode, String operator) {
		this.lNode = lNode;
		this.rNode = rNode;
		this.operator = operator;
	}

	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue v1 = lNode.eval(environment);
		IValue v2 = rNode.eval(environment);

		return doOperation(v1, v2);
	}

	public abstract CodeBlock emitOperation();

	public abstract IValue doOperation(IValue v1, IValue v2) throws Exception;
}
