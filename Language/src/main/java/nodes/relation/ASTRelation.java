package nodes.relation;

import compiler.CodeBlock;
import compiler.Compiler;
import exceptions.IllegalOperatorException;
import exceptions.NullTypecheckException;
import nodes.ASTBinaryOperation;
import nodes.ASTExpression;
import state.Environment;
import types.BooleanType;
import types.IType;
import types.NumberType;
import types.UndefinedType;
import values.BooleanValue;
import values.FloatValue;
import values.IValue;
import values.NumberValue;

public abstract class ASTRelation extends ASTBinaryOperation {
	ASTRelation(ASTExpression lNode, ASTExpression rNode, String operator) {
		super(lNode, rNode, operator);
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = lNode.typecheck(environment);
		IType t2 = rNode.typecheck(environment);

		if (t1.equals(t2) || t1 instanceof NumberType && t2 instanceof NumberType)
			return setType(BooleanType.value);
		return UndefinedType.value;
	}

	@Override
	public IValue doOperation(IValue v1, IValue v2) throws Exception {
		if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
			int priority1 = ((NumberType) v1.getType()).getPriorityLevel();
			int priority2 = ((NumberType) v2.getType()).getPriorityLevel();
			if (priority1 < priority2)
				v1 = new FloatValue(((Number) v1.getValue()).floatValue());
			else if (priority1 > priority2)
				v2 = new FloatValue(((Number) v2.getValue()).floatValue());

			return basicOperation(v1, v2);
		} else if (v1.getType().equals(v2.getType()) && operator.equals("==") || operator.equals("!="))
			return basicOperation(v1, v2);
		throw new IllegalOperatorException(operator, v1.getTypeName(), v2.getTypeName());
	}

	public abstract BooleanValue basicOperation(IValue v1, IValue v2);

	@Override
	public CodeBlock emitOperation() throws NullTypecheckException {
		CodeBlock code = new CodeBlock();
		switch (lNode.getType().getTypeName()) {
			case "int":
			case "boolean":
				code.appendCodeBlock(integerCompare());
				break;
			case "float":
				code.appendCodeBlock(floatCompare());
				break;
		}
		code.emit_blank();
		return code;
	}

	private CodeBlock floatCompare(){
		String l1 = Compiler.generateUniqueLabel();
		String l2 = Compiler.generateUniqueLabel();

		CodeBlock code = new CodeBlock();
		code.emit_float_compare();
		code.appendCodeBlock(floatJumpCondition(l1));
		code.emit_boolean(true);
		code.emit_goto(l2);
		code.emit_label(l1);
		code.emit_boolean(false);
		code.emit_label(l2);

		return code;
	}

	private CodeBlock integerCompare(){
		String l1 = Compiler.generateUniqueLabel();
		String l2 = Compiler.generateUniqueLabel();

		CodeBlock code = new CodeBlock();
		code.appendCodeBlock(intJumpCondition(l1));
		code.emit_boolean(true);
		code.emit_goto(l2);
		code.emit_label(l1);
		code.emit_boolean(false);
		code.emit_label(l2);
		return code;
	}

	protected abstract CodeBlock floatJumpCondition(String label);
	protected abstract CodeBlock intJumpCondition(String label);

}
