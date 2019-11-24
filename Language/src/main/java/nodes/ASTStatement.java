package nodes;

import exceptions.IllegalTypeException;
import types.IType;
import types.VoidType;
import values.IValue;
import values.VoidValue;

public abstract class ASTStatement extends ASTExpression {

	public ASTStatement() {
		super(VoidType.value);
	}

	@Override
	protected IType setType(IType type) throws IllegalTypeException {
		throw new IllegalTypeException("Statements only return void type");
	}

	public IValue getValue() {
		return new VoidValue();
	}
}
