package compiler;

import compiler.classes.ClassFile;
import compiler.classes.MainClass;
import nodes.Node;
import parser.*;
import state.Environment;
import types.IType;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashSet;

import static utils.PropertiesUtils.getCompiledPath;

public class Compiler {
	private static final int SL = 4;
	private static int nLabel;
	private static int nScope;
	private static HashSet<String> compiledClasses = new HashSet<>();

	public static String generateUniqueLabel() {
		return "L" + nLabel++;
	}

	public static String generateUniqueScope() {
		return "frame_" + nScope++;
	}

	public static void run(Parser parser) {
		Node exp;

		try {
			Compiler.reset();
			exp = parser.Start();
			MainClass mainClass = new MainClass(256, 10, "Main");
			IType type = exp.typecheck(new Environment<>());
			CodeBlock code = exp.compile(new CompilerEnvironment(SL));
			mainClass.setBody(code);

			Compiler.addClassFile(mainClass);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not find output path");
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Syntax Error!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void run(String s) {
		Provider provider = new StringProvider(s);
		Parser parser = new Parser(provider);
		run(parser);
	}

	public static void run(InputStream stream) {
		Provider provider = new StreamProvider(stream, Charset.defaultCharset());
		Parser parser = new Parser(provider);
		run(parser);
	}

	public static void reset() {
		nLabel = 0;
		nScope = 0;
		compiledClasses.clear();
	}

	public static void addClassFile(ClassFile file) throws IOException {
		if (!compiledClasses.contains(file.getClassName())) {
			file.close();
			file.dump(getCompiledPath());
			compiledClasses.add(file.getClassName());
		}
	}

	public static boolean isClassFileCompiled(String className) {
		return compiledClasses.contains(className);
	}
}
