package nodes;

import compiler.CodeBlock;
import exceptions.DividedByZeroException;
import values.NumberValue;
import values.IValue;

public class ASTDivision extends ASTArithmetic {
	public final static String operator = "/";

	public ASTDivision(ASTNode lNode, ASTNode rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() {
		CodeBlock code = new CodeBlock();
		code.emit_idiv();
		code.emit_blank();
		return code;
	}

	@Override
	public IValue basicOperation(IValue v1, IValue v2) throws DividedByZeroException {
		return ((NumberValue) v1).divide((NumberValue) v2);
	}
}
