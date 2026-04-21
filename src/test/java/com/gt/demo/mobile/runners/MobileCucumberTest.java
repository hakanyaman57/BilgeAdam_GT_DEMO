package com.gt.demo.mobile.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "classpath:features/mobile",
    glue = {"com.gt.demo.mobile"},
    plugin = {
      "pretty",
      "html:target/cucumber/mobile/cucumber-report.html",
      "json:target/cucumber/mobile/cucumber.json"
    },
    tags = "@mobile and not @wip")
public class MobileCucumberTest extends AbstractTestNGCucumberTests {

  /**
   * Helps VS Code/TestNG show Run/Debug CodeLens on this runner class in remote environments.
   */
  @Test(enabled = false)
  public void codeLensPlaceholder() {}

  @Override
  @DataProvider(parallel = false)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
