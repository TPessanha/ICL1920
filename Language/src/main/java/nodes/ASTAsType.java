package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalCastException;
import state.Environment;
import types.IType;
import types.NumberType;
import values.FloatValue;
import values.IValue;
import values.IntValue;

public class ASTAsType extends ASTExpression implements ASTOperation {
	private static final String operator = "as";
	private ASTNode expression;

	public ASTAsType(ASTNode expression, IType toType) {
		super(toType);
		this.expression = expression;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IType type = expression.getType();
		IValue retValue = expression.eval(environment);

		if (!type.getName().equals(getType().getName()))
			switch (getType().getName()) {
				case "float":
					return new FloatValue(((Number) retValue.getValue()).floatValue());
				case "int":
					return new IntValue(((Number) retValue.getValue()).intValue());
			}
		return retValue;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		IType from = expression.getType();

		CodeBlock code = expression.compile(environment);

		if (!from.getName().equals(getType().getName()))
			code.emit_convert(from, getType());

		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType from = expression.getType();

		if (!(from instanceof NumberType))
			throw new IllegalCastException(from, getType());

		expression.typecheck(environment);
		return getType();
	}

	@Override
	public String getOperator() {
		return operator;
	}
}
