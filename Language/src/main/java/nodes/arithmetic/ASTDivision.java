package nodes.arithmetic;

import compiler.CodeBlock;
import exceptions.DividedByZeroException;
import exceptions.NullTypecheckException;
import nodes.ASTExpression;
import types.NumberType;
import values.IValue;
import values.NumberValue;

public class ASTDivision extends ASTArithmetic {
	public final static String operator = "/";

	public ASTDivision(ASTExpression lNode, ASTExpression rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() throws NullTypecheckException {
		CodeBlock code = new CodeBlock();
		code.emit_div(((NumberType) getType()).getConversionLiteral());
		code.emit_blank();
		return code;
	}

	@Override
	public IValue basicOperation(IValue v1, IValue v2) throws DividedByZeroException {
		return ((NumberValue) v1).divide((NumberValue) v2);
	}
}
