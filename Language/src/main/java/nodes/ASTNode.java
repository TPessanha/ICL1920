package nodes;

import exceptions.IllegalTypeException;
import exceptions.UndeclaredTypeException;
import types.IType;
import types.VoidType;

public abstract class ASTNode implements Node {
	private IType type;

	public ASTNode() {
	}

	public ASTNode(IType type) {
		this.type = type;
	}

	protected IType setType(IType type) throws IllegalTypeException {
		if (!(this instanceof ASTExpression) && !(type instanceof VoidType)) {
			throw new IllegalTypeException("Statements must return a 'Void' value");
		}
		this.type = type;
		return this.type;
	}

	public IType getType() throws UndeclaredTypeException {
		if (type == null)
			throw new UndeclaredTypeException();
		return type;
	}
}
