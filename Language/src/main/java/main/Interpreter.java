package main;

import java.nio.charset.Charset;

import exceptions.TypeMismatchException;
import nodes.ASTNode;
import parser.ParseException;
import parser.Parser;
import parser.Provider;
import parser.StreamProvider;
import state.Environment;

public class Interpreter {

	public static void main(String args[]) {
		Provider provider = new StreamProvider(System.in, Charset.defaultCharset());
		Parser parser = new Parser(provider);

		while (true) {
			try {
				ASTNode exp = parser.Start();
				System.out.println("Expected type: " + exp.typecheck().getTypeName());
				System.out.println(exp.eval(new Environment<>(false)));
			} catch (ParseException e) {
				System.out.println("Syntax Error!");
				System.out.println(e.getMessage());
			} catch (TypeMismatchException e) {
				System.out.println("Typecheck Error!");
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Runtime Error!");
				System.out.println(e.getMessage());
			} finally {
				System.out.println("--------------------------");
				parser.ReInit(provider);
			}
		}
	}
}
