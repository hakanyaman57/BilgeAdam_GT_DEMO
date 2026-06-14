Feature: ParaBank API examples
  API examples mirror common ParaBank web flows for training readability.

  Background:
    * url "https://parabank.parasoft.com/parabank/services/bank"
    * configure headers = { Accept: "application/json" }
    * def demoUsername = "john"
    * def demoPassword = "demo"
    * def checkingType = 0
    * def savingsType = 1

  # curl -X GET -H "Accept: application/json" \
  #https://parabank.parasoft.com/parabank/services/bank/login/john/demo
  # curl -H "Accept: application/json" \
  #   https://parabank.parasoft.com/parabank/services/bank/customers/12212

@parabank
Scenario: Login with demo customer and read customer details
    Given path "login", demoUsername, demoPassword
    When method get
    Then status 200
    And match response.id == "#number"
    And match response.firstName == "John"
    And match response.lastName == "Smith"
    And match response.address.city == "Beverly Hills"
    * def customerId = response.id

    Given path "customers", customerId
    When method get
    Then status 200
    And match response.id == customerId
    And match response.firstName == "John"
    And match response.lastName == "Smith"

  # curl -H "Accept: application/json" \
  #   https://parabank.parasoft.com/parabank/services/bank/login/john/demo
  # curl -H "Accept: application/json" \
  #   https://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts
  # curl -H "Accept: application/json" \
  #   https://parabank.parasoft.com/parabank/services/bank/accounts/12345
  Scenario: Customer should have a list of accounts
    Given path "login", demoUsername, demoPassword
    When method get
    Then status 200
    * def customerId = response.id

    Given path "customers", customerId, "accounts"
    When method get
    Then status 200
    And match response == "#[]"
    And match each response contains { id: "#number", customerId: "#(customerId)", type: "#string", balance: "#number" }
    * def firstAccount = response[0]

    Given path "accounts", firstAccount.id
    When method get
    Then status 200
    And match response.id == firstAccount.id
    And match response.customerId == customerId
    And match response.type == firstAccount.type

  # curl -H "Accept: application/json" \
  #   https://parabank.parasoft.com/parabank/services/bank/login/john/demo
  # curl -H "Accept: application/json" \
  #   https://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts
  # curl -X POST -H "Accept: application/json" \
  #   "https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId=12212&newAccountType=1&fromAccountId=12345"
  Scenario: Customer opens a new savings account from an existing account
    Given path "login", demoUsername, demoPassword
    When method get
    Then status 200
    * def customerId = response.id

    Given path "customers", customerId, "accounts"
    When method get
    Then status 200
    * def fundingAccountId = response[0].id

    Given path "createAccount"
    And param customerId = customerId
    And param newAccountType = savingsType
    And param fromAccountId = fundingAccountId
    When method post
    Then status 200
    And match response.id == "#number"
    And match response.customerId == customerId
    And match response.type == "SAVINGS"
    And match response.balance == "#number"
    * def newAccountId = response.id

    Given path "accounts", newAccountId
    When method get
    Then status 200
    And match response.id == newAccountId
    And match response.customerId == customerId
    And match response.type == "SAVINGS"

  # curl -H "Accept: application/json" \
  #   https://parabank.parasoft.com/parabank/services/bank/login/john/demo
  # curl -H "Accept: application/json" \
  #   https://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts
  # curl -X POST -H "Accept: application/json" \
  #   "https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId=12212&newAccountType=0&fromAccountId=12345"
  # curl -X POST -H "Accept: application/json" \
  #   "https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId=12212&newAccountType=1&fromAccountId=12345"
  Scenario Outline: Customer opens new account by account type
    Given path "login", demoUsername, demoPassword
    When method get
    Then status 200
    * def customerId = response.id

    Given path "customers", customerId, "accounts"
    When method get
    Then status 200
    * def fundingAccountId = response[0].id

    Given path "createAccount"
    And param customerId = customerId
    And param newAccountType = <accountTypeCode>
    And param fromAccountId = fundingAccountId
    When method post
    Then status 200
    And match response.id == "#number"
    And match response.customerId == customerId
    And match response.type == "<expectedType>"

    Examples:
      | accountTypeCode | expectedType |
      | 0               | CHECKING     |
      | 1               | SAVINGS      |
