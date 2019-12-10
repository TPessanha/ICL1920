package nodes.references;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import nodes.ASTExpression;
import nodes.ASTOperation;
import nodes.Node;
import state.Environment;
import types.IType;
import types.ReferenceType;
import values.IValue;
import values.ReferenceValue;

public class ASTDereference extends ASTExpression implements ASTOperation {
	private static final String operator = "!";
	private final Node reference;

	public ASTDereference(Node reference) {
		this.reference = reference;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		ReferenceValue r = (ReferenceValue) reference.eval(environment);
		return (IValue<?>) r.getValue();
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		ReferenceType rType = new ReferenceType<>(getType());
		String className = rType.getClassName();

		CodeBlock code = reference.compile(environment);
		code.emit_comment("Dereference");
		code.emit_checkcast(className);
		code.emit_getField(className + "/value", getType().getJVMType());
		code.emit_blank();
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t = reference.typecheck(environment);
		if (!(t instanceof ReferenceType))
			throw new IllegalOperatorException(operator, t.getName());

		return setType(((ReferenceType) t).getReferenceType());
	}

	@Override
	public String getOperator() {
		return operator;
	}
}
