package nodes;

import exceptions.NullTypecheckException;
import types.IType;

public abstract class ASTNode implements Node {
	private IType type;

	protected IType setType(IType type) {
		this.type = type;
		return type;
	}

	public IType getType() throws NullTypecheckException {
		if (type==null)
			throw new NullTypecheckException();
		return type;
	}
}
