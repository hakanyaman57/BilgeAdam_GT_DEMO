package com.gt.demo.mobile.screens;

import com.gt.demo.common.config.FrameworkConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseMobileScreen {
  protected final AppiumDriver driver;
  private final WebDriverWait wait;

  protected BaseMobileScreen(AppiumDriver driver) {
    this.driver = driver;
    int timeoutSeconds = FrameworkConfig.getInt("mobile.wait.seconds", 15);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
  }

  protected By forPlatform(By androidLocator, By iosLocator) {
    String platformName = String.valueOf(driver.getCapabilities().getCapability("platformName"));
    if ("ios".equalsIgnoreCase(platformName)) {
      return iosLocator;
    }
    return androidLocator;
  }

  protected void tap(By androidLocator, By iosLocator) {
    wait.until(ExpectedConditions.elementToBeClickable(forPlatform(androidLocator, iosLocator))).click();
  }

  protected void type(By androidLocator, By iosLocator, String value) {
    WebElement element =
        wait.until(ExpectedConditions.visibilityOfElementLocated(forPlatform(androidLocator, iosLocator)));
    element.clear();
    element.sendKeys(value);
  }

  protected boolean isVisible(By androidLocator, By iosLocator) {
    return !driver.findElements(forPlatform(androidLocator, iosLocator)).isEmpty();
  }

  protected List<WebElement> visibleElements(By locator) {
    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }

  protected By byAccessibilityId(String value) {
    return AppiumBy.accessibilityId(value);
  }
}
