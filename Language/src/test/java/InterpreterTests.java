import exceptions.DuplicatedIdentifierException;
import exceptions.IllegalOperatorException;
import exceptions.UndeclaredException;
import nodes.Node;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import parser.Parser;
import parser.StreamProvider;
import parser.StringProvider;
import state.Environment;
import types.IType;
import values.BooleanValue;
import values.FloatValue;
import values.IValue;
import values.IntValue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description: Tests for parser
 */
class InterpreterTests {

	final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private PrintStream out = new PrintStream(outputStream);

	private Parser getParser(String cmd) {
		Parser parser = new Parser(new StringProvider(cmd));
		outputStream.reset();
		parser.setOutputStream(out);
		return parser;
	}

	private Parser getParser(InputStream cmd) {
		Parser parser = new Parser(new StreamProvider(cmd, Charset.defaultCharset()));
		outputStream.reset();
		parser.setOutputStream(out);
		return parser;
	}

	private IValue runInterpreter(Parser parser) throws Exception {
		Node exp = parser.Start();
		exp.typecheck(new Environment<>(false));
		return exp.eval(new Environment<>());
	}

	@Test
	void test_1_plus_1() throws Exception {
		Parser parser = getParser("1+1;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals((Integer) 2, (Integer) result.getValue());
	}

	@Test
	void test_1_minus_1() throws Exception {
		Parser parser = getParser("1-1;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals((Integer) 0, (Integer) result.getValue());
	}

	@Test
	void test_1_mult_1() throws Exception {
		Parser parser = getParser("1*1;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals((Integer) 1, (Integer) result.getValue());
	}

	@Test
	void test_1_div_1() throws Exception {
		Parser parser = getParser("1/1;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals((Integer) 1, (Integer) result.getValue());
	}

	@Test
	void test_parenthesis() throws Exception {
		Parser parser = getParser("2*(1+1);;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals((Integer) 4, (Integer) result.getValue());
	}

	@Test
	void test_let_1() throws Exception {
		Parser parser = getParser("let x=5 in x end;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals((Integer) 5, (Integer) result.getValue());
	}

	@Test
	void test_let_2() throws Exception {
		Parser parser = getParser("let x=5 in x+5 end;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals((Integer) 10, (Integer) result.getValue());
	}

	@Test
	void test_let_3() throws Exception {
		Parser parser = getParser("let x=2 in (let x=5 in x+1 end) + x end;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals((Integer) 8, (Integer) result.getValue());
	}

	@Test
	void test_let_list_1() throws Exception {
		Parser parser = getParser("let x=2,y=4 in x + y end;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals((Integer) 6, (Integer) result.getValue());
	}

	@Test
	void test_unary_op_1() throws Exception {
		Parser parser = getParser("-3;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals(-3, (Integer) result.getValue());
	}

	@Test
	void test_unary_op_2() throws Exception {
		Parser parser = getParser("-(-3);;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals(3, (Integer) result.getValue());
	}

	@Test
	void test_unary_op_3() throws Exception {
		Parser parser = getParser("3--3;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof IntValue);
		assertEquals(6, (Integer) result.getValue());
	}

	@Test
	void test_boolean_1() throws Exception {
		Parser parser = getParser("true;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof BooleanValue);
		assertEquals(true, result.getValue());
	}

	@Test
	void test_boolean_2() throws Exception {
		Parser parser = getParser("false;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof BooleanValue);
		assertEquals(false, result.getValue());
	}

	@Test
	void test_boolean_3() throws Exception {
		Parser parser = getParser("~false;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof BooleanValue);
		assertEquals(true, result.getValue());
	}

	@Test
	void test_let_with_boolean_1() throws Exception {
		Parser parser = getParser("let x=false in x end;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof BooleanValue);
		assertEquals(false, result.getValue());
	}

	@Test
	void test_conversion_1() throws Exception {
		Parser parser = getParser("3+3.1;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof FloatValue);
		assertEquals(6.1f, (Float) result.getValue());
	}

	@Test
	void test_conversion_2() throws Exception {
		Parser parser = getParser("3.1+3;;");
		IValue result = runInterpreter(parser);

		assertTrue(result instanceof FloatValue);
		assertEquals(6.1f, (Float) result.getValue());
	}

	@Test
	void test_erro_1() throws Exception {
		Parser parser = getParser("5*false;;");

		assertThrows(
			IllegalOperatorException.class,
			() -> {
				Node exp = parser.Start();
				IType resultType = exp.typecheck(new Environment<>(false));
				exp.eval(new Environment<>());
			},
			"Expected Illegal Operator"
		);
	}

	@Test
	void test_erro_2() throws Exception {
		Parser parser = getParser("let x=1 in y end;;");

		assertThrows(
			UndeclaredException.class,
			() -> parser.Start().eval(new Environment<>()),
			"Expected undeclared"
		);
	}

	@Test
	void test_erro_3() throws Exception {
		Parser parser = getParser("let x=1 x=5 in x end;;");

		assertThrows(
			DuplicatedIdentifierException.class,
			() -> parser.Start().eval(new Environment<>()),
			"Expected duplicated identifier"
		);
	}

	@TestFactory
	Iterable<DynamicTest> runTests() throws URISyntaxException {
		List<DynamicTest> tests = new ArrayList<>();
		File dir = new File(this.getClass().getClassLoader().getResource("compilerTests/").toURI());

		for (File f : dir.listFiles()) {
			DynamicTest t = DynamicTest.dynamicTest(
				f.getName(),
				() ->
				{
					runSingleTest(f.getName());
				}
			);
			tests.add(t);
		}

		return tests;
	}

	private void runSingleTest(String fileName) throws Exception {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("compilerTests/" + fileName);
		List<TypedResult> expected = testUtil.getResultCheck(in);

		in = this.getClass().getClassLoader().getResourceAsStream("compilerTests/" + fileName);
		try {
			Parser parser = getParser(in);
			IValue result = runInterpreter(parser);

			List outputs = Arrays.asList(outputStream.toString().replace("\r","").split("\n"));

			for (int i = 0; i < expected.size(); i++) {
				assertEquals(expected.get(i).getValue(), outputs.get(i));
			}

			if (result.getType().getName().equals("void"))
				return;

			TypedResult fResult = expected.get(expected.size() - 1);
			assertEquals(fResult.getType(), result.getTypeName());
			assertEquals(fResult.getValue(), result.getValue().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
