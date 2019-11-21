package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import values.IValue;

public class ASTPrintln extends ASTNode {
	private ASTNode expression;

	public ASTPrintln(ASTNode expression) {
		this.expression = expression;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue value = expression.eval(environment);
		System.out.println(value);
		return value;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = new CodeBlock();
		code.emit_getstatic("java/lang/System/out","Ljava/io/PrintStream;");
		code.tabify();
		code.appendCodeBlock(expression.compile(environment));
		code.detabify();
		code.emit_invoke_println(getType());
		code.emit_blank();
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		return setType(expression.typecheck(environment));
	}
}
