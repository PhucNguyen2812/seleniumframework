package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static ConfigReader instance;
    private static String loadedEnv;
    private final Properties props = new Properties();

    private ConfigReader(String env) {
        String fileName = "config-" + env + ".properties";
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalStateException("Cannot find config file: " + fileName);
            }
            props.load(input);
            System.out.println("[ConfigReader] Dang dung moi truong: " + env);
            System.out.println("[ConfigReader] retry.count = " + getRetryCount());
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config file: " + fileName, e);
        }
    }

    public static synchronized ConfigReader getInstance() {
        String env = System.getProperty("env", "dev");
        if (instance == null || loadedEnv == null || !loadedEnv.equals(env)) {
            instance = new ConfigReader(env);
            loadedEnv = env;
        }
        return instance;
    }

    public int getRetryCount() {
        return Integer.parseInt(props.getProperty("retry.count", "0"));
    }

    public String getBrowser() {
        return props.getProperty("browser", "chrome");
    }

    public String getBaseUrl() {
        return props.getProperty("base.url", "about:blank");
    }
}
