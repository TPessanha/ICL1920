package nodes.conditional;

import compiler.CodeBlock;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import exceptions.IncompatibleTypeException;
import nodes.ASTNode;
import nodes.ASTStatement;
import state.Environment;
import types.BooleanType;
import types.IType;
import values.IValue;

public class ASTWhile extends ASTStatement {
	ASTNode condition, body;

	public ASTWhile(ASTNode condition, ASTNode body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		while ((Boolean) condition.eval(environment).getValue()) {
			body.eval(environment);
		}

		return getValue();
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		String l1 = Compiler.generateUniqueLabel();
		String l2 = Compiler.generateUniqueLabel();

		CodeBlock code = new CodeBlock();

		code.emit_label(l1);
		code.appendCodeBlock(condition.compile(environment));
		code.emit_if_equal(l2);

		code.tabify();
		code.appendCodeBlock(body.compile(environment));
		code.emit_goto(l1);
		code.detabify();

		code.emit_label(l2);

		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType condType = condition.typecheck(environment);

		if (!(condType instanceof BooleanType))
			throw new IncompatibleTypeException(BooleanType.value, condType);

		body.typecheck(environment);

		return getType();
	}
}
