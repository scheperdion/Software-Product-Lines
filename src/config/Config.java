package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config _instance;
    private final Properties properties;
    private static final String CONFIG_FILE_PATH = "src/config.properties";



    private Config() {
        properties = new Properties();
        loadConfig();
    }

    public static Config getConfig() {
        if(_instance == null) {
            _instance = new Config();
        }
        return _instance;
    }

    private void loadConfig() {
        try (FileInputStream propsInput = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(propsInput);
        } catch (FileNotFoundException e) {
            System.err.println("Config File not found");
        } catch (IOException e) {
            System.err.println("Could not read Config File");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getConfigInfo() {
        StringBuilder configInfo = new StringBuilder("\n");
        for (String propertyName : properties.stringPropertyNames()) {
            configInfo.append(propertyName)
                    .append(": ")
                    .append(properties.getProperty(propertyName))
                    .append(",\n");
        }
        return configInfo.toString();
    }
}