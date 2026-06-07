package com.gt.demo.web.locators;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.json.Json;

public class JsonLocatorRepository {
  private final Map<String, Object> locatorGroups;

  private JsonLocatorRepository(Map<String, Object> locatorGroups) {
    this.locatorGroups = locatorGroups;
  }

  public static JsonLocatorRepository fromResource(String resourcePath) {
    InputStream stream =
        JsonLocatorRepository.class.getClassLoader().getResourceAsStream(resourcePath);
    if (stream == null) {
      throw new IllegalArgumentException("Locator JSON not found: " + resourcePath);
    }

    try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
      Map<String, Object> parsedJson = new Json().toType(reader, Map.class);
      return new JsonLocatorRepository(parsedJson);
    } catch (Exception exception) {
      throw new IllegalStateException("Locator JSON could not be read: " + resourcePath, exception);
    }
  }

  public By by(String pageName, String locatorName) {
    Map<String, Object> pageLocators = mapValue(locatorGroups, pageName);
    Map<String, Object> locator = mapValue(pageLocators, locatorName);

    String type = textValue(locator, "type");
    String value = textValue(locator, "value");

    return switch (type) {
      case "id" -> By.id(value);
      case "name" -> By.name(value);
      case "css" -> By.cssSelector(value);
      case "xpath" -> By.xpath(value);
      case "linkText" -> By.linkText(value);
      case "partialLinkText" -> By.partialLinkText(value);
      case "className" -> By.className(value);
      case "tagName" -> By.tagName(value);
      default -> throw new IllegalArgumentException(
          "Unsupported locator type '" + type + "' for " + pageName + "." + locatorName);
    };
  }

  @SuppressWarnings("unchecked")
  private static Map<String, Object> mapValue(Map<String, Object> source, String key) {
    Object value = source.get(key);
    if (!(value instanceof Map)) {
      throw new IllegalArgumentException("Locator JSON key is missing or not an object: " + key);
    }
    return (Map<String, Object>) value;
  }

  private static String textValue(Map<String, Object> source, String key) {
    Object value = source.get(key);
    if (!(value instanceof String text) || text.isBlank()) {
      throw new IllegalArgumentException("Locator JSON value is missing or blank: " + key);
    }
    return text;
  }
}
