package com.gt.demo.mobile.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApiDemosHybridScreen extends BaseMobileScreen {
  private static final By VIEWS_MENU =
      AppiumBy.androidUIAutomator("new UiSelector().text(\"Views\")");
  private static final By WEBVIEW_MENU =
      AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"WebView\"))");
  private static final By FIRST_WEB_LINK = By.cssSelector("a");

  public ApiDemosHybridScreen(AppiumDriver driver) {
    super(driver);
  }

  public void openWebViewScreen() {
    driver.findElement(VIEWS_MENU).click();
    driver.findElement(WEBVIEW_MENU).click();
  }

  public String switchToWebViewContext() {
    SupportsContextSwitching contextDriver = (SupportsContextSwitching) driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    return wait.until(
        ignored -> {
          Set<String> contexts = contextDriver.getContextHandles();
          Optional<String> webViewContext =
              contexts.stream().filter(context -> context.startsWith("WEBVIEW")).findFirst();
          webViewContext.ifPresent(contextDriver::context);
          return webViewContext.orElse(null);
        });
  }

  public String clickFirstWebLink() {
    WebElement link =
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(FIRST_WEB_LINK));
    String linkText = link.getText().trim();
    link.click();
    return linkText;
  }

  public void switchToNative() {
    SupportsContextSwitching contextDriver = (SupportsContextSwitching) driver;
    contextDriver.context("NATIVE_APP");
  }
}
