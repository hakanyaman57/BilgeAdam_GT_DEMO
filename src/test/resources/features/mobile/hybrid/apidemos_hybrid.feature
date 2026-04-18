@mobile @android @apidemos @smoke
Feature: Android ApiDemos - hybrid/context switching
  This feature is strictly for teaching NATIVE_APP <-> WEBVIEW context switching.

  Scenario: Switch context between native and webview
    Given I open the ApiDemos hybrid screen
    When I switch to WEBVIEW context
    Then I can interact with a web element
