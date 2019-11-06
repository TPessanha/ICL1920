package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import compiler.IdentifierDetails;
import state.Environment;
import types.IType;
import values.Value;

public class ASTAssignment implements ASTNode {
    private final String identifier;
    private final ASTNode expression;

    public ASTAssignment(String identifier, ASTNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public Value<?> eval(Environment<Value<?>> environment) throws Exception {
        return environment.assignVariable(identifier,expression.eval(environment));
    }

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		return null;
	}

	@Override
	public IType typecheck() throws Exception {
		return null;
	}
}
