package nodes.conditional;

import compiler.CodeBlock;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import exceptions.IncompatibleTypeException;
import nodes.ASTAsType;
import nodes.ASTExpression;
import nodes.ASTNode;
import state.Environment;
import types.*;
import values.IValue;

public class ASTTernary extends ASTExpression {
	protected ASTNode condition, positiveExpression, negativeExpression;
	private boolean sameTypeCheck;

	public ASTTernary(ASTNode condition, ASTNode positiveExpression, ASTNode negativeExpression) {
		this.condition = condition;
		this.positiveExpression = positiveExpression;
		this.negativeExpression = negativeExpression;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		if ((Boolean) condition.eval(environment).getValue())
			return positiveExpression.eval(environment);
		return negativeExpression.eval(environment);
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		String l1 = Compiler.generateUniqueLabel();
		String l2 = Compiler.generateUniqueLabel();

		CodeBlock code = condition.compile(environment);
		code.emit_if_equal(l1);

		code.tabify();
		code.appendCodeBlock(positiveExpression.compile(environment));
		IType t1 = positiveExpression.getType();
		if (!sameTypeCheck && t1 instanceof PrimitiveType)
			code.emit_valueOf(t1);
		code.emit_goto(l2);
		code.detabify();

		code.emit_label(l1);

		code.tabify();
		code.appendCodeBlock(negativeExpression.compile(environment));
		IType t2 = negativeExpression.getType();
		if (!sameTypeCheck && t2 instanceof PrimitiveType)
			code.emit_valueOf(t2);
		code.detabify();

		code.emit_label(l2);
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType condType = condition.typecheck(environment);

		if (!(condType instanceof BooleanType))
			throw new IncompatibleTypeException(BooleanType.value, condType);

		IType positiveType = positiveExpression.typecheck(environment);
		IType negativeType = negativeExpression.typecheck(environment);

		if (positiveType.equals(negativeType)) {
			sameTypeCheck = true;
			return setType(positiveType);
		} else if (positiveType instanceof NumberType && negativeType instanceof NumberType) {
			int priority1 = ((NumberType) positiveType).getPriorityLevel();
			int priority2 = ((NumberType) negativeType).getPriorityLevel();

			sameTypeCheck = true;

			if (priority1 > priority2) {
				negativeExpression = new ASTAsType(negativeExpression, positiveType);
				return setType(positiveType);
			} else if (priority1 < priority2) {
				positiveExpression = new ASTAsType(positiveExpression, negativeType);
				return setType(negativeType);
			}
		}
		sameTypeCheck = false;
//		throw new UnsupportedFeatureException("IfElseThen expression with different return types is not yet implemented sorry for the inconvenience");
		return setType(AnyType.value);
	}
}
