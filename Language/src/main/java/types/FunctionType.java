package types;

import java.util.Collections;
import java.util.List;

public class FunctionType extends AnyType {
	private final IType returnType;
	private final List<IType> parameters;

	public FunctionType(IType returnType, List<IType> parameters) {
		this.returnType = returnType;
		this.parameters = parameters;
	}

	public FunctionType() {
		this.returnType = VoidType.value;
		this.parameters = Collections.EMPTY_LIST;
	}

	public FunctionType(List<IType> parameters) {
		this.returnType = VoidType.value;
		this.parameters = parameters;
	}

	public FunctionType(IType returnType) {
		this.returnType = returnType;
		this.parameters = Collections.EMPTY_LIST;
	}

	public IType getReturnType() {
		return returnType;
	}

	public List<IType> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		String retType = returnType.toString();

		String arguments = "";

		if (this.parameters.size() == 1)
			arguments = this.parameters.get(0).toString();
		else if (this.parameters.size() > 1) {
			for (IType par : this.parameters) {
				arguments += par.toString() + ", ";
			}
			arguments = arguments.substring(0, arguments.length() - 2);
		}

		return "(" + arguments + ") -> " + retType;
	}

	@Override
	public String getName() {
		return "function";
	}

	@Override
	public IType getType() {
		return this;
	}

	@Override
	public String getJVMType() {
		return "L" + getJavaClass() + ";";
	}

	@Override
	public String getClassName() {
		return "Object";
	}

	@Override
	public String getJavaClass() {
		return "java/lang/Object";
	}

	@Override
	public boolean equals(IType o) {
		if (this == o) return true;
		if (!(o instanceof FunctionType)) return false;
		FunctionType that = (FunctionType) o;
		return returnType.equals(that.returnType) &&
			parameters.equals(that.parameters);
	}

	@Override
	public String returnValue() {
		return "areturn";
	}
}
