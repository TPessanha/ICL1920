import main.Assembler;
import main.Compiler;
import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import utils.PropertiesUtils;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompilerTests {
	SummaryGeneratingListener listener = new SummaryGeneratingListener();
	private Properties properties = new Properties();

	@Test
	void test_basic_1() throws Exception {
		String fileName = "test_basic_1.icl";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("compilerTests/" + fileName);
		try {
			compileAndAssemble(in);
			List outputs = runClass();
			assertEquals(outputs.get(0), "5");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	void test_let_1() throws Exception {
		String fileName = "test_let_1.icl";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("compilerTests/"+ fileName);
		try {
			compileAndAssemble(in);
			List outputs = runClass();
			assertEquals(outputs.get(0), "15");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void compileAndAssemble(InputStream in) throws IOException {
		Compiler.run(in);
		Assembler.assemble();
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
						System.out.print(line2);
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
