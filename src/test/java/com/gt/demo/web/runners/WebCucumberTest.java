package com.gt.demo.web.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "classpath:features/web/",
    glue = {"com.gt.demo.web"},
    plugin = {
      "pretty",
      "html:target/cucumber/web/cucumber-report.html",
      "json:target/cucumber/web/cucumber.json"
    },
    tags = "@Egitim and @smoke and @web and not @wip")
public class WebCucumberTest extends AbstractTestNGCucumberTests {

  /**
   * VS Code/TestNG discovery in some environments expects at least one local @Test method
   * to show the Run/Debug CodeLens for this class. This placeholder is never executed.
   */
  @Test(enabled = false)
  public void codeLensPlaceholder() {}

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
