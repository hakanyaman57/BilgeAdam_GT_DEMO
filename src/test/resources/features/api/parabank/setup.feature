
Feature: Shared ParaBank test setup

  Background:
    * url parabankBaseUrl
    * configure headers = { Accept: 'application/json', 'Content-Type': 'application/json' }

  Scenario: Get demo customer and first account
    Given path 'login', 'john', 'demo'
    When method get
    Then status 200
    * def customerId = response.id
    * print response

    Given path 'customers', customerId, 'accounts'
    When method get
    Then status 200
    * def fromAccountId = response[0].id
    * print response