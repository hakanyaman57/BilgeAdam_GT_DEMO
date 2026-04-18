package com.gt.demo.web.base;

import org.openqa.selenium.WebDriver;

public final class WebDriverContext {
  private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

  private WebDriverContext() {}

  public static WebDriver get() {
    WebDriver driver = DRIVER.get();
    if (driver == null) {
      throw new IllegalStateException("WebDriver is not initialized for this thread.");
    }
    return driver;
  }

  public static void set(WebDriver driver) {
    DRIVER.set(driver);
  }

  public static void clear() {
    WebDriver driver = DRIVER.get();
    if (driver != null) {
      driver.quit();
    }
    DRIVER.remove();
  }
}

