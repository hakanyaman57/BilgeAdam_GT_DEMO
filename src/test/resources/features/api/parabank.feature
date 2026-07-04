    @apiExercise
    Feature: Parabank fetaure

Background:
* url "https://parabank.parasoft.com/parabank/services/bank/
* configure headers = { Accept: "application/json", "Content-Type" : "application/json" }
* def demoUsername = "john"
* def demoPassword = "demo"
* def p_customerId = 12212
* def p_fromAccountId =12345



Scenario: Parabank Login 
    Given path "login"
    When method get
    Then status 200
    * print "login response:", response
    And match response.id == "number"
    And match response.lastName == "Smith"
    And def customerId = response.id

  Scenario: parabank create account
    Given path "createAccount"
    And param customerId = customerId
    And param newAccountType = 1
    And param fromAccountId = fromAccountId
    When method post
    Then status 200
    And print "Create "

  Scenario: Parabank Request Loan
    * def LoanRequest = read ("classpath:testdata/api/request-loan.json")
    Given path "requestLoan"
    And param customerId = loadRequest.customerId
    And param amount = loadRequest.amount
    And param downPayment = loadRequest.downPayment
    And param fromAccountId = loadRequest.fromAccountId
    * print "Request loan payload:", loanRequest
    When method post
    Then status 200
    * print "Request loan response:", response
    And match response.approved == "#boolean"
    And match response.approved == true

     Scenario: billpay
    Given path 'bill_payee'
    And request {"name: "Electric Company",
    "address": {
    "street": "100 main street"
    "city: "new yourk"
    "state: NY"
    "zipCode": "10001}
    "phoneNumber": "531-222-3233"
    "accountNumber": 12334  
}
    When method post
    Then status 200