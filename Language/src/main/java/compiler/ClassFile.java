package compiler;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ClassFile {
	protected CodeBlock code;
	protected String className;
	protected String superName;
	protected Collection<IdentifierDetails> fields;

	private String[] parentStructuresStart = {".method", "; tabify"};
	private String[] parentStructuresEnd = {".end method", "; detabify"};

	public ClassFile(String className, String superName, Collection<IdentifierDetails> fields) {
		code = new CodeBlock();
		this.className = className;
		this.superName = superName;
		this.fields = fields;
	}

	public void initialize() {
		writeHeader();
		writeDefaultConstructor();
	}

	public ClassFile(String className, Collection<IdentifierDetails> fields) {
		this(className, "java/lang/Object", fields);
	}

	public ClassFile(String className, String superName) {
		this(className, superName, Collections.emptyList());
	}

	public ClassFile(String className) {
		this(className, "java/lang/Object", Collections.emptyList());
	}

	private void writeHeader() {
		code.appendCodeLine(".class public " + className);
		code.appendCodeLine(".super java/lang/Object");
		if (this instanceof StackFrameFile)
			code.appendCodeLine(".field public sl L" + superName + ";");
		for (IdentifierDetails details : fields)
			code.appendCodeLine(".field public " + details.getName() + " " + details.getType());
		code.appendCodeLine("");
	}

	private void writeDefaultConstructor() {
		code.appendCodeLine("; standard initializer");
		code.appendCodeLine(".method public <init>()V");
		code.appendCodeLine("aload_0");
		code.appendCodeLine("invokenonvirtual java/lang/Object/<init>()V");
		code.appendCodeLine("return");
		code.appendCodeLine(".end method");
		code.appendCodeLine("");
	}

	public String getFileName() {
		return className + ".j";
	}

	public void dump(PrintStream out, boolean prettify) {
		if (prettify) {
			StringBuilder tabs = new StringBuilder();

			for (String line : code.getCode()) {
				if (isParentStructureEnd(line)) {
					tabs.deleteCharAt(tabs.length() - 1);
					out.println(tabs + line);
				} else if (isParentStructureStart(line)) {
					out.println(tabs + line);
					tabs.append("\t");
				} else
					out.println(tabs + line);
			}
		} else {
			for (String line : code.getCode())
				out.println(line);
		}
	}

	private boolean isParentStructureStart(String line) {
		return Arrays.stream(parentStructuresStart).parallel().anyMatch(line::contains);
	}

	private boolean isParentStructureEnd(String line) {
		return Arrays.stream(parentStructuresEnd).parallel().anyMatch(line::contains);
	}

//	public String dump() {
//		StringBuilder builder = new StringBuilder();
//		for (String line : code.getCode())
//			builder.append(line + "\n");
//		return builder.toString();
//	}

	public void dump(Path path) throws IOException {
		dump(path.toAbsolutePath().toString());
	}

	public void dump(String path) throws IOException {
		File compiledFile = new File(Paths.get(path, getFileName()).toString());
		if (!compiledFile.exists()) {
			compiledFile.getParentFile().mkdirs();
			compiledFile.createNewFile();
		}
		PrintStream out = new PrintStream(compiledFile);
		dump(out, true);
		out.close();
	}

	public void emitCodeBlock(CodeBlock code) {
		this.code.appendCodeBlock(code);
	}
}
