package state;

import types.IType;

public class Parameter {
	String identifier;
	IType type;

	public Parameter(String identifier, IType type) {
		this.identifier = identifier;
		this.type = type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public IType getType() {
		return type;
	}
}
