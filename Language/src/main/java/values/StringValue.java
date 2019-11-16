package values;

import types.IType;
import types.StringType;

public class StringValue implements IValue<String> {
	private final String value;

	public StringValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getTypeName() {
		return "String";
	}

	@Override
	public IType getType() {
		return StringType.value;
	}

	@Override
	public int compareTo(IValue anotherString) {
		return getValue().compareTo((String) anotherString.getValue());
	}

	@Override
	public boolean equals(IValue anotherString) {
		return getValue().equals(anotherString.getValue());
	}
}

