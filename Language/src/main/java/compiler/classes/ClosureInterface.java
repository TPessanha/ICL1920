package compiler.classes;

import types.FunctionType;
import types.IType;

public class ClosureInterface extends CompilerFile {
	private String interfaceName;
	private String methodCall;

	public ClosureInterface(FunctionType func) {
		this.interfaceName = getInterfaceName(func);
		this.methodCall = getMethodCall(func);
	}

	@Override
	public void close() {
		code.append(".interface public " + interfaceName);
		code.append(".super java/lang/Object");
		code.append(".method public abstract " + methodCall);
		code.append(".end method");
	}

	public static String getMethodCall(FunctionType func) {
		StringBuilder builder = new StringBuilder();
		builder.append("call(");
		for (IType t : func.getParameters()) {
			builder.append(t.getJVMType());
		}
		builder.append(")");
		builder.append(func.getReturnType().getJVMType());

		return builder.toString();
	}

	@Override
	public String getFileName() {
		return interfaceName + ".j";
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public static String getInterfaceName(FunctionType func) {
		StringBuilder builder = new StringBuilder();
		builder.append("closure_interface_");

		for (IType t : func.getParameters()) {
			builder.append(t.getName() + "x");
		}
		builder.deleteCharAt(builder.length() - 1);

		builder.append("_2_" + func.getReturnType().getName());

		return builder.toString();
	}

	public String getMethodCall() {
		return methodCall;
	}
}
