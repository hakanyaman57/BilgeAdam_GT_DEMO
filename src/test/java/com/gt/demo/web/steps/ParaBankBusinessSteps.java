package com.gt.demo.web.steps;

import com.gt.demo.common.config.FrameworkConfig;
import com.gt.demo.web.base.WebDriverContext;
import com.gt.demo.web.pages.business.ParaBankHomePage;
import com.gt.demo.web.pages.business.ParaBankOpenAccountPage;
import com.gt.demo.web.pages.business.ParaBankRegisterPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class ParaBankBusinessSteps {
  private static final Logger LOG = LoggerFactory.getLogger(ParaBankBusinessSteps.class);
  private final ParaBankHomePage homePage = new ParaBankHomePage(WebDriverContext.get());
  private final ParaBankOpenAccountPage openAccountPage =
      new ParaBankOpenAccountPage(WebDriverContext.get());
  private final ParaBankRegisterPage registerPage = new ParaBankRegisterPage(WebDriverContext.get());
  private String generatedUsername;
  private final String generatedPassword = "Training123!";
  private String selectedFundingAccountId;
  private String newAccountId;

  @Given("Parabank ana sayfasini actim")
  @Given("I open ParaBank home page")
  public void openParaBankHomePage() {
    homePage.open(FrameworkConfig.getRequired("web.business.baseUrl"));
  }

  @When("I login with invalid business credentials")
  public void loginWithInvalidBusinessCredentials() {
    homePage.login("invalid-user", "invalid-pass");
  }

  @Then("I should see a business login error")
  public void shouldSeeBusinessLoginError() {
    String actualError = homePage.loginError();
    Assert.assertTrue(
        actualError.contains("could not be verified"),
        "Expected ParaBank invalid login message to contain 'could not be verified' but was: "
            + actualError);
  }

  @Given("I open ParaBank registration page")
  public void openParaBankRegistrationPage() {
    registerPage.open(FrameworkConfig.getRequired("web.business.baseUrl"));
  }

  @When("I complete business registration with unique user")
  public void completeBusinessRegistrationWithUniqueUser() {
    generatedUsername = "trainer" + System.currentTimeMillis();
    LOG.info("Generated ParaBank username: {}", generatedUsername);
    registerPage.fillRegistrationForm(generatedUsername, generatedPassword);
    registerPage.submitRegistration();
    Assert.assertTrue(
        registerPage.isRegistrationSuccessfulFor(generatedUsername),
        "Expected first registration to be successful for username: " + generatedUsername);
  }

  @When("I fill business registration form with unique user")
  public void fillBusinessRegistrationFormWithUniqueUser() {
    generatedUsername = "trainer" + System.currentTimeMillis();
    LOG.info("Generated ParaBank username: {}", generatedUsername);
    registerPage.fillRegistrationForm(generatedUsername, generatedPassword);
  }

  @When("I try to register again with the same username")
  public void tryToRegisterAgainWithTheSameUsername() {
    Assert.assertNotNull(
        generatedUsername,
        "Expected a generated username from the first registration step.");
    homePage.logout();
    registerPage.open(FrameworkConfig.getRequired("web.business.baseUrl"));
    registerPage.fillRegistrationForm(generatedUsername, generatedPassword);
    registerPage.submitRegistration();
  }

  @Then("I should see registration form filled with my generated username")
  public void shouldSeeRegistrationFormFilledWithMyGeneratedUsername() {
    Assert.assertEquals(
        registerPage.enteredUsername(),
        generatedUsername,
        "Expected registration form to keep the entered username.");
  }

  @Then("I should see a registration error message")
  public void shouldSeeRegistrationErrorMessage() {
    String actualError = registerPage.usernameErrorMessage();
    Assert.assertTrue(
        actualError.contains("already exists"),
        "Expected duplicate username message to contain 'already exists' but was: " + actualError);
  }

  @Then("I should see the accounts overview page")
  public void I_should_see_the_accounts_overview_page() {
    Assert.assertTrue(
        homePage.isAccountsOverviewVisible(),
        "Expected to see the accounts overview page after successful login but did not.");
  }

  @When("I login with valid business credentials")
  public void I_login_with_valid_business_credentials() {
    homePage.login("john", "demo");
  }

  @Then("I should see a welcome message with my username")
  public void I_should_see_a_welcome_message_with_my_username() {
    String actualWelcomeMessage = homePage.welcomeMessage();
    Assert.assertTrue(
        actualWelcomeMessage.contains("Welcome"),
        "Expected welcome message to contain Welcome but was: " + actualWelcomeMessage);
  }


  @Given("I go to the Open New Account page")
  public void goToTheOpenNewAccountPage() {
    openAccountPage.goToOpenNewAccount();
  }

  @Given("I have opened a new account successfully")
  public void openANewAccountSuccessfully() {
    openAccountPage.goToOpenNewAccount();
    openAccountPage.selectAccountType("CHECKING");
    selectedFundingAccountId = openAccountPage.selectFirstFundingAccount();
    LOG.info("Selected ParaBank funding account: {}", selectedFundingAccountId);
    openAccountPage.submitOpenAccountForm();
    newAccountId = openAccountPage.newAccountNumber();
    LOG.info("Opened ParaBank account: {}", newAccountId);
  }

  @When("I select {string} as the new account type")
  public void selectNewAccountType(String accountType) {
    openAccountPage.selectAccountType(accountType);
  }

  @When("I select an existing account as the funding account")
  public void selectExistingFundingAccount() {
    selectedFundingAccountId = openAccountPage.selectFirstFundingAccount();
    LOG.info("Selected ParaBank funding account: {}", selectedFundingAccountId);
  }

  @When("I select account id {string} as the funding account")
  public void selectFundingAccountById(String accountId) {
    selectedFundingAccountId = accountId;
    openAccountPage.selectFundingAccount(accountId);
  }

  @When("I submit the open new account form")
  public void submitOpenNewAccountForm() {
    openAccountPage.submitOpenAccountForm();
    newAccountId = openAccountPage.newAccountNumber();
    LOG.info("Opened ParaBank account: {}", newAccountId);
  }

  @When("I click the new account number link")
  public void clickTheNewAccountNumberLink() {
    Assert.assertNotNull(newAccountId, "Expected a new account number before clicking its link.");
    openAccountPage.clickNewAccountNumber();
  }

  @Then("I should see the account opened confirmation message")
  public void shouldSeeAccountOpenedConfirmationMessage() {
    Assert.assertTrue(
        openAccountPage.isAccountOpened(),
        "Expected the account opened confirmation message to be visible.");
  }

  @Then("I should see {string}")
  public void shouldSeeTextOnOpenAccountPage(String expectedText) {
    Assert.assertTrue(
        openAccountPage.containsText(expectedText),
        "Expected open account page to contain text: " + expectedText);
  }

  @Then("I should see the new account number")
  public void shouldSeeTheNewAccountNumber() {
    newAccountId = openAccountPage.newAccountNumber();
    Assert.assertFalse(newAccountId.isBlank(), "Expected a new account number to be shown.");
  }

  @Then("I should see the account activity page for the new account")
  public void shouldSeeAccountActivityForTheNewAccount() {
    Assert.assertNotNull(newAccountId, "Expected a new account number before checking activity.");
    Assert.assertTrue(
        openAccountPage.isAccountActivityVisibleFor(newAccountId),
        "Expected account activity page for new account id: " + newAccountId);
  }
}
