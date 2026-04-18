package com.gt.demo.mobile.base;

import com.gt.demo.common.config.FrameworkConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class MobileDriverFactory {
  private MobileDriverFactory() {}

  public static AppiumDriver create() {
    return create(FrameworkConfig.get("mobile.appProfile"));
  }

  public static AppiumDriver create(String appProfile) {
    String platform = FrameworkConfig.getRequired("mobile.platform"); // android | ios
    URL serverUrl = toUrl(FrameworkConfig.getRequired("appium.serverUrl"));
    String resolvedProfile =
        appProfile == null || appProfile.isBlank() ? "theapp" : appProfile.toLowerCase();

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("platformName", platform);

    if ("android".equalsIgnoreCase(platform)) {
      caps.setCapability("automationName", FrameworkConfig.getRequired("android.automationName"));
      caps.setCapability("deviceName", FrameworkConfig.getRequired("android.deviceName"));

      if ("apidemos".equals(resolvedProfile)) {
        caps.setCapability("app", FrameworkConfig.getRequired("android.apidemos.app"));
        caps.setCapability("appPackage", FrameworkConfig.getRequired("android.apidemos.appPackage"));
        caps.setCapability("appActivity", FrameworkConfig.getRequired("android.apidemos.appActivity"));
      } else {
        caps.setCapability("app", FrameworkConfig.getRequired("android.theapp.app"));
      }
      return new AndroidDriver(serverUrl, caps);
    }

    if ("ios".equalsIgnoreCase(platform)) {
      if ("apidemos".equals(resolvedProfile)) {
        throw new IllegalArgumentException("ApiDemos hybrid target is Android-only in this project.");
      }
      caps.setCapability("automationName", FrameworkConfig.getRequired("ios.automationName"));
      caps.setCapability("deviceName", FrameworkConfig.getRequired("ios.deviceName"));
      caps.setCapability("platformVersion", FrameworkConfig.getRequired("ios.platformVersion"));
      caps.setCapability("app", FrameworkConfig.getRequired("ios.theapp.app"));
      return new IOSDriver(serverUrl, caps);
    }

    throw new IllegalArgumentException("Unsupported mobile.platform: " + platform);
  }

  private static URL toUrl(String value) {
    try {
      return URI.create(value).toURL();
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("Invalid appium.serverUrl: " + value, e);
    }
  }
}
