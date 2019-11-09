package main;

import java.nio.charset.Charset;

import nodes.ASTNode;
import parser.ParseException;
import parser.Parser;
import parser.Provider;
import parser.StreamProvider;
import state.Environment;

public class Interpreter {

	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		Provider provider = new StreamProvider(System.in, Charset.defaultCharset());
		Parser parser = new Parser(provider);

		while (true) {
			try {
				ASTNode exp = parser.Start();
				System.out.println(exp.eval(new Environment<>(false)));
			} catch (RuntimeException e) {
				System.out.println("Runtime Error!");
				System.out.println(e.getMessage());
			} catch (ParseException e) {
				System.out.println("Syntax Error!");
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Unknown Error!");
				System.out.println(e.getMessage());
			} finally {
				System.out.println("--------------------------");
				parser.ReInit(provider);
			}
		}
	}
}
