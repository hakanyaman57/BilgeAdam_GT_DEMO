package com.gt.demo.web.hooks;

import com.gt.demo.web.base.WebDriverContext;
import com.gt.demo.web.base.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class WebHooks {

  @Before("@web")
  public void startWeb() {
    WebDriverContext.set(WebDriverFactory.create());
  }

  @After("@web")
  public void stopWeb() {
    WebDriverContext.clear();
  }
}

