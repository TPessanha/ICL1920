package compiler.classes;

import compiler.CodeBlock;
import compiler.IdentifierDetails;

import java.util.ArrayList;
import java.util.List;

public class ClassFile extends CompilerFile {
	protected CodeBlock body;
	protected String className;
	protected String superName;
	protected List<String> implementsI;
	protected List<IdentifierDetails> fields;
	protected List<Method> methods;

	public ClassFile(String className, String superName, List<IdentifierDetails> fields) {
		super();
		this.className = className;
		this.superName = superName;
		implementsI = new ArrayList<>();
		this.fields = fields;
		this.methods = new ArrayList<>();
		body = new CodeBlock();
	}

	protected List<String> getImplementsI() {
		return implementsI;
	}

	protected void addImplements(String implementsI) {
		this.implementsI.add(implementsI);
	}

	@Override
	public void close() {
		writeHeader();
		writeDefaultConstructor();
		writeMethods();
		code.append(body);
	}

	public void addField(IdentifierDetails details){
		fields.add(details);
	}

	public void addMethod(Method m) {
		methods.add(m);
	}

	public List<IdentifierDetails> getFields() {
		return fields;
	}

	public String getClassName() {
		return className;
	}

	public ClassFile(String className, List<IdentifierDetails> fields) {
		this(className, "java/lang/Object", fields);
	}

	public ClassFile(String className, String superName) {
		this(className, superName, new ArrayList<>());
	}

	public ClassFile(String className) {
		this(className, "java/lang/Object", new ArrayList<>());
	}

	protected void writeHeader() {
		code.append(".class public " + getClassName());
		code.append(".super java/lang/Object");

		for (String i: implementsI)
			code.append(".implements " + i);

		if (this instanceof StackFrame)
			code.append(".field public SL " + superName);
		for (IdentifierDetails details : fields)
			code.append(".field public " + details.getName() + " " + details.getType());


		code.emit_blank();
	}

	protected void writeDefaultConstructor() {
		code.append("; standard initializer");
		code.append(".method public <init>()V");
		code.append("aload_0");
		code.append("invokenonvirtual java/lang/Object/<init>()V");
		code.append("return");
		code.append(".end method");
		code.emit_blank();
	}

	private void writeMethods(){
		for (Method m: methods)
		{
			code.append(".method public " + m.getSignature());
			code.append(".limit locals 10");
			code.append(".limit stack 256");
			code.append(m.getBody());
			code.append(m.getReturnValue());
			code.append(".end method");
			code.emit_blank();
		}
	}

	public String getFileName() {
		return getClassName() + ".j";
	}
}
