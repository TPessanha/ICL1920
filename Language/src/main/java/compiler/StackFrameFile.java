package compiler;

import java.io.PrintStream;
import java.util.Collection;
import java.util.List;

public class StackFrameFile extends ClassFile {

	private StackFrameFile(String className, String superName, Collection<IdentifierDetails> fields) {
		super(className, superName, fields);
	}

	private StackFrameFile(String className, Collection<IdentifierDetails> fields) {
		super(className, fields);
	}

	private StackFrameFile(String className) {
		super(className);
	}

	public StackFrameFile(CompilerEnvironment env) {
		this("frame_" + env.getLevel(), env.getValues());
		int level = env.getLevel();
		if (level==0)
		{
			this.superName = "java/lang/Object";
		}
		else
		{
			this.superName = "frame_"+(level-1);
		}
		initialize();
	}
}
