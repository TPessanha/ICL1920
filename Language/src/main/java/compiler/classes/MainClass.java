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
		code.appendCodeBlock(body);
		writeMainFooter();
	}

	public MainClass(int stackSize, int localsSize, String className) {
		this(stackSize, localsSize, className, 4);
	}

	private void writeMain(int localsSize, int stackSize) {
		code.appendCodeLine(".method public static main([Ljava/lang/String;)V");
		code.appendCodeLine("; set limits used by this method");
		code.appendCodeLine(".limit locals " + localsSize);
		code.appendCodeLine(".limit stack " + stackSize);
		code.appendCodeLine("");
		code.appendCodeLine("; setup local variables:");
		code.appendCodeLine("");
		code.appendCodeLine("; START =================================================");
		code.appendCodeLine("");
		code.appendCodeLine("; initialize frame pointer SL stored in local #SL to null");
		code.appendCodeLine("aconst_null");
		code.appendCodeLine("astore " + SL);
		code.appendCodeLine("");
		code.appendCodeLine("; initialize base Stack Frame");
		code.appendCodeLine("new frame_0");
		code.appendCodeLine("dup");
		code.appendCodeLine("invokespecial frame_0/<init>()V");
		code.appendCodeLine("dup");
		code.appendCodeLine("aload " + SL);
		code.appendCodeLine("putfield frame_0/sl Ljava/lang/Object;");
		code.appendCodeLine("astore " + SL);
		code.appendCodeLine("");
	}

	public void writeMainFooter() {
		code.appendCodeLine("");
		code.appendCodeLine("; END ===================================================");
		code.appendCodeLine("");
		code.appendCodeLine("return");
		code.appendCodeLine(".end method");
	}
}
