package nodes;

import compiler.CodeBlock;
import values.NumberValue;
import values.IValue;

public class ASTDivision extends ASTMath {
    public ASTDivision(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode);
    }

	@Override
	CodeBlock emitOperation() {
    	CodeBlock code = new CodeBlock();
		code.emit_idiv();
		code.emit_blank();
		return code;
	}

	@Override
	NumberValue doOperation(IValue v1, IValue v2) {
		return ((NumberValue) v1).Divide((NumberValue) v2);
	}

	@Override
	String getOperator() {
		return "/";
	}
}
