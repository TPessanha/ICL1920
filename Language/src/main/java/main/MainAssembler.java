package main;

import jasmin.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.PropertiesUtils.getAssembledPath;
import static utils.PropertiesUtils.getCompiledPath;

public class MainAssembler {

	private static Main jasmin = null;
	private static File outputDir = null;

	public static void main(String[] args) {
		try {
			run();
//            jasminHelp();
//            jasminVersion();
		} catch (Exception e) {
			System.out.println("Syntax Error!");
		}
	}

	public static void setOutputDir(File outputDir) {
		MainAssembler.outputDir = outputDir;
	}

	public static void run() throws IOException {
		if (MainAssembler.outputDir == null)
			setOutputDir(new File(getAssembledPath().toAbsolutePath().toString()));
		File inputDir = new File(getCompiledPath().toAbsolutePath().toString());

		jasmin = new Main();
		jasmin.run(new String[]{"-d", outputDir.getAbsolutePath(), "-g"});
		run(inputDir);
	}

	public static void run(File inputDir) throws IOException {

//        List<String> args = new ArrayList<>();
//        args.add("-d");
//        args.add(outputDir.getAbsolutePath());
//        args.add("-g");
//        args.addAll(getAllJasminFiles(inputDir));

//        String[] arrayArgs = args.toArray(new String[0]);
//        jasmin.run(arrayArgs);

		for (File f : inputDir.listFiles()) {
			if (f.isDirectory()) {
				run(f);
			} else if (f.isFile() && f.getName().endsWith(".j")) {
				jasmin.assemble(f.getAbsolutePath());
			}
		}
	}

	private static List<String> getAllJasminFiles(File inputDir) {
		List<String> list = new ArrayList<>();

		for (File f : inputDir.listFiles()) {
			if (f.isDirectory()) {
				list.addAll(getAllJasminFiles(f));
			} else if (f.isFile() && f.getName().endsWith(".j")) {
				list.add(f.getAbsolutePath());
			}
		}
		return list;
	}

	public static void jasminHelp() {
		if (jasmin == null)
			jasmin = new Main();
		jasmin.run(new String[]{"-help"});
	}

	public static void jasminVersion() {
		if (jasmin == null)
			jasmin = new Main();
		jasmin.run(new String[]{"-version"});
	}
}
