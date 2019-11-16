package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import exceptions.NullTypecheckException;
import state.Environment;
import types.IType;
import types.NumberType;
import types.UndefinedType;
import values.FloatValue;
import values.IValue;
import values.NumberValue;

public abstract class ASTBinaryOperation extends ASTExpression {
	public final String operator;
	protected ASTExpression lNode, rNode;

	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		IType t1 = lNode.getType();
		IType t2 = rNode.getType();

		if (this.getType() instanceof UndefinedType)
			throw new IllegalOperatorException(operator, t1.getTypeName(), t2.getTypeName());

		if (!t1.equals(t2)) {
			if (t1 instanceof NumberType && t2 instanceof NumberType) {
				int priority1 = ((NumberType) t1).getPriorityLevel();
				int priority2 = ((NumberType) t2).getPriorityLevel();
				if (priority1 > priority2)
					rNode = new ASTCast(rNode, "float");
				else
					lNode = new ASTCast(lNode, "float");
			}
		}

		CodeBlock code = lNode.compile(environment);
		code.appendCodeBlock(rNode.compile(environment));
		code.appendCodeBlock(emitOperation());
		return code;
	}

	public IValue doOperation(IValue v1, IValue v2) throws Exception {
		if (v1.getType().equals(v2.getType()) || v1 instanceof NumberValue && v2 instanceof NumberValue) {
			int priority1 = ((NumberType) v1.getType()).getPriorityLevel();
			int priority2 = ((NumberType) v2.getType()).getPriorityLevel();
			if (priority1 < priority2)
				v1 = new FloatValue(((Number) v1.getValue()).floatValue());
			else if (priority1 > priority2)
				v2 = new FloatValue(((Number) v2.getValue()).floatValue());

			return basicOperation(v1, v2);
		}
		throw new IllegalOperatorException(operator, v1.getTypeName(), v2.getTypeName());
	}

	public ASTBinaryOperation(ASTExpression lNode, ASTExpression rNode, String operator) {
		this.lNode = lNode;
		this.rNode = rNode;
		this.operator = operator;
	}

	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		IValue v1 = lNode.eval(environment);
		IValue v2 = rNode.eval(environment);

		return doOperation(v1, v2);
	}

	public abstract CodeBlock emitOperation() throws NullTypecheckException;

	public abstract IValue basicOperation(IValue v1, IValue v2) throws Exception;
}
