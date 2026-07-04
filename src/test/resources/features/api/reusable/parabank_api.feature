Feature: ParaBank API smoke scenarios
  These examples are intentionally simple for training purposes.

  Background:
    * url 'https://parabank.parasoft.com'
    * configure headers = { Accept: 'application/xml' }

  Scenario: Get customer details from ParaBank
    Given path 'parabank/services/bank/customers/12212'
    When method get
    Then status 200
    And match response/customer/id == '12212'
    And match response/customer/firstName == 'John'
    And match response/customer/lastName == 'Smith'

  Scenario: Get account details from ParaBank
    Given path 'parabank/services/bank/accounts/12345'
    When method get
    Then status 200
    And match response/account/id == '12345'
    And match response/account/customerId == '12212'
    And match response/account/type == 'CHECKING'
    And match response/account/balance == '-2300.00'

@apiExercise   
Scenario: Request loan
  * def loanRequest = read('classpath:testdata/api/reusable/request-loan.json')
  Given path "requwestLoan"
  And header CustomerId =  loanRewquest.customerId
  And param amount = loanRequest.downPayment
  And param downPayment = loanRequest.downPayment
  And param fromAccountId = loanRequest.fromAccountId
  When method post
  Then status 200
  * print "Request loan response:", response
  And match response.approved == "#boolean"
  And match response.approved == true


