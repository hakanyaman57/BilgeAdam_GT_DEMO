package com.gt.demo.mobile.steps;

import com.gt.demo.mobile.base.MobileDriverContext;
import com.gt.demo.mobile.screens.ApiDemosHybridScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ApiDemosHybridSteps {
  private final ApiDemosHybridScreen screen = new ApiDemosHybridScreen(MobileDriverContext.get());
  private String currentContext;
  private String clickedLinkText;

  @Given("I open the ApiDemos hybrid screen")
  public void iOpenTheApiDemosHybridScreen() {
    screen.openWebViewScreen();
  }

  @When("I switch to WEBVIEW context")
  public void iSwitchToWEBVIEWContext() {
    currentContext = screen.switchToWebViewContext();
    Assert.assertNotNull(currentContext, "Expected to switch into WEBVIEW context.");
  }

  @Then("I can interact with a web element")
  public void iCanInteractWithAWebElement() {
    clickedLinkText = screen.clickFirstWebLink();
    Assert.assertFalse(clickedLinkText.isBlank(), "Expected clickable web element text in WEBVIEW.");
    screen.switchToNative();
  }
}
