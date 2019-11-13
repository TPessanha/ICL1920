package main;

import compiler.Compiler;
import parser.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class MainCompiler {

	public static void main(String[] args) throws IOException {
		Provider provider;
		Parser parser;
		Compiler compiler = new Compiler();

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
		compiler.run(parser);

		if (assemble)
			MainAssembler.run();
	}

}
