package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import values.BooleanValue;
import values.IValue;

public class ASTEquals extends ASTRelation {
	public final static String operator = "==";

	public ASTEquals(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() {
		return null;
	}


	@Override
	public BooleanValue basicOperation(IValue v1, IValue v2) {
		if (v1.equals(v2))
			return BooleanValue.TRUE;
		return BooleanValue.FALSE;
	}
}
