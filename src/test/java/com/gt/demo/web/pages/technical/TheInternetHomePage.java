package com.gt.demo.web.pages.technical;

import com.gt.demo.web.base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TheInternetHomePage extends BaseWebPage {
  private static final By HEADING = By.cssSelector("h1");

  public TheInternetHomePage(WebDriver driver) {
    super(driver);
  }

  public void open(String baseUrl) {
    driver.get(baseUrl + "/");
  }

  public String headingText() {
    return text(HEADING);
  }
}

