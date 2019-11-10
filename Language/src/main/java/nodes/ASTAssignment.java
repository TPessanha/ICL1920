package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import values.IValue;

public class ASTAssignment implements ASTNode {
    private final String identifier;
    private final ASTNode expression;

    public ASTAssignment(String identifier, ASTNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
        return environment.assignVariable(identifier,expression.eval(environment));
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
