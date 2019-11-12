package state;

import nodes.ASTNode;
import values.IValue;

public class Binding {
    private String identifier;
    private ASTNode expression;

    public Binding(String identifier, ASTNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ASTNode getExpression() {
        return expression;
    }
}
