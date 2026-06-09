@web @smoke
Feature: Web Exercises

  Scenario: Geçerli kullanıcı ile login yapılabilmeli
    Given I open ParaBank home page
    When I login with valid business credentials
    Then I should be on the ParaBank accounts page


    Scenario: Yanlış şifre ile login yapılırsa hata mesajı görülmeli
    Given I open ParaBank home page
    When I login with invalid business credentials
    Then I should see a business login error

    Scenario: Dropdown Option 1 seçimi
    Given I open The Internet dropdown page
    When I choose dropdown option "Option 1"
    Then I should see selected dropdown option "Option 1"

    Scenario Outline: Dropdown'dan <option> seçilmeli
    Given I open The Internet dropdown page
    When I choose dropdown option "<option>"
    Then I should see selected dropdown option "<option>"

    Examples:
      | option   |
      | Option 1 |
      | Option 2 |