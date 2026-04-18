package com.gt.demo.web.base;

import com.gt.demo.common.config.FrameworkConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class WebDriverFactory {
  private WebDriverFactory() {}

  public static WebDriver create() {
    String browser = FrameworkConfig.get("webdriver.browser");
    if (browser == null || browser.isBlank()) {
      browser = "chrome";
    }

    boolean headless = FrameworkConfig.getBoolean("webdriver.headless", true);

    return switch (browser.toLowerCase()) {
      case "firefox" -> createFirefox(headless);
      case "chrome" -> createChrome(headless);
      default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
    };
  }

  private static WebDriver createChrome(boolean headless) {
    ChromeOptions options = new ChromeOptions();
    if (headless) {
      options.addArguments("--headless=new");
    }
    options.addArguments("--window-size=1920,1080");
    return new ChromeDriver(options);
  }

  private static WebDriver createFirefox(boolean headless) {
    FirefoxOptions options = new FirefoxOptions();
    if (headless) {
      options.addArguments("-headless");
    }
    return new FirefoxDriver(options);
  }
}

