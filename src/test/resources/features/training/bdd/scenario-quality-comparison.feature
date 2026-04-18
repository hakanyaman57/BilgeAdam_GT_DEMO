@training @bdd
Feature: Scenario quality comparison for trainers
  This file intentionally shows the same intent in bad and improved forms.

  Scenario: Poorly written scenario (DO NOT USE)
    Given I do all setup quickly
    When I click many things and wait a bit and continue
    Then it should work somehow

  Scenario: Improved explicit scenario
    Given I am on ParaBank login page
    When I login with invalid credentials
    Then I should see an authentication error message

  Scenario: Same intent without Scenario Outline
    Given I am on ParaBank login page
    When I login with username "invalid-user-1" and password "invalid-pass-1"
    Then I should see an authentication error message
    When I login with username "invalid-user-2" and password "invalid-pass-2"
    Then I should see an authentication error message

  Scenario Outline: Same intent with Scenario Outline (recommended)
    Given I am on ParaBank login page
    When I login with username "<username>" and password "<password>"
    Then I should see an authentication error message

    Examples:
      | username       | password       |
      | invalid-user-1 | invalid-pass-1 |
      | invalid-user-2 | invalid-pass-2 |
