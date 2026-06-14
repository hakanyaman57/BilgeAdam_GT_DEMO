@training
Feature: API training exercise


  # Task 1 - Run and inspect the existing request
  # Use the prepared transactions endpoint.
  # Verify status 200 and print the response so it is visible in the console and Karate report.

  # Task 2 - Add basic response assertions
  # Verify that the response is a list.
  # Verify that the list is not empty.
  # Store the first transaction in a variable and check its basic fields: id, accountId, type, amount.

  # Task 3 - Replace fixed data with login data
  # Call the login endpoint with demoUsername and demoPassword.
  # Print the login response.
  # Store response.id as customerId for the next request.

  # Task 4 - Chain requests with customer accounts
  # Use customerId to get the customer accounts list.
  # Print the accounts response.
  # Verify each account contains id, customerId, type, and balance with the correct data types.
  # Store the first account id and use it to request that account transactions.

  # Task 5 - Add a POST flow and improve it with Scenario Outline
  # Create a new account using customerId, account type, and fromAccountId parameters.
  # Verify the created account response fields.
  # Then convert the account creation flow to Scenario Outline for CHECKING and SAVINGS.

Background:
  * url "https://parabank.parasoft.com/parabank/services/bank"
  * configure headers = { Accept: "application/json" }
  * def demoUsername = "john"
  * def demoPassword = "demo"

Scenario: Transaction list for account
  Given path "accounts", 13344, "transactions"
  When method get
  Then status 200
  * print "Transactions response:", response


# Example - POST with an inline JSON body
# This uses Restful-Booker because ParaBank POST endpoints expect params, not JSON body.
Scenario: Create booking with inline request body
  * url apiBaseUrl
  * configure headers = { Accept: "application/json", "Content-Type": "application/json" }
  * def bookingPayload =
  """
  {
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
      "checkin": "2026-01-01",
      "checkout": "2026-01-05"
    },
    "additionalneeds": "Breakfast"
  }
  """
  Given path "booking"
  And request bookingPayload
  When method post
  Then status 200
  * print "Create booking response:", response
  And match response.bookingid == "#number"
  And match response.booking.firstname == bookingPayload.firstname
  And match response.booking.lastname == bookingPayload.lastname

# Example - POST with a JSON body loaded from a file
# The payload is stored under src/test/resources/testdata/api/booking_payload.json.
Scenario: Create booking with request body from json file
  * url apiBaseUrl
  * configure headers = { Accept: "application/json", "Content-Type": "application/json" }
  * def bookingPayload = read("classpath:testdata/api/booking_payload.json")
  Given path "booking"
  And request bookingPayload
  When method post
  Then status 200
  * print "Create booking from file response:", response
  And match response.bookingid == "#number"
  And match response.booking.firstname == bookingPayload.firstname
  And match response.booking.lastname == bookingPayload.lastname
