package nodes;

import types.IType;

public abstract class ASTExpression extends ASTNode {

	public ASTExpression() {
	}

	public ASTExpression(IType type) {
		super(type);
	}
}
