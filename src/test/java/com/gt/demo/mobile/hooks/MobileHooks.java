package com.gt.demo.mobile.hooks;

import com.gt.demo.mobile.base.MobileDriverContext;
import com.gt.demo.mobile.base.MobileDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.util.Collection;

public class MobileHooks {

  @Before("@mobile")
  public void startMobile(Scenario scenario) {
    Collection<String> tags = scenario.getSourceTagNames();
    String appProfile = tags.contains("@apidemos") ? "apidemos" : "theapp";
    MobileDriverContext.set(MobileDriverFactory.create(appProfile));
  }

  @After("@mobile")
  public void stopMobile() {
    MobileDriverContext.clear();
  }
}
