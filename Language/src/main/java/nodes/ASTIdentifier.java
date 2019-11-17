package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import compiler.IdentifierDetails;
import exceptions.UndeclaredException;
import state.Environment;
import types.IType;
import values.IValue;

public class ASTIdentifier extends ASTExpression {
	private final String name;

	public ASTIdentifier(String name) {
		this.name = name;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> env) throws Exception {
		return env.find(name);
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = new CodeBlock();

		IdentifierDetails details = environment.find(name);

		int level = environment.getLevel();
		int SL = environment.getSL();

		code.emit_comment("Identifier "+ name);
		code.emit_aload(SL);

		int i;
		for (i = level; i > details.getLevel(); i--) {
			code.emit_getField("frame_" + i + "/sl", "Lframe_" + (i-1) + ";");
		}
		code.emit_getField("frame_" + i + "/" + details.getName(), details.getType().getJVMClass());
		code.emit_blank();
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws UndeclaredException {
		return setType(environment.find(name));
	}
}
