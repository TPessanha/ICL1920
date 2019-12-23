package compiler.classes;

import compiler.CodeBlock;

public class Method {
	private String signature;
	private CodeBlock body;
	private String returnValue;

	public Method(String signature, CodeBlock body, String returnValue) {
		this.signature = signature;
		this.body = body;
		this.returnValue = returnValue;
	}

	public String getSignature() {
		return signature;
	}

	public CodeBlock getBody() {
		return body;
	}

	public String getReturnValue() {
		return returnValue;
	}
}
