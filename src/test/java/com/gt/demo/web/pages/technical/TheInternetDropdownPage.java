package com.gt.demo.web.pages.technical;

import com.gt.demo.web.base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TheInternetDropdownPage extends BaseWebPage {
  private static final By DROPDOWN = By.id("dropdown");
  private static final By SELECTED_OPTION = By.cssSelector("#dropdown option:checked");

  public TheInternetDropdownPage(WebDriver driver) {
    super(driver);
  }

  public void open(String baseUrl) {
    driver.get(baseUrl + "/dropdown");
  }

  public void choose(String optionText) {
    selectByVisibleText(DROPDOWN, optionText);
  }

  public String selectedOption() {
    return text(SELECTED_OPTION);
  }
}

