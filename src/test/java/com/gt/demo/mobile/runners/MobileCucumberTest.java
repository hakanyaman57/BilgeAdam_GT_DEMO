package com.gt.demo.mobile.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
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

  @Override
  @Test(dataProvider = "scenarios")
  public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
    super.runScenario(pickleWrapper, featureWrapper);
  }

  @Override
  @DataProvider(parallel = false)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
