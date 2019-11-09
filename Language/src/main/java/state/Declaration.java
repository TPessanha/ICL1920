package state;

import nodes.ASTNode;
import values.Value;

public class Declaration {
    private String identifier;
    private ASTNode expression;

    public Declaration(String identifier, ASTNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public void declareIn(Environment<Value<?>> scope) throws Exception {
        scope.declareVariable(identifier, expression.eval(scope));
    }

    public String getIdentifier() {
        return identifier;
    }

    public ASTNode getExpression() {
        return expression;
    }
}
