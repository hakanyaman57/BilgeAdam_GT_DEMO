package com.gt.demo.mobile.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductListScreen extends BaseMobileScreen {
  private static final By LIST_DEMO_BUTTON_ANDROID = AppiumBy.accessibilityId("List Demo");
  private static final By LIST_DEMO_BUTTON_IOS = AppiumBy.accessibilityId("List Demo");

  private static final By LIST_ITEM_ONE_ANDROID = AppiumBy.accessibilityId("Altocumulus");
  private static final By LIST_ITEM_ONE_IOS = AppiumBy.accessibilityId("Altocumulus");

  private static final By LIST_ITEM_TWO_ANDROID = AppiumBy.accessibilityId("Cirrostratus");
  private static final By LIST_ITEM_TWO_IOS = AppiumBy.accessibilityId("Cirrostratus");

  private static final By LIST_TEXT_FALLBACK_ANDROID =
      AppiumBy.xpath("//*[contains(@text,'Altocumulus') or contains(@text,'Cirrostratus')]");
  private static final By LIST_TEXT_FALLBACK_IOS =
      AppiumBy.iOSNsPredicateString("label CONTAINS 'Altocumulus' OR label CONTAINS 'Cirrostratus'");

  public ProductListScreen(AppiumDriver driver) {
    super(driver);
  }

  public void openListDemo() {
    tap(LIST_DEMO_BUTTON_ANDROID, LIST_DEMO_BUTTON_IOS);
  }

  public boolean hasVisibleListContent() {
    if (isVisible(LIST_ITEM_ONE_ANDROID, LIST_ITEM_ONE_IOS)
        || isVisible(LIST_ITEM_TWO_ANDROID, LIST_ITEM_TWO_IOS)) {
      return true;
    }
    return isVisible(LIST_TEXT_FALLBACK_ANDROID, LIST_TEXT_FALLBACK_IOS);
  }

  public int visibleItemCountByTextFallback() {
    By locator = forPlatform(LIST_TEXT_FALLBACK_ANDROID, LIST_TEXT_FALLBACK_IOS);
    List<WebElement> items = visibleElements(locator);
    return items.size();
  }
}
