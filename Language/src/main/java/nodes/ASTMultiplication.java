package nodes;

import compiler.CodeBlock;
import exceptions.DividedByZeroException;
import exceptions.NullTypecheckException;
import types.NumberType;
import values.NumberValue;
import values.IValue;

public class ASTMultiplication extends ASTArithmetic {
	public final static String operator = "*";

	public ASTMultiplication(ASTExpression lNode, ASTExpression rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() throws NullTypecheckException {
		CodeBlock code = new CodeBlock();
		code.emit_mul(((NumberType) getType()).getConversionLiteral());
		code.emit_blank();
		return code;
	}

	@Override
	public IValue basicOperation(IValue v1, IValue v2) {
		return ((NumberValue) v1).multiply((NumberValue) v2);
	}
}
