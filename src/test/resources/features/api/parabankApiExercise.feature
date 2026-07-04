
Feature: Parabank API Exercise

  Background:
    * url parabankBaseUrl
    * configure headers = { Accept: "application/json", "Content-Type": "application/json" }
    * def demoUsername = "john"
    * def demoPassword = "demo"
    * def p_fromAccountId = 12456
    * def p_customerId = 12212


    @login
  Scenario: Parabank Login
    Given path "login", demoUsername, demoPassword
    When method get
    Then status 200
    * print "Login response:", response
    And match response.id == "#number"
    And match response.lastName == "Smith"
    And def customerId = response.id


  
  Scenario: Parabank Create Account
    * def loginResult = call read("classpath:features/api/parabankApiExercise.feature@login")
    * def customerId = loginResult.customerId
    Given path "createAccount"
    And param customerId = customerId
    And param newAccountType = 1
    And param fromAccountId = fromAccountId
    When method post
    Then status 200
    And print "Create account response:", response
    And print "customerId:", customerId
    And match response.customerId == customerId


    Scenario: Parabank Login and Create Account
    Given path "login", demoUsername, demoPassword
    When method get
    Then status 200
    * print "Login response:", response
    And match response.id == "#number"
    And match response.lastName == "Smith"
    And def customerId = response.id

    Given path "createAccount"
    And param customerId = customerId
    And param newAccountType = 1
    And param fromAccountId = fromAccountId
    When method post
    Then status 200
    And print "Create account response:", response
    And print "customerId:", customerId
    And match response.customerId == customerId

    
    Scenario: Parabank Request Loan
    * def loanRequest = read("classpath:testdata/api/request-loan.json")
    Given path "requestLoan"
    And param customerId = loanRequest.customerId
    And param amount = loanRequest.amount
    And param downPayment = loanRequest.downPayment
    And param fromAccountId = loanRequest.fromAccountId
    * print "Request loan payload:", loanRequest
    When method post
    Then status 200
    * print "Request loan response:", response
    And match response.approved == "#boolean"
    And match response.approved == true

    @apiExercise
    Scenario: Parabank Bill Pay
    Given path "billpay"
    And configure headers = { Accept: "application/json", "Content-Type": "application/json" }
    And def payload = 
    """
    {
     "name": "Electric Company",
     "address": {
       "street": "100 Main Street",
       "city": "Anytown",
       "state": "CA",
       "zipCode": "12345"
     },
     "phoneNumber": "555-123-4567",
     "accountNumber": "#(p_fromAccountId)"
    }
    """
    And request payload
    When method post
    * print "Bill pay request payload:", payload
    * print "Bill pay response:", response
    And match payload.name == "Electric Company"
    And match payload.address.street == "100 Main Street"
    Then status 200

