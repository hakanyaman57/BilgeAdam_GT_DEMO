Feature: Restful-Booker API smoke
  API examples are intentionally compact for training readability.

  Background:
    * url apiBaseUrl
    * configure headers = { Accept: 'application/json', 'Content-Type': 'application/json' }
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

  Scenario: Health check ping
    Given path 'ping'
    When method get
    Then status 201

  Scenario: Create booking with POST
    Given path 'booking'
    And request bookingPayload
    When method post
    Then status 200
    And match response.bookingid == '#number'
    And match response.booking.firstname == bookingPayload.firstname
    And match response.booking.lastname == bookingPayload.lastname

  Scenario: Read booking with GET after create
    Given path 'booking'
    And request bookingPayload
    When method post
    Then status 200
    * def createdBookingId = response.bookingid
    Given path 'booking', createdBookingId
    When method get
    Then status 200
    And match response.firstname == bookingPayload.firstname
    And match response.lastname == bookingPayload.lastname

  Scenario: Reusable auth token flow for cleanup
    * def tokenResult = call read('classpath:features/api/reusable/create_token.feature')
    * def token = tokenResult.token
    Given path 'booking'
    And request bookingPayload
    When method post
    Then status 200
    * def createdBookingId = response.bookingid
    Given path 'booking', createdBookingId
    And header Cookie = 'token=' + token
    When method delete
    Then status 201
