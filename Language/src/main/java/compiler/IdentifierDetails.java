package compiler;

import types.IType;

public class IdentifierDetails {
	private String name;
	private String type;
	private int level;

	public IdentifierDetails(String name, IType type, int level) {
		this.type = type.getJVMType();
		this.level = level;
		this.name = name;
	}

	public IdentifierDetails(String name, String type, int level) {
		this.name = name;
		this.type = type;
		this.level = level;
	}

	public IdentifierDetails(String name, IType type) {
		this(name, type, 0);
	}

	public IdentifierDetails(String name, String type) {
		this(name, type, 0);
	}

	public String getType() {
		return type;
	}

	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}
}
