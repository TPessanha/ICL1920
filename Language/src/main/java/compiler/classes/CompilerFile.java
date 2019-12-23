package compiler.classes;

import compiler.CodeBlock;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class CompilerFile {
	public void close(){

	};
	protected CodeBlock code;

	private String[] parentStructuresStart = {".method", "; tabify"};
	private String[] parentStructuresEnd = {".end method", "; detabify"};

	public CompilerFile()
	{
		this.code = new CodeBlock();
	}

	private boolean isParentStructureStart(String line) {
		return Arrays.stream(parentStructuresStart).parallel().anyMatch(line::contains);
	}

	private boolean isParentStructureEnd(String line) {
		return Arrays.stream(parentStructuresEnd).parallel().anyMatch(line::contains);
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

	public abstract String getFileName();

	public void emitCodeBlock(CodeBlock code) {
		this.code.append(code);
	}
}
