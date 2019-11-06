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

public class ASTDivision implements ASTNode {
    private final ASTNode lNode, rNode;

    public ASTDivision(ASTNode lNode, ASTNode rNode) {
        this.lNode = lNode;
        this.rNode = rNode;
    }

    @Override
    public Value<?> eval(Environment<Value<?>> environment) throws Exception {
        Value v1 = lNode.eval(environment);
        Value v2 = rNode.eval(environment);

        if (v1 instanceof NumberValue && v2 instanceof NumberValue)
            return ((NumberValue) v1).Divide((NumberValue) v2);
        throw new IllegalOperatorException("/", v1.getTypeName(), v2.getTypeName());
    }

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CodeBlock code = lNode.compile(environment);
		code.appendCodeBlock(rNode.compile(environment));
		code.emit_idiv();
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
