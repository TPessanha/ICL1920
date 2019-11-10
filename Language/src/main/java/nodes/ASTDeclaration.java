package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import values.IntValue;
import values.IValue;

public class ASTDeclaration implements ASTNode {
    private final String identifier;

    public ASTDeclaration(String identifier) {
        this.identifier = identifier;
    }
//
//    String getIdentifier() {
//        return identifier;
//    }
//
//    ASTNode getExpression() {
//        return expression;
//    }

    public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
        environment.declareVariable(this.identifier, new IntValue(0));
        return new IntValue(0);
    }

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		return null;
	}

	@Override
	public IType typecheck() {
		return null;
	}
}
