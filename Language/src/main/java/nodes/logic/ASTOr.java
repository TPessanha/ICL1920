package nodes.logic;

import compiler.CodeBlock;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import nodes.ASTBinaryNode;
import nodes.ASTNode;
import nodes.ASTOperation;
import state.Environment;
import types.BooleanType;
import types.IType;
import values.BooleanValue;
import values.IValue;

public class ASTOr extends ASTBinaryNode implements ASTOperation {
	private static final String operator = "||";

	public ASTOr(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode);
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		if (((BooleanValue) lNode.eval(environment)).getValue() || ((BooleanValue) rNode.eval(environment)).getValue())
			return BooleanValue.TRUE;

		return BooleanValue.FALSE;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		String l1 = Compiler.generateUniqueLabel();
		String l2 = Compiler.generateUniqueLabel();
		String l3 = Compiler.generateUniqueLabel();

		CodeBlock code = new CodeBlock();

		code.appendCodeBlock(lNode.compile(environment));
		code.emit_if_not_equal(l1);
		code.appendCodeBlock(rNode.compile(environment));
		code.emit_if_equal(l2);
		code.emit_label(l1);
		code.emit_boolean(true);
		code.emit_goto(l3);
		code.emit_label(l2);
		code.emit_boolean(false);
		code.emit_label(l3);
		code.emit_blank();

		return code;
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
	public String getOperator() {
		return operator;
	}
}
