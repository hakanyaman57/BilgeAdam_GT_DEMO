Feature: Other Karate Exercises ids with query parameters
  
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


Scenario: Get booking ids with query parameters
  Given path 'booking'
  And param firstname = 'Jim'
  And param lastname = 'Brown'
  When method get
  Then status 200
  * print 'Filtered booking ids:', response


Scenario: Verify response header
  Given path 'ping'
  When method get
  Then status 201
  And match header Content-Type contains 'text/plain'