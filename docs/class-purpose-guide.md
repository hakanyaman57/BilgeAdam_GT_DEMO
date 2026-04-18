# Class Purpose Guide

This file is a trainer-friendly map of the key classes and what they do.

## Common

- `com.gt.demo.common.config.FrameworkConfig`  
  Loads `src/test/resources/config/framework.properties` and exposes simple getters.

## Web

- `com.gt.demo.web.base.WebDriverFactory`  
  Creates a local WebDriver (Chrome/Firefox) using Selenium Manager.
- `com.gt.demo.web.base.WebDriverContext`  
  Thread-local storage for WebDriver (safe for parallel scenarios).
- `com.gt.demo.web.hooks.WebHooks`  
  Starts/quits WebDriver per scenario.
- `com.gt.demo.web.pages.*`  
  Page Objects (kept small and explicit for training).
- `com.gt.demo.web.steps.*`  
  Cucumber step definitions.
- `com.gt.demo.web.runners.WebCucumberTest`  
  TestNG + Cucumber runner for web features.

## Mobile

- `com.gt.demo.mobile.base.MobileDriverFactory`  
  Creates Android/iOS driver (Appium) and supports `theapp` / `apidemos` app profiles.
- `com.gt.demo.mobile.base.MobileDriverContext`  
  Thread-local storage for Appium driver.
- `com.gt.demo.mobile.hooks.MobileHooks`  
  Starts/quits mobile driver per scenario and chooses app profile from scenario tags.
- `com.gt.demo.mobile.screens.*`  
  Screen Objects with Android/iOS locator separation and accessibility-id-first strategy.
- `com.gt.demo.mobile.steps.*`  
  Step definitions for native and hybrid flows.
- `com.gt.demo.mobile.runners.MobileCucumberTest`  
  TestNG + Cucumber runner for mobile features (enabled with `-Pmobile`).

## API

- `com.gt.demo.api.karate.runners.ApiKarateTest`  
  JUnit 5 Karate runner for features under `src/test/resources/features/api`.
