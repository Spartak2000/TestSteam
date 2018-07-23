package framework;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;
    private String pathFile = "./src/test/resources/%s.properties";
    private final String format = "utf8";

    ConfigReader(String path) {
        prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(new File(String.format(pathFile, path)).getCanonicalPath());
            BufferedReader br = new BufferedReader(new InputStreamReader(input, format));
            prop.load(br);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProperties(String parameter) {
        return prop.getProperty(parameter);
    }
}
