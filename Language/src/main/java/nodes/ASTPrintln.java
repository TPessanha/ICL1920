package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalTypeException;
import state.Environment;
import types.IType;
import types.VoidType;
import values.IValue;
import values.VoidValue;

import java.io.PrintStream;

public class ASTPrintln extends ASTStatement {
	private ASTNode expression;
	private PrintStream out;

	public ASTPrintln(ASTNode expression) {
		this.expression = expression;
		out = System.out;
	}

	public ASTPrintln(ASTNode expression, PrintStream out)
	{
		this.expression = expression;
		if(out==null)
			this.out = System.out;
		else
			this.out = out;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue value = expression.eval(environment);
		out.println(value);
		return new VoidValue();
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = new CodeBlock();
		code.emit_getstatic("java/lang/System/out","Ljava/io/PrintStream;");
		code.tabify();
		code.append(expression.compile(environment));
		code.detabify();
		code.emit_invoke_println(expression.getType());
		code.emit_blank();
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType printType = expression.typecheck(environment);
		if (printType instanceof VoidType)
			throw new IllegalTypeException("Cannot print 'Void' expressions");
		return VoidType.value;
	}
}
