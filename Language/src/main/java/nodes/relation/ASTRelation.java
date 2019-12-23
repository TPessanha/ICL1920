package nodes.relation;

import compiler.CodeBlock;
import compiler.Compiler;
import exceptions.IllegalOperatorException;
import exceptions.UndeclaredTypeException;
import nodes.ASTAsType;
import nodes.ASTBinaryOperation;
import nodes.ASTNode;
import state.Environment;
import types.BooleanType;
import types.IType;
import types.NumberType;
import values.IValue;

public abstract class ASTRelation extends ASTBinaryOperation {
	ASTRelation(ASTNode lNode, ASTNode rNode, String operator) {
		super(lNode, rNode, operator);
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		IType t1 = lNode.typecheck(environment);
		IType t2 = rNode.typecheck(environment);

		if (t1 instanceof NumberType && t2 instanceof NumberType) {
			int priority1 = ((NumberType) t1).getPriorityLevel();
			int priority2 = ((NumberType) t2).getPriorityLevel();

			if (priority1 > priority2) {
				rNode = new ASTAsType(rNode, lNode.getType());
			} else {
				lNode = new ASTAsType(lNode, rNode.getType());
			}
			return setType(BooleanType.value);
		} else if (t1.equals(t2))
			return setType(BooleanType.value);
		throw new IllegalOperatorException(operator, t1.getName(), t2.getName());
	}

	@Override
	public abstract IValue doOperation(IValue v1, IValue v2) throws Exception;

	@Override
	public CodeBlock emitOperation() throws UndeclaredTypeException {
		CodeBlock code = new CodeBlock();
		switch (lNode.getType().getName()) {
			case "int":
			case "boolean":
				code.append(integerCompare());
				break;
			case "float":
				code.append(floatCompare());
				break;
		}
		code.emit_blank();
		return code;
	}

	private CodeBlock floatCompare() {
		String l1 = Compiler.generateUniqueLabel();
		String l2 = Compiler.generateUniqueLabel();

		CodeBlock code = new CodeBlock();
		code.emit_float_compare();
		code.append(floatJumpCondition(l1));
		code.emit_boolean(true);
		code.emit_goto(l2);
		code.emit_label(l1);
		code.emit_boolean(false);
		code.emit_label(l2);

		return code;
	}

	private CodeBlock integerCompare() {
		String l1 = Compiler.generateUniqueLabel();
		String l2 = Compiler.generateUniqueLabel();

		CodeBlock code = new CodeBlock();
		code.append(intJumpCondition(l1));
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
