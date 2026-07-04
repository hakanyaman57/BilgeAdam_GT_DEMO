Feature: BDD Scenarios
Bir müşteri olarak, kendi hesaplarım arasında para transferi yapmak istiyorum.

  Scenario: Para Transferi Başarılı Senaryo
    Given Müşteri username:john ve password:demo ile login olmalıdır.
    And Anasayfadan transfer funds sayfasına tıklamalıdır.
    And Kaynak hesap ve hedef hesap seçilmelidir
    And Kaynak hesapta 1500 TL bakiye vardır
    When 1000 TL Tutar girişi yapmalıp Transfer butonuna tıklamalı
    Then Hedef hesap bakiyesi 1000 TL artmalı
    And kaynak hesap bakiyesi 1000 TL azalmalı

  Scenario: Para Transferi Başarısız Senaryo
    Given Müşteri username:john ve password:demo ile login olmalıdır.
    And Anasayfadan transfer funds sayfasına tıklamalıdır.
    And Kaynak hesap ve hedef hesap seçilmelidir
    And Kaynak hesapta 1000 TL bakiye vardır
    When 0 TL veya negatif Tutar girişi yapmalıp Transfer butonuna tıklamalı
    Then Transfer yapılamaz
    And Hata mesajı verilmeli

  Scenario: Para Transferi Başarısız Senaryo
    Given Müşteri username:john ve password:demo ile login olmalıdır.
    And Anasayfadan transfer funds sayfasına tıklamalıdır.
    When Kaynak hesap ve hedef hesap aynı hesaplar seçilmelidir
    Then Transfer yapılamaz
    And Hata mesajı verilmeli

  Scenario Outline: Hesaplarım arası para transferi -geçersiz tutarlı senaryolar
    Given müşterinin kaynak hesabında 1000 TL vardır
    And müşterinin hedef hesabı aktiftir.
    When müşteri kaynak hesaptan hedef hesaba "<tutar>" TL göndermek ister
    Then transfer işlemi gerçekleşmez
    And ekranda "<hata_mesajı>" görünür

    Examples:
      | tutar | hata_mesajı                 |
      |     0 | tutar sıfırdan büyük olmalı |
      |  -100 | tutar negatif olamaz        |

 