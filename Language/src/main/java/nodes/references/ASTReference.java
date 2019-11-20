package nodes.references;

import compiler.CodeBlock;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import compiler.classes.ReferenceClass;
import nodes.ASTNode;
import state.Environment;
import types.IType;
import types.ReferenceType;
import values.IValue;
import values.ReferenceValue;

public class ASTReference extends ASTNode {
	private final ASTNode referenceTo;

	public ASTReference(ASTNode referenceTo) {
		this.referenceTo = referenceTo;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		return new ReferenceValue<>(referenceTo.eval(environment));
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		ReferenceClass refClass = new ReferenceClass((ReferenceType) getType());
		Compiler.addClassFile(refClass);

		CodeBlock code = new CodeBlock();
		code.emit_comment("Reference");
		code.emit_new(refClass.getClassName());
		code.emit_duplicate();
		code.emit_invokeSpecial(refClass.getClassName() + "/<init>()V");
		code.emit_duplicate();
		code.appendCodeBlock(referenceTo.compile(environment));
		code.emit_putField(refClass.getClassName() + "/value", referenceTo.getType().getJVMClass());
		code.emit_blank();
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		return setType(new ReferenceType<>(referenceTo.typecheck(environment)));
	}
}
