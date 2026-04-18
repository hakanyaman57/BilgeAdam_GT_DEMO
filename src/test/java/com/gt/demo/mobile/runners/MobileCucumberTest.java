package com.gt.demo.mobile.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

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
  @DataProvider(parallel = false)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}

