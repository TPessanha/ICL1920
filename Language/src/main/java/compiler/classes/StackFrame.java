package compiler.classes;

import compiler.Compiler;
import compiler.CompilerEnvironment;
import compiler.IdentifierDetails;

import java.util.List;

public class StackFrame extends ClassFile {

	private StackFrame(String className, String superName, List<IdentifierDetails> fields) {
		super(className, superName, fields);
	}

	private StackFrame(String className, List<IdentifierDetails> fields) {
		super(className, fields);
	}

	private StackFrame(String className) {
		super(className);
	}

	public StackFrame(CompilerEnvironment env, StackFrame parentFrame) {
		this(Compiler.generateUniqueScope(), env.getValues());
		this.superName = "L" + parentFrame.getClassName() + ";";
	}

	public StackFrame(CompilerEnvironment env) {
		this(Compiler.generateUniqueScope(), env.getValues());
		this.superName = "Ljava/lang/Object;";
	}

	public void addField(IdentifierDetails field)
	{
		this.fields.add(field);
	}


}
