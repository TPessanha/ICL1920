package nodes;

import types.IType;
import types.VoidType;

public abstract class ASTStatement extends ASTNode {

	public ASTStatement() {
		super(VoidType.value);
	}

	protected ASTStatement(IType type) {
		super(type);
	}
}
