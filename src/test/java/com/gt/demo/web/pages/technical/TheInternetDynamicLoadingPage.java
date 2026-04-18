package com.gt.demo.web.pages.technical;

import com.gt.demo.web.base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TheInternetDynamicLoadingPage extends BaseWebPage {
  private static final By START_BUTTON = By.cssSelector("#start button");
  private static final By FINISH_TEXT = By.cssSelector("#finish h4");

  public TheInternetDynamicLoadingPage(WebDriver driver) {
    super(driver);
  }

  public void open(String baseUrl) {
    driver.get(baseUrl + "/dynamic_loading/1");
  }

  public void startLoading() {
    click(START_BUTTON);
  }

  public String finishText() {
    return text(FINISH_TEXT);
  }
}

