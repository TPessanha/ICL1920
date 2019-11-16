package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import values.IValue;

public class ASTPrintln implements ASTNode {
	private ASTExpression expression;

	public ASTPrintln(ASTExpression expression) {
		this.expression = expression;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue value = expression.eval(environment);
		System.out.println(value.getValue());
		return value;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = expression.compile(environment);
		code.emit_println(expression.typecheck(new Environment<>()).getJVMType());
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		return expression.typecheck(environment);
	}
}
