package compiler.classes;

import compiler.CodeBlock;
import compiler.IdentifierDetails;
import types.FunctionType;
import types.IType;

public class ClosureClass extends ClassFile {

	public ClosureClass(
		String className,
		ClosureInterface closureInterface,
		StackFrame scope,
		StackFrame ancestor,
		FunctionType func,
		CodeBlock code
	) {
		super(className);
		this.addImplements(closureInterface.getInterfaceName());

		this.addField(new IdentifierDetails(
			"SL",
			"L" + ancestor.getClassName() + ";"
		));
		Method callMethod =
			new Method(
				getSignature(func),
				generateCall(scope, ancestor, code, func),
				func.getReturnType().returnValue()
			);
		this.addMethod(callMethod);
	}
	// .locals " + (func.getParameters().size() + 1)

	private CodeBlock generateCall(StackFrame scope, StackFrame ancestor, CodeBlock exp, FunctionType functionType) {
		CodeBlock code = new CodeBlock();
		code.emit_new(scope.getClassName()); // the function stack frame
		code.emit_duplicate();
		code.emit_invokeSpecial(scope.getClassName() + "/<init>()V");
		code.emit_duplicate();
		code.emit_aload(0); // get this
		code.emit_getField(className + "/SL", "L" + ancestor.getClassName() + ";");
		code.emit_putField(scope.getClassName() + "/SL", "L" + ancestor.getClassName() + ";");

		// load agruments
		for (int i = 1; i <= functionType.getParameters().size(); i++) {
			IType t = functionType.getParameters().get(i - 1);

			code.emit_duplicate();

			code.append(t.loadValue() + " " + i);

//			if (functionType.getParameters().get(i - 1).getType() instanceof PrimitiveType)
				code.emit_putField(
					scope.getClassName() + "/x_" + (i-1),
					functionType.getParameters().get(i - 1).getJVMType()
				);
//			else
//				code.emit_putField(
//					scope.getClassName() + "/x_" + (i - 1),
//					"L" + functionType.getParameters().get(i - 1).getJVMType() + ";"
//				);

		}

		int SL = functionType.getParameters().size() + 2;
		code.emit_astore(SL);
//		code.emit_aload(SL);
//		[[e]]
		code.append(exp);
		return code;
	}

	private String getSignature(FunctionType func) {
		StringBuilder builder = new StringBuilder();
		builder.append("call(");
		for (IType t : func.getParameters()) {
			builder.append(t.getJVMType());
		}
		builder.append(")");
		builder.append(func.getReturnType().getJVMType());

		return builder.toString();
	}
}
