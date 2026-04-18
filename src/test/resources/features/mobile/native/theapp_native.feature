@mobile @android @theapp @smoke
Feature: TheApp native flows
  TheApp is used as the primary native training target for Android and iOS.

  Scenario: Login with accessibility id locators
    Given the TheApp is launched
    When I login successfully on TheApp
    Then I should see successful native login state

  Scenario: List interaction fallback (list validation)
    Given I am on TheApp home screen
    When I open list demo on TheApp
    Then I should see list items in TheApp
