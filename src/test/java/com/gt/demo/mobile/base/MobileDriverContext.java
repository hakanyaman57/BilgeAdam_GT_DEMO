package com.gt.demo.mobile.base;

import io.appium.java_client.AppiumDriver;

public final class MobileDriverContext {
  private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();

  private MobileDriverContext() {}

  public static AppiumDriver get() {
    AppiumDriver driver = DRIVER.get();
    if (driver == null) {
      throw new IllegalStateException("Mobile driver is not initialized for this thread.");
    }
    return driver;
  }

  public static void set(AppiumDriver driver) {
    DRIVER.set(driver);
  }

  public static void clear() {
    AppiumDriver driver = DRIVER.get();
    if (driver != null) {
      driver.quit();
    }
    DRIVER.remove();
  }
}

