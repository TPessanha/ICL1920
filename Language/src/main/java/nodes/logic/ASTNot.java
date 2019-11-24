package nodes.logic;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import nodes.ASTExpression;
import nodes.ASTOperation;
import nodes.Node;
import state.Environment;
import types.BooleanType;
import types.IType;
import values.BooleanValue;
import values.IValue;

public class ASTNot extends ASTExpression implements ASTOperation {
	private static final String operator = "~";
	private Node node;

	public ASTNot(Node node) {
		this.node = node;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue v = node.eval(environment);

		return ((BooleanValue) v).not();
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = node.compile(environment);
		code.emit_iconst(1);
		code.emit_ixor();
		code.emit_blank();
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = node.typecheck(environment);

		if (t1 instanceof BooleanType)
			return setType(BooleanType.value);
		throw new IllegalOperatorException(t1.getName(), getOperator());
	}

	@Override
	public String getOperator() {
		return operator;
	}
}
