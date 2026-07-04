@web
Feature: ParaBank request Loan
    As a user I want to apply loan

  @smoke @happy
  Scenario: Success Apply
    Given I open ParaBank home page
    And I login with valid business cridentials.
    When I open Request Loan Page
    And I enter loan amount 50$ and I enter down payment 50 $
    And I click apply button
    Then I should see approved text: Congratulations, your loan has been approved.
