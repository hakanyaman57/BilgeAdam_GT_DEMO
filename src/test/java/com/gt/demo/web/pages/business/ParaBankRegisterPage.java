package com.gt.demo.web.pages.business;

import com.gt.demo.web.base.BaseWebPage;
import com.gt.demo.web.locators.JsonLocatorRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParaBankRegisterPage extends BaseWebPage {
  private static final JsonLocatorRepository LOCATORS =
      JsonLocatorRepository.fromResource("locators/web/parabank-locators.json");

  private static final By FIRST_NAME = LOCATORS.by("register", "firstName");
  private static final By LAST_NAME = LOCATORS.by("register", "lastName");
  private static final By STREET = LOCATORS.by("register", "street");
  private static final By CITY = LOCATORS.by("register", "city");
  private static final By STATE = LOCATORS.by("register", "state");
  private static final By ZIP_CODE = LOCATORS.by("register", "zipCode");
  private static final By PHONE = LOCATORS.by("register", "phone");
  private static final By SSN = LOCATORS.by("register", "ssn");
  private static final By USERNAME = LOCATORS.by("register", "username");
  private static final By PASSWORD = LOCATORS.by("register", "password");
  private static final By REPEATED_PASSWORD = LOCATORS.by("register", "repeatedPassword");
  private static final By REGISTER_BUTTON = LOCATORS.by("register", "registerButton");

  public ParaBankRegisterPage(WebDriver driver) {
    super(driver);
  }

  public void open(String baseUrl) {
    driver.get(baseUrl + "/register.htm");
  }

  public void fillRegistrationForm(String username, String password) {
    type(FIRST_NAME, "Training");
    type(LAST_NAME, "User");
    type(STREET, "1 Demo Street");
    type(CITY, "Istanbul");
    type(STATE, "TR");
    type(ZIP_CODE, "34000");
    type(PHONE, "5551234567");
    type(SSN, "999991111");
    type(USERNAME, username);
    type(PASSWORD, password);
    type(REPEATED_PASSWORD, password);
  }

  public void submitRegistration() {
    click(REGISTER_BUTTON);
  }

  public String enteredUsername() {
    return visible(USERNAME).getAttribute("value");
  }
}
