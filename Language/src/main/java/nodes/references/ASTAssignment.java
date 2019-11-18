package nodes.references;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.NullTypecheckException;
import nodes.ASTBinaryOperation;
import nodes.ASTExpression;
import state.Environment;
import types.IType;
import values.IValue;

public class ASTAssignment extends ASTBinaryOperation {
    private static final String operator = ":=";

    public ASTAssignment(ASTExpression reference, ASTExpression expression) {
    	super(reference, expression, operator);
    }

    @Override
    public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
        return null;
    }

	@Override
	public CodeBlock emitOperation() throws NullTypecheckException {
		return null;
	}

	@Override
	public IValue basicOperation(IValue v1, IValue v2) throws Exception {
		return null;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		return null;
	}

	@Override
	public IType typecheck(Environment<IType> environment) {
		return null;
	}
}
