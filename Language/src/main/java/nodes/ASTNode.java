package nodes;

import exceptions.IllegalTypeException;
import exceptions.NullTypecheckException;
import types.IType;

public abstract class ASTNode implements Node {
	private IType type;

	public ASTNode() {
	}

	public ASTNode(IType type) {
		this.type = type;
	}

	protected IType setType(IType type) throws IllegalTypeException {
		this.type = type;
		return type;
	}

	public IType getType() throws NullTypecheckException {
		if (type == null)
			throw new NullTypecheckException();
		return type;
	}
}
