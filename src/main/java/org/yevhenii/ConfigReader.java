package org.yevhenii;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE_NAME = "config.properties";

    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME)) {
            if (inputStream == null) {
                throw new RuntimeException("Could not find " + CONFIG_FILE_NAME + " on the classpath.");
            }
            properties.load(inputStream);
            System.out.println("Successfully loaded " + CONFIG_FILE_NAME);
        } catch (IOException e) {
            throw new RuntimeException("Error loading " + CONFIG_FILE_NAME, e);
        }
    }

    /**
     * Create an uppercase version of the key with underscores.
     * Step 1: Check for a system environment variable first
     * Step 2: If not found, fall back to the properties file
     * @param key - propertyKey
     * @return - value of key
     */
    public static String getProperty(String key) {
        String envKey = key.toUpperCase().replace('.', '_');

        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue;
        }

        String propValue = properties.getProperty(key);
        if (propValue == null) {
            throw new RuntimeException("Property with key '" + key + "' not found in environment variables or in " +
                    CONFIG_FILE_NAME);
        }
        return propValue;
    }
}
