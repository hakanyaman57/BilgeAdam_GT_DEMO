package com.gt.demo.web.pages.business;

import com.gt.demo.web.base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ParaBankOpenAccountPage extends BaseWebPage {
  private static final By OPEN_NEW_ACCOUNT_LINK = By.linkText("Open New Account");
  private static final By PAGE_TITLE = By.cssSelector("#openAccountForm h1.title");
  private static final By ACCOUNT_TYPE = By.id("type");
  private static final By FROM_ACCOUNT = By.id("fromAccountId");
  private static final By OPEN_ACCOUNT_BUTTON =
      By.cssSelector("input.button[value='Open New Account']");
  private static final By RESULT_TITLE = By.cssSelector("#openAccountResult h1.title");
  private static final By RIGHT_PANEL = By.id("rightPanel");
  private static final By NEW_ACCOUNT_ID = By.id("newAccountId");
  private static final By ACCOUNT_DETAILS_TITLE = By.cssSelector("#accountDetails h1.title");
  private static final By ACTIVITY_TITLE = By.cssSelector("#accountActivity h1.title");

  public ParaBankOpenAccountPage(WebDriver driver) {
    super(driver);
  }

  public void goToOpenNewAccount() {
    click(OPEN_NEW_ACCOUNT_LINK);
    visible(PAGE_TITLE);
    waitForFundingAccounts();
  }

  public void selectAccountType(String accountType) {
    selectByVisibleText(ACCOUNT_TYPE, accountType);
  }

  public String selectFirstFundingAccount() {
    waitForFundingAccounts();
    Select accounts = new Select(visible(FROM_ACCOUNT));
    accounts.selectByIndex(0);
    return accounts.getFirstSelectedOption().getText().trim();
  }

  public void selectFundingAccount(String accountId) {
    waitForFundingAccounts();
    selectByVisibleText(FROM_ACCOUNT, accountId);
  }

  public void submitOpenAccountForm() {
    click(OPEN_ACCOUNT_BUTTON);
    visible(RESULT_TITLE);
  }

  public boolean isAccountOpened() {
    return text(RESULT_TITLE).contains("Account Opened");
  }

  public boolean containsText(String expectedText) {
    return visible(RIGHT_PANEL).getText().contains(expectedText);
  }

  public String newAccountNumber() {
    return text(NEW_ACCOUNT_ID);
  }

  public void clickNewAccountNumber() {
    click(NEW_ACCOUNT_ID);
  }

  public boolean isAccountActivityVisibleFor(String accountId) {
    boolean detailsLoaded =
        waitUntilJsCondition(
            "return document.querySelector('#accountId')"
                + " && document.querySelector('#accountId').textContent.trim() === arguments[0];",
            accountId);

    return detailsLoaded
        && text(ACCOUNT_DETAILS_TITLE).contains("Account Details")
        && text(ACTIVITY_TITLE).contains("Account Activity");
  }

  private void waitForFundingAccounts() {
    waitUntilJsCondition(
        "return document.querySelector('#fromAccountId')"
            + " && document.querySelector('#fromAccountId').options.length > 0;");
  }
}
