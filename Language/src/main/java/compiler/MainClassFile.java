package compiler;

import java.io.*;
import java.nio.file.Paths;

public class MainClassFile extends ClassFile {
	protected int SL;

	public MainClassFile(int stackSize, int localsSize, String className, int SL) {
		super(className);
		this.SL = SL;
		initialize();
		writeMain(localsSize, stackSize);
	}

	public MainClassFile(int stackSize, int localsSize, String className) {
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
		code.appendCodeLine("; 1 - the PrintStream object held in java.lang.System.out");
		code.appendCodeLine("getstatic java/lang/System/out Ljava/io/PrintStream;");
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

	public void writeFooter(String valueType) {
		code.appendCodeLine("");
		code.appendCodeLine("; END ===================================================");
		code.appendCodeLine("");
		code.appendCodeLine("; convert to String;");
		code.appendCodeLine("invokestatic java/lang/String/valueOf(" + valueType + ")Ljava/lang/String;");
		code.appendCodeLine("; call println");
		code.appendCodeLine("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		code.appendCodeLine("");
		code.appendCodeLine("return");
		code.appendCodeLine(".end method");
	}
}
