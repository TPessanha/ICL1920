package main;

import compiler.CompilerEnvironment;
import compiler.MainClassFile;
import compiler.CodeBlock;
import nodes.ASTNode;
import parser.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static utils.PropertiesUtils.getCompiledPath;

public class Compiler {

	public static void main(String[] args) throws IOException {
		Provider provider;
		Parser parser;

		boolean assemble = false;
		String filePath = null;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-a")) {
				assemble = true;
			} else if (args[i].equals("-f")) {
				filePath = args[++i];
			}
		}
		if (filePath == null) {
			provider = new StreamProvider(System.in, Charset.defaultCharset());
		} else {
			InputStream in = new FileInputStream(filePath);
			provider = new StreamProvider(in, Charset.defaultCharset());
		}
		parser = new Parser(provider);
		run(parser);

		if (assemble)
			Assembler.run();
	}

	private static void run(Parser parser) {
		ASTNode exp;

		try {
			exp = parser.Start();
			MainClassFile mainClassFile = new MainClassFile(256, 10, "Main");
			CodeBlock code = exp.compile(new CompilerEnvironment());
			mainClassFile.emitCodeBlock(code);
			mainClassFile.writeFooter();

			mainClassFile.dump(getCompiledPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not find output path");
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Syntax Error!");//TODO change
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
		Provider provider = new StreamProvider(stream,Charset.defaultCharset());
		Parser parser = new Parser(provider);
		run(parser);
	}

}
