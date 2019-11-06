package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesUtils {
    private static final Properties properties = new Properties();
    private static final InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("config.properties");

    public static String getProperty(String key) throws IOException {
        properties.load(in);
        return properties.getProperty(key);
    }

    public static Path getProjectPath(){
        return Paths.get(System.getProperty("user.dir"));
    }

	public static Path getCompiledPath() throws IOException {
		String dir = getProperty("compiledOutputDir");
		Path proj = getProjectPath();
		if (proj.endsWith("Language"))
			proj = proj.getParent();
		return Paths.get(proj.toString(), dir);
	}

	public static Path getAssembledPath() throws IOException {
		String dir = getProperty("assembledOutputDir");
		Path proj = getProjectPath();
		if (proj.endsWith("Language"))
			proj = proj.getParent();
		return Paths.get(proj.toString(), dir);
	}
}
