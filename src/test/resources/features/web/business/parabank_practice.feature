@web @practice
Feature: ParaBank login and registration practice
  Business analysts write BDD scenarios by reusing existing framework steps where possible.

  Scenario: Valid username and valid password login should be successful
    Given I open ParaBank home page
    When I login with valid business credentials
    Then I should see the accounts overview page
    And I should see a welcome message with my username

  Scenario: Valid username and invalid password login should show an error
    Given I open ParaBank home page
    When I login with valid username and invalid password
    Then I should see a business login error

  Scenario Outline: Login form should validate missing credentials
    Given I open ParaBank home page
    When I try to login with username "<username>" and password "<password>"
    Then I should see a validation message "<message>"

    Examples:
      | username | password | message |
      |          |          | Please enter a username |
      | user1    |          | Please enter a password |
      |          | pass1    | Please enter a username |

  Scenario: Duplicate username registration should be rejected
    Given I open ParaBank registration page
    When I complete business registration with unique user
    And I try to register again with the same username
    Then I should see a registration error message
