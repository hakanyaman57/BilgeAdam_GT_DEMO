@web @practice
Feature: ParaBank login and registration practice
  Business analysts write BDD scenarios by reusing existing framework steps where possible.

  Scenario: Valid username and valid password login should be successful
    Given I open ParaBank home page
    When I login with valid business credentials
    Then I should be on the ParaBank accounts page

  Scenario: Valid username and invalid password login should show an error
    Given I open ParaBank home page
    When I login with valid username and invalid password
    Then I should see a business login error

  Scenario Outline: Login form should validate missing credentials
    Given I open ParaBank home page
    When I try to login with username "<username>" and password "<password>"
    Then I should see a validation message

    Examples:
      | username | password |
      |          |          |
      | user1    |          |
      |          | pass1    |
