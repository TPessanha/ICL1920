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

public class ASTAsType extends ASTNode implements ASTOperation {
	private static final String operator = "as";
	private ASTNode expression;
	private IType toType;

	public ASTAsType(ASTNode expression, IType toType) {
		this.expression = expression;
		this.toType = toType;
		setType(toType);
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IType type = expression.getType();
		IValue retValue = expression.eval(environment);

		if (!type.getName().equals(toType))
			switch (toType.getName()) {
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

		if (!(from instanceof NumberType))
			throw new IllegalCastException(from.getName(), toType.getName());

		CodeBlock code = expression.compile(environment);

		if (!from.getName().equals(toType.getName()))
			code.emit_convert(((NumberType) from).getConversionLiteral(), ((NumberType) toType).getConversionLiteral());

		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		expression.typecheck(environment);
		return getType();
	}

	@Override
	public String getOperator() {
		return operator;
	}
}
