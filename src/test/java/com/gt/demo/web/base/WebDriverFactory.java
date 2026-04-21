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

    boolean headless = resolveHeadlessMode();

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
    // Linux containers (Codespaces/CI) often need these flags for stable startup.
    if (isLinux()) {
      options.addArguments("--no-sandbox");
      options.addArguments("--disable-dev-shm-usage");
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

  private static boolean resolveHeadlessMode() {
    boolean configured = FrameworkConfig.getBoolean("webdriver.headless", true);
    if (configured) {
      return true;
    }
    // If there is no active display in Linux, force headless even if config is false.
    return isLinux() && isDisplayUnavailable();
  }

  private static boolean isLinux() {
    String osName = System.getProperty("os.name", "");
    return osName.toLowerCase().contains("linux");
  }

  private static boolean isDisplayUnavailable() {
    String display = System.getenv("DISPLAY");
    return display == null || display.isBlank();
  }
}
