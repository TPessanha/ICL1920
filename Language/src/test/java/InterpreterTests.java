import parser.Parser;
import parser.StringProvider;
import state.Environment;
import values.IntValue;
import values.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Description: Tests for parser
 */
class InterpreterTests {

    private Parser getParser(String cmd) {
        return new Parser(new StringProvider(cmd));
    }

    @Test
    void test_1_plus_1() throws Exception {
        Parser parser = getParser("1+1;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals((Integer) 2, (Integer) result.getValue());
    }

    @Test
    void test_1_minus_1() throws Exception {
        Parser parser = getParser("1-1;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals((Integer) 0, (Integer) result.getValue());

    }

    @Test
    void test_1_mult_1() throws Exception {
        Parser parser = getParser("1*1;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals((Integer) 1, (Integer) result.getValue());

    }

    @Test
    void test_1_div_1() throws Exception {
        Parser parser = getParser("1/1;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals((Integer) 1, (Integer) result.getValue());
    }

    @Test
    void test_parenthesis() throws Exception {
        Parser parser = getParser("2*(1+1);");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals((Integer) 4, (Integer) result.getValue());
    }

    @Test
    void test_let_1() throws Exception {
        Parser parser = getParser("let x=5 in x end;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals((Integer) 5, (Integer) result.getValue());
    }

    @Test
    void test_let_2() throws Exception {
        Parser parser = getParser("let x=5 in x+5 end;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals((Integer) 10, (Integer) result.getValue());
    }

    @Test
    void test_let_3() throws Exception {
        Parser parser = getParser("let x=2 in (let x=5 in x+1 end) + x end;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals((Integer) 8, (Integer) result.getValue());
    }

    @Test
    void test_let_list_1() throws Exception {
        Parser parser = getParser("let x=2,y=4 in x + y end;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals((Integer) 6, (Integer) result.getValue());
    }

    @Test
    void test_unary_op_1() throws Exception {
        Parser parser = getParser("-3;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals( -3, (Integer) result.getValue());
    }

    @Test
    void test_unary_op_2() throws Exception {
        Parser parser = getParser("-(-3);");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals(3, (Integer) result.getValue());
    }

    @Test
    void test_unary_op_3() throws Exception {
        Parser parser = getParser("3--3;");
        Environment<Value<?>> env = new Environment<>();
        Value<?> result = parser.Start().eval(env);

        assertTrue(result instanceof IntValue);
        assertEquals(6, (Integer) result.getValue());
    }


}
