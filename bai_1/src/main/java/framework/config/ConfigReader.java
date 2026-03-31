package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static ConfigReader instance;
    private final Properties props = new Properties();

    private ConfigReader() {
        String env = System.getProperty("env", "dev");
        String fileName = "config-" + env + ".properties";

        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalStateException("Cannot find config file: " + fileName);
            }
            props.load(input);
            System.out.println("[ConfigReader] Using environment: " + env);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config file: " + fileName, e);
        }
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getBaseUrl() {
        return props.getProperty("base.url");
    }

    public String getBrowser() {
        return props.getProperty("browser", "chrome");
    }

    public int getRetryCount() {
        return Integer.parseInt(props.getProperty("retry.count", "1"));
    }
}
