package com.gt.demo.web.pages.business;

import com.gt.demo.web.base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParaBankHomePage extends BaseWebPage {
  private static final By USERNAME = By.name("username");
  private static final By PASSWORD = By.name("password");
  private static final By LOGIN_BUTTON = By.cssSelector("input.button[value='Log In']");
  private static final By REGISTER_LINK = By.linkText("Register");
  private static final By LOGIN_ERROR = By.cssSelector("#rightPanel p.error");
  private static final By LOGOUT_LINK = By.linkText("Log Out");

  public ParaBankHomePage(WebDriver driver) {
    super(driver);
  }

  public void open(String baseUrl) {
    driver.get(baseUrl + "/index.htm");
  }

  public void login(String username, String password) {
    type(USERNAME, username);
    type(PASSWORD, password);
    click(LOGIN_BUTTON);
  }

  public void goToRegister() {
    click(REGISTER_LINK);
  }

  public String loginError() {
    return text(LOGIN_ERROR);
  }

  public boolean isLoggedIn() {
    return !driver.findElements(LOGOUT_LINK).isEmpty();
  }
}
