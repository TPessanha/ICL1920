package compiler;

public class IdentifierDetails {
	private String type;
	private int level;
	private String name;

	public IdentifierDetails(String type, int level, String name) {
		this.type = type;
		this.level = level;
		this.name = name;
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
