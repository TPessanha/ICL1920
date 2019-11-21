import compiler.Compiler;
import main.MainAssembler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import utils.PropertiesUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompilerTests {
	@AfterAll
	static void finish() throws IOException {
		File dir =
			new File(Paths.get(PropertiesUtils.getAssembledPath().toString()).toString());
		System.out.println("Delete: " + dir.getAbsolutePath());
		for (File f : Objects.requireNonNull(dir.listFiles())) {
			f.delete();
		}
	}

	@TestFactory
	public Iterable<DynamicTest> runTests() throws URISyntaxException {
		List<DynamicTest> tests = new ArrayList<>();
		File dir = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("compilerTests/")).toURI());

		for (File f: Objects.requireNonNull(dir.listFiles()))
		{
			DynamicTest t = DynamicTest.dynamicTest(f.getName(),
				() ->
				{
					runSingleTest(f.getName());
				});
			tests.add(t);
		}

		return tests;
	}

	void cleanup() throws IOException {
		File dir =
			new File(Paths.get(PropertiesUtils.getCompiledPath().toString()).toString());

		System.out.println("Delete: " + dir.getAbsolutePath());
		for (File f : Objects.requireNonNull(dir.listFiles())) {
			f.delete();
		}
	}
//
//	@Test
//	public void runSingleTest() throws Exception {
//		runSingleTest("test_let_1.icl");
//	}

	private void runSingleTest(String fileName) throws Exception {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("compilerTests/" + fileName);
		List<TypedResult> expected = testUtil.getResultCheck(in);


		in = this.getClass().getClassLoader().getResourceAsStream("compilerTests/" + fileName);
		try {
			compileAndAssemble(in);
			List<String> outputs = runClass();

			for (int i = 0; i < expected.size(); i++) {

				assertEquals(expected.get(i).getValue(), outputs.get(i));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		cleanup();
	}

	private void compileAndAssemble(InputStream in) throws IOException {
		Compiler.reset();
		Compiler.run(in);
		MainAssembler.run();
	}

	private List<String> runClass() throws Exception {
		String classPath = Paths.get(PropertiesUtils.getAssembledPath().toString()).toString();
		List<String> outputs = new ArrayList<>();

		try {

			System.out.println("command output:");
			Process proc = Runtime.getRuntime().exec("java -cp " + classPath + " Main");

			InputStream errin = proc.getErrorStream();
			InputStream in = proc.getInputStream();
			BufferedReader errorOutput = new BufferedReader(new InputStreamReader(errin));
			BufferedReader output = new BufferedReader(new InputStreamReader(in));
			String errorLine = null;
			String outputLine = null;
			try {
				while ((errorLine = errorOutput.readLine()) != null ||
					(outputLine = output.readLine()) != null) {
					if (errorLine != null) System.out.print(errorLine);
					if (outputLine != null) {
						outputs.add(outputLine);
						System.out.println(outputLine);
					}
				}
				errorOutput.close();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int result = proc.waitFor();
			if (result != 0)
				throw new Exception("Unknown error result: " + result);
		} catch (IOException e) {
			System.err.println("IOException raised: " + e.getMessage());
		}

		return outputs;
	}
}
