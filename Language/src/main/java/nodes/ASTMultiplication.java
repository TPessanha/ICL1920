package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.IllegalOperatorException;
import state.Environment;
import types.IType;
import types.NumberType;
import types.UndefinedType;
import values.NumberValue;
import values.Value;

public class ASTMultiplication implements ASTNode {
    private final ASTNode lNode, rNode;

    public ASTMultiplication(ASTNode lNode, ASTNode rNode) {
        this.lNode = lNode;
        this.rNode = rNode;
    }

    public Value eval(Environment env) throws Exception {
        Value v1 = lNode.eval(env);
        Value v2 = rNode.eval(env);

        if (v1 instanceof NumberValue && v2 instanceof NumberValue)
            return ((NumberValue) v1).Multiply((NumberValue) v2);
        throw new IllegalOperatorException("*", v1.getTypeName(), v2.getTypeName());
    }

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = lNode.compile(environment);
		code.appendCodeBlock(rNode.compile(environment));
		code.emit_imul();
		code.emit_blank();
		return code;
	}

	@Override
	public IType typecheck() throws Exception {
		IType t1 = lNode.typecheck();
		IType t2 = rNode.typecheck();
		if (t1 instanceof NumberType && t2 instanceof NumberType)
			return t1.getType();
		else
			return UndefinedType.value;
	}
}
