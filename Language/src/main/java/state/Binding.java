package state;

import nodes.Node;

public class Binding {
    private String identifier;
    private Node expression;

    public Binding(String identifier, Node expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Node getExpression() {
        return expression;
    }
}
