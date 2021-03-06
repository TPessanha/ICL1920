package nodes.references;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import exceptions.IncompatibleTypeException;
import nodes.ASTBinaryNode;
import nodes.ASTNode;
import nodes.ASTOperation;
import state.Environment;
import types.IType;
import types.ReferenceType;
import types.UndefinedType;
import types.VoidType;
import values.IValue;
import values.ReferenceValue;
import values.VoidValue;

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

		return new VoidValue();
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		IType t1 = lNode.getType();
		IType t2 = rNode.getType();

		if (this.getType() instanceof UndefinedType || !((ReferenceType) t1).getReferenceType().getName()
			.equals(t2.getName()))
			throw new IllegalOperatorException(getOperator(), t1.getName(), t2.getName());

		CodeBlock code = lNode.compile(environment);
		code.emit_checkcast(t1.getClassName());
//		code.emit_duplicate();
		code.append(rNode.compile(environment));
		code.emit_putField(t1.getClassName() + "/value", t2.getJVMType());
		code.emit_blank();

		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType refType = lNode.typecheck(environment);
		IType expressionType = rNode.typecheck(environment);

		if (!(refType instanceof ReferenceType))
			throw new IncompatibleTypeException(ReferenceType.value, refType.getType());

		if (!((ReferenceType) refType).getReferenceType().equals(expressionType))
			throw new IncompatibleTypeException(((ReferenceType) refType).getReferenceType(), expressionType);

		return setType(VoidType.value);
//		return setType(new ReferenceType<>(expressionType));
	}

	@Override
	public String getOperator() {
		return operator;
	}
}
