package nodes;

import types.AnyType;
import types.IType;

public abstract class ASTExpression extends ASTStatement {

	public ASTExpression() {
		super(AnyType.value);
	}

	public ASTExpression(IType type) {
		super(type);
	}
}
