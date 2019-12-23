package state;

import nodes.Node;
import types.IType;

public class Binding {
	private String identifier;
	private Node expression;
	private IType type;

	public Binding(String identifier, Node expression) {
		this(identifier,expression, null);
	}

	public Binding(String identifier, Node expression, IType type) {
		this.identifier = identifier;
		this.type = type;
		this.expression = expression;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Node getExpression() {
		return expression;
	}

	public IType getType() {
		return type;
	}

	public void setType(IType type) {
		this.type = type;
	}
}
