package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import values.IValue;

public class ASTSequence extends ASTBinaryNode {
	public ASTSequence(Node lNode, Node rNode) {
		super((ASTNode) lNode, (ASTNode) rNode);
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		lNode.eval(environment);
		return rNode.eval(environment);
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = lNode.compile(environment);
		code.appendCodeBlock(rNode.compile(environment));
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		lNode.typecheck(environment);
		return setType(rNode.typecheck(environment));
	}
}
