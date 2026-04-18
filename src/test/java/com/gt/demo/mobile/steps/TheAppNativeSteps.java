package com.gt.demo.mobile.steps;

import com.gt.demo.mobile.base.MobileDriverContext;
import com.gt.demo.mobile.screens.LoginScreen;
import com.gt.demo.mobile.screens.ProductListScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class TheAppNativeSteps {
  private final LoginScreen loginScreen = new LoginScreen(MobileDriverContext.get());
  private final ProductListScreen productListScreen = new ProductListScreen(MobileDriverContext.get());

  @Given("the TheApp is launched")
  public void theTheAppIsLaunched() {
    Assert.assertNotNull(MobileDriverContext.get(), "Expected mobile driver to be initialized.");
  }

  @When("I login successfully on TheApp")
  public void loginSuccessfullyOnTheApp() {
    loginScreen.openLoginForm();
    loginScreen.login("alice", "mypassword");
  }

  @Then("I should see successful native login state")
  public void shouldSeeSuccessfulNativeLoginState() {
    Assert.assertTrue(loginScreen.isLoginSuccessful(), "Expected successful login message.");
  }

  @Given("I am on TheApp home screen")
  public void iAmOnTheAppHomeScreen() {
    Assert.assertNotNull(MobileDriverContext.get(), "Expected mobile driver to be initialized.");
  }

  @When("I open list demo on TheApp")
  public void iOpenListDemoOnTheApp() {
    productListScreen.openListDemo();
  }

  @Then("I should see list items in TheApp")
  public void iShouldSeeListItemsInTheApp() {
    Assert.assertTrue(productListScreen.hasVisibleListContent(), "Expected list items to be visible.");
    Assert.assertTrue(
        productListScreen.visibleItemCountByTextFallback() > 0,
        "Expected at least one list item with known label.");
  }
}
