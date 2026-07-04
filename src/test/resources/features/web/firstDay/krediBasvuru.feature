Feature: Kredi Başvurusu Yapma
Bir müşteri olarak, ihtiyaç duyduğum kredi tutarı için onlien başvuru yapmak istiyorum.

  Background:
    Given sisteme giriş yapılır
    And Krediler menüsüne geçilir

  Scenario: Kredi başvurusu onay
    Given müşterinin aktif hesabı olduğu doğrulanır
    When müşteri kredi tutarını 20000 TL girmeli
    And peşinat tutarını 5000 TL girmeli
    And ödeme yapılacak hesabı seçmeli
    And kredi başvurusu yapılmalı
    Then Kredi başvurusu onaylanmalı
    And müşteriye başvuru sonucunun başarılı olduğu bilgisi verilmeli

  Scenario: Kredi başvurusu red
    Given müşterinin aktif hesabı olduğu doğrulanır
    When müşteri kredi tutarını 2000000 TL girmeli
    And peşinat tutarını 5000 TL girmeli
    And ödeme yapılacak hesabı seçmeli
    And kredi başvurusu yapılmalı
    Then kredi başvurusu onaylanmamalı
    And müşteriye başvuru sonucunun olumsuz olduğu bilgisi verilmeli

  Scenario Outline: Kredi başvurusu - geçersiz tutarlı senaryolar
    Given müşterinin aktif hesabı olduğu doğrulanır
    When müşteri "<kredi_tutari>" girmeli
    And peşinat tutarını "<pesinat_tutari>"girmeli
    And ödeme yapılacak hesabı seçmeli
    And kredi başvurusu butonuna tıklanmalı
    Then Kredi başvurusu gerçekleşmemeli
    And müşteriye "<hata_mesaji>" verilmeli

    Examples:
      | kredi_tutari | pesinat_tutari | hata_mesaji                 |
      |            0 |              0 | tutar sıfırdan büyük olmalı |
      |         -100 |           -100 | tutar negatif olamaz        |
