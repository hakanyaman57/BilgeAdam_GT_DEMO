package com.gt.demo.mobile.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginScreen extends BaseMobileScreen {
  private static final By LOGIN_SCREEN_BUTTON_ANDROID = AppiumBy.accessibilityId("Login Screen");
  private static final By LOGIN_SCREEN_BUTTON_IOS = AppiumBy.accessibilityId("Login Screen");

  private static final By USERNAME_FIELD_ANDROID = AppiumBy.accessibilityId("username");
  private static final By USERNAME_FIELD_IOS = AppiumBy.accessibilityId("username");

  private static final By PASSWORD_FIELD_ANDROID = AppiumBy.accessibilityId("password");
  private static final By PASSWORD_FIELD_IOS = AppiumBy.accessibilityId("password");

  private static final By LOGIN_BUTTON_ANDROID = AppiumBy.accessibilityId("loginBtn");
  private static final By LOGIN_BUTTON_IOS = AppiumBy.accessibilityId("loginBtn");

  private static final By LOGGED_IN_TEXT_ANDROID =
      AppiumBy.xpath("//*[contains(@text,'You are logged in as')]");
  private static final By LOGGED_IN_TEXT_IOS =
      AppiumBy.iOSNsPredicateString("label CONTAINS 'You are logged in as'");

  public LoginScreen(AppiumDriver driver) {
    super(driver);
  }

  public void openLoginForm() {
    tap(LOGIN_SCREEN_BUTTON_ANDROID, LOGIN_SCREEN_BUTTON_IOS);
  }

  public void login(String username, String password) {
    type(USERNAME_FIELD_ANDROID, USERNAME_FIELD_IOS, username);
    type(PASSWORD_FIELD_ANDROID, PASSWORD_FIELD_IOS, password);
    tap(LOGIN_BUTTON_ANDROID, LOGIN_BUTTON_IOS);
  }

  public boolean isLoginSuccessful() {
    return isVisible(LOGGED_IN_TEXT_ANDROID, LOGGED_IN_TEXT_IOS);
  }
}
