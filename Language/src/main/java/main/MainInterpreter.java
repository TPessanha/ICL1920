package main;

import exceptions.TypeMismatchException;
import nodes.Node;
import parser.ParseException;
import parser.Parser;
import parser.Provider;
import parser.StreamProvider;
import state.Environment;
import types.IType;
import types.VoidType;
import values.IValue;

import java.nio.charset.Charset;

public class MainInterpreter {

	public static void main(String[] args) {
		Provider provider = new StreamProvider(System.in, Charset.defaultCharset());
		Parser parser = new Parser(provider);

		while (true) {
			try {
				Node exp = parser.Start();
				IType resultType = exp.typecheck(new Environment<>(false));
				System.out.println("Expected type: " + resultType.getName());

				IValue<?> out = exp.eval(new Environment<>(false));
				if(out.getType() instanceof VoidType)
					System.out.println("done");
				else
					System.out.println(out);
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
