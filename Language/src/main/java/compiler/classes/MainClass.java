package compiler.classes;

import compiler.CodeBlock;

public class MainClass extends ClassFile {
	protected int SL;
	private int stackSize, localsSize;

	public MainClass(int stackSize, int localsSize, String className, int SL) {
		super(className);
		this.SL = SL;
		this.stackSize=stackSize;
		this.localsSize=localsSize;

	}

	public void setBody(CodeBlock body)
	{
		this.body=body;
	}

	@Override
	public void close() {
		writeHeader();
		writeDefaultConstructor();
		writeMain(localsSize, stackSize);
		code.append(body);
		writeMainFooter();
	}

	public MainClass(int stackSize, int localsSize, String className) {
		this(stackSize, localsSize, className, 4);
	}

	private void writeMain(int localsSize, int stackSize) {
		code.append(".method public static main([Ljava/lang/String;)V");
		code.append("; set limits used by this method");
		code.append(".limit locals " + localsSize);
		code.append(".limit stack " + stackSize);
		code.append("");
		code.append("; setup local variables:");
		code.append("");
		code.append("; START =================================================");
		code.append("");
		code.append("; initialize frame pointer SL stored in local #SL to null");
		code.append("aconst_null");
		code.append("astore " + SL);
		code.append("");
		code.append("; initialize base Stack Frame");
		code.append("new frame_0");
		code.append("dup");
		code.append("invokespecial frame_0/<init>()V");
		code.append("dup");
		code.append("aload " + SL);
		code.append("putfield frame_0/SL Ljava/lang/Object;");
		code.append("astore " + SL);
		code.append("");
	}

	public void writeMainFooter() {
		code.append("");
		code.append("; END ===================================================");
		code.append("");
		code.append("return");
		code.append(".end method");
	}
}
