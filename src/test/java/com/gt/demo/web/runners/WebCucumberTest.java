package com.gt.demo.web.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
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
    // Targets versioned smoke web scenarios by default; training-specific tags are optional.
    tags = "@web and @smoke and not @wip")
public class WebCucumberTest extends AbstractTestNGCucumberTests {

  @Override
  @Test(dataProvider = "scenarios")
  public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
    super.runScenario(pickleWrapper, featureWrapper);
  }

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
