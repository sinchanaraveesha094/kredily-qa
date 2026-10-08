package com.kredily.qa.utils;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            PROPS.load(in);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to load config.properties", e);
        }
    }

    private ConfigReader() {}

    public static String get(String key) {
        // -Dkey=value on the command line overrides the file
        return System.getProperty(key, PROPS.getProperty(key, "")).trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}
