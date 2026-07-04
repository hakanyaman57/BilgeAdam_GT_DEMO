@web
Feature: ParaBank business flows
    Business-like scenarios are kept simple for training and explainability.

  @smoke @happy
  Scenario: Invalid login should show a clear business error
    Given I open ParaBank home page
    And Parabank ana sayfasini actim
    When I login with invalid business credentials
    Then I should see a business login error

  @debug @smoke
  Scenario: Registration form fill should keep entered values
    Given I open ParaBank registration page
    When I complete business registration with unique user
    Then I should see registration form filled with my generated username

  Scenario: User should login with valid user and password
    Given Parabank ana sayfasini actim
    When I login with valid business credentials
    Then I should see a welcome message with my username

  Scenario Outline: Valid user - invalid password and valid user - empty password
    Given I open ParaBank home page
    When I enter username john and password = "<password>"
    Then I should see Error message "<error_message>"

    Examples:
      | password | error_message                                   |
      |     1234 | The username and password could not be verified |
      |          | Please enter a username and password            |
