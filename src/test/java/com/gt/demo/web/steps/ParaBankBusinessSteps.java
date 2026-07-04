package com.gt.demo.web.steps;

import com.gt.demo.common.config.FrameworkConfig;
import com.gt.demo.web.base.WebDriverContext;
import com.gt.demo.web.pages.business.ParaBankHomePage;
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
  private final ParaBankRegisterPage registerPage = new ParaBankRegisterPage(WebDriverContext.get());
  private final ParaBankRequestLoanPage ParaBankRequestLoanPage = new ParaBankRequestLoanPage(WebDriverContext.get());
  private String generatedUsername;
  private final String generatedPassword = "Training123!";
  private static final string demoUser = 'john';
  private static final string demoPassword = 'demo';

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
    generatedUsername = "trainer_" + System.currentTimeMillis();
    LOG.info("Generated ParaBank username: {}", generatedUsername);
    registerPage.fillRegistrationForm(generatedUsername, generatedPassword);
  }

  @Then("I should see registration form filled with my generated username")
  public void shouldSeeRegistrationFormFilledWithMyGeneratedUsername() {
    Assert.assertEquals(
        registerPage.enteredUsername(),
        generatedUsername,
        "Expected registration form to keep the entered username.");
  }

    @When("I login with valid business credentials")
    public void I_login_with_valid_business_credentials() {
        // Write code here that turns the phrase above into concrete actions
        homePage.login(demoUser, demoPassword);
        Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in with valid credentials");
    }

    @Then("I should see a welcome message with my username")
    public void I_should_see_a_welcome_message_with_my_username() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(
          homePage.isWelcomeMessageDisplayed(EXPECTED_CUSTOMER_NAME),
          "Expected welcome message not found:"
            +EXPECTED_CUSTOMER_NAME
            +"but actual message was:");

    }

    @When("I enter username john and password &#x3D; {string}")
    public void I_enter_username_john_and_password_x_D(String s) {
        // Write code here that turns the phrase above into concrete actions
      homePage.login(username, password);
      Assert.assertTrue(actualError.contains(errorMessage), "The username and password could not be verified");
    }

