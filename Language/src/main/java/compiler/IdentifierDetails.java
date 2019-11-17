package compiler;

import types.IType;

public class IdentifierDetails {
	private IType type;
	private int level;
	private String name;

	public IdentifierDetails(IType type, int level, String name) {
		this.type = type;
		this.level = level;
		this.name = name;
	}

	public IType getType() {
		return type;
	}

	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}
}
