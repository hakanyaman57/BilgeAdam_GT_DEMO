@training
Feature: Excersize 

Background:
  * url "https://parabank.parasoft.com/parabank/services/bank"
  * configure headers = { Accept: "application/json" }
  * def demoUsername = "john"
  * def demoPassword = "demo"

Scenario: Transaction list for account
  Given path "accounts", 13344, "transactions"
  When method get
  Then status 200
