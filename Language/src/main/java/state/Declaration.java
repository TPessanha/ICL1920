package state;

import nodes.ASTNode;
import exceptions.DuplicatedIdentifierException;
import values.Value;

public class Declaration {
    private String identifier;
    private ASTNode value;

    public Declaration(String identifier, ASTNode value) {
        this.identifier = identifier;
        this.value = value;
    }

    public void declareIn(Environment<Value<?>> scope) throws Exception {
        scope.declareVariable(identifier,value.eval(scope));
    }

    public String getIdentifier() {
        return identifier;
    }

    public ASTNode getValue() {
        return value;
    }
}
