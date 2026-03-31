package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private final Properties properties = new Properties();

    private ConfigReader(String env) {
        String fileName = "config-" + env + ".properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalStateException("Cannot find config file: " + fileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load config file: " + fileName, e);
        }
    }

    public static ConfigReader getInstance() {
        String env = System.getProperty("env", "dev");
        return new ConfigReader(env);
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }
}
