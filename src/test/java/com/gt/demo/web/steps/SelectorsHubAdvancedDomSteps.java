package com.gt.demo.web.steps;

import com.gt.demo.common.config.FrameworkConfig;
import com.gt.demo.web.base.WebDriverContext;
import com.gt.demo.web.pages.dom.SelectorsHubPracticePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SelectorsHubAdvancedDomSteps {
  private final SelectorsHubPracticePage page =
      new SelectorsHubPracticePage(WebDriverContext.get());
  private String openShadowValue;

  @Given("I open SelectorsHub advanced DOM page")
  public void openSelectorsHubAdvancedDomPage() {
    page.open(FrameworkConfig.getRequired("web.advancedDom.baseUrl"));
  }

  @When("I enter {string} inside open shadow root input")
  public void enterInsideOpenShadowRootInput(String value) {
    openShadowValue = page.fillOpenShadowInput(value);
  }

  @When("I enter {string} inside nested shadow root input")
  public void enterInsideNestedShadowRootInput(String value) {
    String nestedValue = page.fillNestedShadowInput(value);
    Assert.assertEquals(nestedValue, value, "Expected nested shadow value to be set.");
  }

  @Then("open shadow root input should keep value {string}")
  public void openShadowRootInputShouldKeepValue(String expected) {
    Assert.assertEquals(openShadowValue, expected, "Expected open shadow value to be set.");
    Assert.assertEquals(
        page.readOpenShadowInput(), expected, "Expected open shadow value to be readable.");
  }

  @When("I switch into an iframe and back to default content")
  public void switchIntoIframeAndBackToDefaultContent() {
    page.switchToAnyIframeAndBack();
  }
}

