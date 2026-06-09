Feature: Feauture başlığı -Ödeme Sistemleri
   Kredi Kartı ve Debit Kart Ödemlerine ait Test Senaryoları

  Background: Kullanıcı ödeme sayfasına erişir
    Given Kullanıcı sisteme giriş yapar
    And Kullanıcı ödeme sayfasını açar

   Scenario: Kredi Kartı ile ödeme yapıldığında başarılı mesajı alınmalı
    When Kullanıcı geçerli kredi kartı bilgilerini girer ve ödemeyi onaylar
    And Kullanıcı ödeme işlemini tamamlar
    Then Kullanıcı başarılı ödeme mesajını görmelidir
    And Kullanıcının hesabından ödeme tutarı düşülmelidir
     
  Scenario: Debit Kart ile ödeme yapıldığında başarılı mesajı alınmalı
    When Kullanıcı geçerli debit kart bilgilerini girer ve ödemeyi onaylar
    And Kullanıcı ödeme işlemini tamamlar
    Then Kullanıcı başarılı ödeme mesajını görmelidir
    And Kullanıcının hesabından ödeme tutarı düşülmelidir
     