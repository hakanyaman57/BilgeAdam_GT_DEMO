@web @practice @account-services
Feature: ParaBank account services practice
  Business analysts write BDD scenarios for opening a new ParaBank account.

  Background:
    Given I open ParaBank home page
    And I login with valid business credentials
    And I should see the accounts overview page

  @smoke @happy
  Scenario: Customer should open a new checking account
    Given I go to the Open New Account page
    When I select "CHECKING" as the new account type
    And I select an existing account as the funding account
    And I submit the open new account form
    Then I should see the account opened confirmation message
    And I should see "Congratulations, your account is now open."
    And I should see the new account number

  Scenario: Customer should open a new savings account
    Given I go to the Open New Account page
    When I select "SAVINGS" as the new account type
    And I select an existing account as the funding account
    And I submit the open new account form
    Then I should see the account opened confirmation message
    And I should see the new account number

  Scenario: Open new account page should explain the minimum opening deposit
    Given I go to the Open New Account page
    Then I should see "A minimum of $100.00 must be deposited into this account at time of opening."

  Scenario Outline: Customer should choose account type before opening a new account
    Given I go to the Open New Account page
    When I select "<accountType>" as the new account type
    And I select an existing account as the funding account
    And I submit the open new account form
    Then I should see the account opened confirmation message
    And I should see the new account number

    Examples:
      | accountType |
      | CHECKING    |
      | SAVINGS     |

  Scenario: New account number should navigate to account activity
    Given I have opened a new account successfully
    When I click the new account number link
    Then I should see the account activity page for the new account
