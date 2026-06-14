  @training
Feature: API training exercise

  # Görev 2 - Temel response doğrulamaları ekle
  # Response değerinin bir liste olduğunu doğrula.
  # Listenin boş olmadığını doğrula.
  # İlk transaction kaydını bir değişkene ata.
  # Bu kayıttaki temel alanları kontrol et: id, accountId, type, amount.


 



Background:
  * url "https://parabank.parasoft.com/parabank/services/bank"
  * configure headers = { Accept: "application/json" }
  * def demoUsername = "john"
  * def demoPassword = "demo"

  # Görev 1 - Mevcut isteği çalıştır ve incele
  # Kullanılacak endpoint: GET /accounts/13344/transactions
  # Hazır transactions endpointini kullan.
  # Status code değerinin 200 olduğunu doğrula.
  # Response bilgisini console ve Karate report içinde görünecek şekilde print et.
Scenario: Transaction list for account
  Given path "accounts", 13344, "transactions"
  When method get
  Then status 200
  * print "Transactions response:", response


  # Görev: Login ve customerId al
  # Kullanılacak endpoint: GET /login/{username}/{password}
  # Örnek path: /login/john/demo
  # demoUsername ve demoPassword kullanarak login endpointine istek at.
  # Login response bilgisini print et.
  # Sonraki requestte kullanmak için response.id değerini customerId olarak kaydet.

  # Görev 4 - Customer accounts ile requestleri birbirine bağla
  # Kullanılacak endpoint: GET /customers/{customerId}/accounts
  # Accounts response bilgisini print et.
  # Sonrasında kullanılacak endpoint: GET /accounts/{accountId}/transactions
  # customerId kullanarak müşterinin hesap listesini çek.
  # Accounts response bilgisini print et.
  # Her account kaydında id, customerId, type ve balance alanlarının doğru veri tipiyle geldiğini doğrula.
  # İlk account id değerini kaydet ve bu account için transactions requesti at.

    # Görev 5 - POST akışı ekle ve Scenario Outline ile iyileştir
  # Kullanılacak endpoint: POST /createAccount
  # Parametreler: customerId, newAccountType, fromAccountId
  # Örnek: /createAccount?customerId=12212&newAccountType=1&fromAccountId=12345
  # customerId, account type ve fromAccountId parametrelerini kullanarak yeni bir hesap oluştur.
  # Oluşturulan account response alanlarını doğrula.
  # Sonra account oluşturma akışını CHECKING ve SAVINGS için Scenario Outline haline getir.
  @singleRun
Scenario: Login and get customer accounts
  Given  path "login", demoUsername, demoPassword
  When method get
  Then status 200
  * print "Login response:", response
  * def musteriNo = response.id
  * print "Customer ID:", musteriNo
  And match response.id == "#number"
  And match response.firstName == "#string"
  And match response.lastName == "Smith"

  Given path "customers", musteriNo, "accounts"
  When method get
  Then status 200
  * print "Customer accounts response:", response
  And match response == "#[]"
  * def accountId = response[0].id
  And match response[0].type == "CHECKING"

  Given path "accounts", accountId, "transactions"
  When method get
  Then status 200
  * print  " Transactions response for account " + accountId + ":", response
  And match response == "#[]"

  Given path "createAccount" 
  And param  customerId = musteriNo 
  And param newAccountType = 1
  And param fromAccountId = accountId
  When method post
  Then status 200
  * print "Create account response:", response

  # curl -H "Accept: application/json" \
  #   https://parabank.parasoft.com/parabank/services/bank/login/john/demo



