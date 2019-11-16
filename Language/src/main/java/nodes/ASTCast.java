package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalCastException;
import state.Environment;
import types.*;
import values.FloatValue;
import values.IValue;
import values.IntValue;

public class ASTCast extends ASTExpression {
	private ASTExpression expression;
	private String toType;

	public ASTCast(ASTExpression expression, String toType) {
		this.expression = expression;
		this.toType = toType;
		switch (toType) {
			case "float":
				setType(FloatType.value);
				break;
			case "int":
				setType(IntType.value);
				break;
		}
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IType type = expression.getType();
		IValue retValue = expression.eval(environment);

		if (!type.getName().equals(toType))
			switch (toType) {
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
			throw new IllegalCastException(from.getName(), toType);

		CodeBlock code = expression.compile(environment);

		if (!from.getName().equals(toType))
			switch (toType) {
				case "float":
					code.emit_convert(((NumberType) from).getConversionLiteral(), "f");
					break;
				case "int":
					code.emit_convert(((NumberType) from).getConversionLiteral(), "i");
					break;
			}
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		switch (toType) {
			case "float":
				return setType(FloatType.value);
			case "int":
				return setType(IntType.value);
		}
		return setType(UndefinedType.value);
	}
}
