package compiler;

import nodes.ASTNode;
import parser.*;
import state.Environment;
import types.IType;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static utils.PropertiesUtils.getCompiledPath;

public class Compiler {
	private static final int SL = 4;
	private int nLabel;

	public Compiler() {
		nLabel = 0;
	}

	public String generateUniqueLabel() {
		return "L" + nLabel++;
	}

	public void run(Parser parser) {
		ASTNode exp;

		try {
			exp = parser.Start();
			MainClassFile mainClassFile = new MainClassFile(256, 10, "Main");
			CodeBlock code = exp.compile(new CompilerEnvironment(SL));
			IType type = exp.typecheck(new Environment<>());
			mainClassFile.emitCodeBlock(code);
			mainClassFile.writeFooter();

			mainClassFile.dump(getCompiledPath());
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

	public void run(String s) {
		Provider provider = new StringProvider(s);
		Parser parser = new Parser(provider);
		run(parser);
	}

	public void run(InputStream stream) {
		Provider provider = new StreamProvider(stream, Charset.defaultCharset());
		Parser parser = new Parser(provider);
		run(parser);
	}
}
