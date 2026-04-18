package com.gt.demo.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class FrameworkConfig {
  private static final String DEFAULT_CONFIG_PATH = "config/framework.properties";
  private static final Properties PROPERTIES = load(DEFAULT_CONFIG_PATH);

  private FrameworkConfig() {}

  public static String get(String key) {
    String systemValue = System.getProperty(key);
    if (systemValue != null && !systemValue.isBlank()) {
      return systemValue.trim();
    }

    String value = PROPERTIES.getProperty(key);
    return value == null ? null : value.trim();
  }

  public static String getRequired(String key) {
    String value = get(key);
    if (value == null || value.isBlank()) {
      throw new IllegalStateException("Missing required config key: " + key);
    }
    return value;
  }

  public static boolean getBoolean(String key, boolean defaultValue) {
    String value = get(key);
    if (value == null) {
      return defaultValue;
    }
    return Boolean.parseBoolean(value);
  }

  public static int getInt(String key, int defaultValue) {
    String value = get(key);
    if (value == null) {
      return defaultValue;
    }
    return Integer.parseInt(value);
  }

  private static Properties load(String classpathResource) {
    Properties properties = new Properties();
    try (InputStream is =
        FrameworkConfig.class.getClassLoader().getResourceAsStream(classpathResource)) {
      if (is == null) {
        throw new IllegalStateException("Config file not found on classpath: " + classpathResource);
      }
      properties.load(is);
      return properties;
    } catch (IOException e) {
      throw new IllegalStateException("Failed to load config: " + classpathResource, e);
    }
  }
}

