public class TypedResult {
	private String value;
	private String type;

	public TypedResult(String value, String type) {
		this.value = value;
		this.type = type;
	}

	public TypedResult(String valueType) {
		this.value = valueType.split(":")[0];
		this.type = valueType.split(":")[1];
	}

	public String getValue() {
		return value;
	}

	public String getType() {
		return type;
	}
}
