
@parabankRun
Feature: ParaBank REST API Karate examples

  Background:
    * url parabankBaseUrl
    * configure headers = { Accept: 'application/json', 'Content-Type': 'application/json' }
    * def demoUsername = 'john'
    * def demoPassword = 'demo'
    * def accountTypes = { checking: 0, savings: 1, loan: 2 }
    * def setup = callonce read('classpath:features/api/parabank/setup.feature')
    * def customerId = setup.customerId
    * def fromAccountId = setup.fromAccountId

  # Kullanilacak Endpoint: GET /login/{username}/{password}
  # Ornek path: /login/john/demo
  # Senaryo aciklama: Gecerli kullanici bilgileri ile login istegi atilir ve musteri bilgileri dogrulanir.
  Scenario: Login with valid customer credentials
    Given path 'login', demoUsername, demoPassword
    When method get
    Then status 200
    * print response
    And match response.id == '#number'
    And match response.firstName == '#string'
    And match response.lastName == '#string'

  # Kullanilacak Endpoint: POST /createAccount
  # Ornek path: /createAccount?customerId=12212&newAccountType=1&fromAccountId=12345
  # Senaryo aciklama: Mevcut musterinin ilk hesabini kaynak hesap olarak kullanip yeni bir SAVINGS hesabi acar.
  Scenario: Open new savings account
    Given path 'createAccount'
    And param customerId = customerId
    And param newAccountType = accountTypes.savings
    And param fromAccountId = fromAccountId
    When method post
    Then status 200
    * print response
    And match response.id == '#number'
    And match response.customerId == customerId
    And match response.type == 'SAVINGS'

  # Kullanilacak Endpoint: GET /accounts/{accountId}/transactions
  # Ornek path: /accounts/12345/transactions
  # Senaryo aciklama: Secilen hesaba ait transaction listesini getirir ve donen cevabin liste oldugunu kontrol eder.
  Scenario: Find transactions by account id
    Given path 'accounts', fromAccountId, 'transactions'
    When method get
    Then status 200
    * print response
    And match response == '#[]'

  # Kullanilacak Endpoint: POST /requestLoan
  # Ornek path: /requestLoan?customerId=12212&amount=1000&downPayment=100&fromAccountId=12345
  # Senaryo aciklama: JSON dosyasindan okunan kredi talebi verisi ile loan request gonderir ve cevabi dogrular.
  Scenario: Request loan using data read from JSON file
    * def loanRequest = read('classpath:testdata/api/request-loan.json')
    Given path 'requestLoan'
    And param customerId = loanRequest.customerId
    And param amount = loanRequest.amount
    And param downPayment = loanRequest.downPayment
    And param fromAccountId = loanRequest.fromAccountId
    When method post
    Then status 200
    * print response
    And match response.approved == '#boolean'
    And match response.accountId == '#number'
    And match response.loanProviderName == '#string'
