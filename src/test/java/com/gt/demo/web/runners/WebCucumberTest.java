package com.gt.demo.web.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "classpath:features/web",
    glue = {"com.gt.demo.web"},
    plugin = {
      "pretty",
      "html:target/cucumber/web/cucumber-report.html",
      "json:target/cucumber/web/cucumber.json"
    },
    tags = "@smoke and @web and not @wip")
public class WebCucumberTest extends AbstractTestNGCucumberTests {

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}

