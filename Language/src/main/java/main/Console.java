package main;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

import nodes.ASTNode;
import parser.ParseException;
import parser.Parser;
import parser.Provider;
import parser.StreamProvider;
import state.Environment;

public class Console {

    @SuppressWarnings("static-access")
    public static void main(String args[]) {
        Provider provider = new StreamProvider(System.in, Charset.defaultCharset());
        Parser parser = new Parser(provider);

        while (true) {
            try {
                ASTNode exp = parser.Start();
                System.out.println(exp.eval(new Environment<>(false)));
                System.out.println("-----------------------");
            } catch (Exception e) {
                System.out.println("Syntax Error!");
                System.out.println(e.getMessage());
                System.out.println("-----------------------");
                parser.ReInit(provider);
            }
        }
    }
}
