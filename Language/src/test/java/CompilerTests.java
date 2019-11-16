import compiler.Compiler;
import main.MainAssembler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import utils.PropertiesUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompilerTests {
	SummaryGeneratingListener listener = new SummaryGeneratingListener();
	private Properties properties = new Properties();

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
	Iterable<DynamicTest> runTests() throws URISyntaxException {
		List<DynamicTest> tests = new ArrayList<>();
		File dir = new File(this.getClass().getClassLoader().getResource("compilerTests/").toURI());

		for (File f: dir.listFiles())
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

	private void runSingleTest(String fileName) throws Exception {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("compilerTests/" + fileName);
		String[] expected = getResultCheck(in).split(":");


		in = this.getClass().getClassLoader().getResourceAsStream("compilerTests/" + fileName);
		try {
			compileAndAssemble(in);
			List outputs = runClass();
			assertEquals(outputs.get(0), expected[0]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String getResultCheck(InputStream in)
	{
		Scanner reader = new Scanner(in);
		reader.reset();
		String check = reader.nextLine().substring(2);
		return check;
	}

	private void compileAndAssemble(InputStream in) throws IOException {
		Compiler compiler = new Compiler();
		compiler.run(in);
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
			String line1 = null;
			String line2 = null;
			try {
				while ((line1 = errorOutput.readLine()) != null ||
					(line2 = output.readLine()) != null) {
					if (line1 != null) System.out.print(line1);
					if (line2 != null) {
						outputs.add(line2);
						System.out.println(line2);
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
