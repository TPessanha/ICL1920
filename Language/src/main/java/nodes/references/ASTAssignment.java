package nodes.references;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import nodes.ASTBinaryNode;
import nodes.ASTNode;
import nodes.ASTOperation;
import state.Environment;
import types.IType;
import types.ReferenceType;
import types.UndefinedType;
import values.IValue;
import values.ReferenceValue;

public class ASTAssignment extends ASTBinaryNode implements ASTOperation {
	private static final String operator = ":=";

	public ASTAssignment(ASTNode reference, ASTNode expression) {
		super(reference, expression);
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		ReferenceValue ref = (ReferenceValue) lNode.eval(environment);
		IValue value = rNode.eval(environment);
		ref.setValue(value);

		return ref;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		IType t1 = lNode.getType();
		IType t2 = rNode.getType();

		if (this.getType() instanceof UndefinedType || !((ReferenceType) t1).getReferenceType().getName().equals(t2.getName()))
			throw new IllegalOperatorException(getOperator(), t1.getName(), t2.getName());

		CodeBlock code = lNode.compile(environment);
		code.emit_checkcast(t1.getClassName());
		code.appendCodeBlock(rNode.compile(environment));
		code.emit_putField(t1.getClassName() + "/value", t2.getJVMType());
		code.emit_blank();

		return code;
	}


	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType refType = lNode.typecheck(environment);

		if (!(refType instanceof ReferenceType))
			return UndefinedType.value;

		return setType(new ReferenceType<>(rNode.typecheck(environment)));
	}

	@Override
	public String getOperator() {
		return operator;
	}
}
