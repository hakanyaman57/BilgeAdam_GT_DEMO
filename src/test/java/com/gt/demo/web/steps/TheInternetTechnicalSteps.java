package com.gt.demo.web.steps;

import com.gt.demo.common.config.FrameworkConfig;
import com.gt.demo.web.base.WebDriverContext;
import com.gt.demo.web.pages.technical.TheInternetDropdownPage;
import com.gt.demo.web.pages.technical.TheInternetDynamicLoadingPage;
import com.gt.demo.web.pages.technical.TheInternetHomePage;
import com.gt.demo.web.pages.technical.TheInternetIframePage;
import com.gt.demo.web.pages.technical.TheInternetShadowDomPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class TheInternetTechnicalSteps {
  private final TheInternetHomePage homePage = new TheInternetHomePage(WebDriverContext.get());
  private final TheInternetDropdownPage dropdownPage =
      new TheInternetDropdownPage(WebDriverContext.get());
  private final TheInternetIframePage iframePage = new TheInternetIframePage(WebDriverContext.get());
  private final TheInternetDynamicLoadingPage dynamicPage =
      new TheInternetDynamicLoadingPage(WebDriverContext.get());
  private final TheInternetShadowDomPage shadowDomPage =
      new TheInternetShadowDomPage(WebDriverContext.get());

  private String baseUrl() {
    return FrameworkConfig.getRequired("web.tech.baseUrl");
  }

  @Given("I open The Internet technical lab home page")
  public void openTechnicalLabHomePage() {
    homePage.open(baseUrl());
  }

  @Then("I should see technical lab heading {string}")
  public void shouldSeeTechnicalLabHeading(String expected) {
    Assert.assertEquals(homePage.headingText(), expected);
  }

  @Given("I open The Internet dropdown page")
  public void openDropdownPage() {
    dropdownPage.open(baseUrl());
  }

  @When("I choose dropdown option {string}")
  public void chooseDropdownOption(String option) {
    dropdownPage.choose(option);
  }

  @Then("I should see selected dropdown option {string}")
  public void shouldSeeSelectedDropdownOption(String option) {
    Assert.assertEquals(dropdownPage.selectedOption(), option);
  }

  @Given("I open The Internet iframe page")
  public void openIframePage() {
    iframePage.open(baseUrl());
  }

  @When("I type {string} into the iframe editor")
  public void typeIntoIframeEditor(String message) {
    iframePage.writeText(message);
  }

  @Then("iframe editor should contain {string}")
  public void iframeEditorShouldContain(String expectedText) {
    Assert.assertTrue(
        iframePage.editorText().contains(expectedText),
        "Expected editor text to contain: " + expectedText);
  }

  @Given("I open The Internet dynamic loading page")
  public void openDynamicLoadingPage() {
    dynamicPage.open(baseUrl());
  }

  @When("I trigger dynamic loading")
  public void triggerDynamicLoading() {
    dynamicPage.startLoading();
  }

  @Then("I should see dynamic loading result {string}")
  public void shouldSeeDynamicLoadingResult(String expected) {
    Assert.assertEquals(dynamicPage.finishText(), expected);
  }

  @Given("I open The Internet shadow DOM page")
  public void openShadowDomPage() {
    shadowDomPage.open(baseUrl());
  }

  @Then("I should read slotted shadow text containing {string}")
  public void shouldReadSlottedShadowTextContaining(String expected) {
    Assert.assertTrue(
        shadowDomPage.firstSlottedText().contains(expected),
        "Expected slotted shadow text to contain: " + expected);
  }
}

