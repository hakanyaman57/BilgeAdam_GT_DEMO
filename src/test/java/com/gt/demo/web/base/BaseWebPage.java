package com.gt.demo.web.base;

import com.gt.demo.common.config.FrameworkConfig;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseWebPage {
  protected final WebDriver driver;
  private final WebDriverWait wait;

  protected BaseWebPage(WebDriver driver) {
    this.driver = driver;
    int timeoutSeconds = FrameworkConfig.getInt("web.wait.seconds", 15);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
  }

  protected WebElement visible(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected WebElement clickable(By locator) {
    return wait.until(ExpectedConditions.elementToBeClickable(locator));
  }

  protected List<WebElement> allVisible(By locator) {
    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }

  protected void click(By locator) {
    clickable(locator).click();
  }

  protected void type(By locator, String value) {
    WebElement element = visible(locator);
    element.clear();
    element.sendKeys(value);
  }

  protected String text(By locator) {
    return visible(locator).getText().trim();
  }

  protected void selectByVisibleText(By locator, String value) {
    new Select(visible(locator)).selectByVisibleText(value);
  }

  protected void switchToFrame(By locator) {
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
  }

  protected void defaultContent() {
    driver.switchTo().defaultContent();
  }

  protected Object js(String script, Object... args) {
    return ((JavascriptExecutor) driver).executeScript(script, args);
  }

  protected boolean waitUntilJsCondition(String script) {
    try {
      return wait.until(d -> Boolean.TRUE.equals(js(script)));
    } catch (TimeoutException timeoutException) {
      return false;
    }
  }
}

