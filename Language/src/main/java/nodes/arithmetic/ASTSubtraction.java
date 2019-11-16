package nodes.arithmetic;

import compiler.CodeBlock;
import exceptions.NullTypecheckException;
import nodes.ASTExpression;
import types.NumberType;
import values.IValue;
import values.NumberValue;

public class ASTSubtraction extends ASTArithmetic {
	public final static String operator = "-";

	public ASTSubtraction(ASTExpression lNode, ASTExpression rNode) {
		super(lNode, rNode, operator);
	}

	@Override
	public CodeBlock emitOperation() throws NullTypecheckException {
		CodeBlock code = new CodeBlock();
		code.emit_sub(((NumberType) getType()).getConversionLiteral());
		code.emit_blank();
		return code;
	}

	@Override
	public IValue basicOperation(IValue v1, IValue v2) {
		return ((NumberValue) v1).subtract((NumberValue) v2);
	}

}
