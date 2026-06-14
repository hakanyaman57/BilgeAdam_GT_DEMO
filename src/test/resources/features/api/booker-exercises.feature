
Feature: Booker API Exercises

Background:
  * url apiBaseUrl
  * configure headers = { Content-Type: "application/json" }
  * def username = bookerUsername
  * def password = bookerPassword

@createToken
Scenario: Create token for booking
  Given path 'auth'
  And request {username: "#(username)",password: "#(password)" }
  When method post
  Then status 200
  * def token = response.token
  * print "Token response:", response

@bookerRun
Scenario: Get bookings and then delete a random booking it using the token
* def tokenResult = call read('this:booker-exercises.feature@createToken')
* def token = tokenResult.token
Given path 'booking'
When method get
Then status 200
* print "Booking list response:", response
* def randomBookingId = Math.floor(Math.random() * response.length) + 1
* print "Random booking id: " + randomBookingId

Given path 'booking', randomBookingId
And header Cookie = 'token=' + token
When method delete 
Then status 201

Given path 'booking'
When method get
Then status 200
* print "Booking list after deletion response:", response
And match response !contains { bookingid: randomBookingId }


















  # Örnek - Inline JSON body ile POST isteği
# Kullanılacak endpoint: POST /booking
# ParaBank POST endpointleri JSON body değil param beklediği için bu örnek Restful-Booker kullanır.

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

# Örnek - Dosyadan okunan JSON body ile POST isteği
# Kullanılacak endpoint: POST /booking
# Payload src/test/resources/testdata/api/booking_payload.json dosyasında tutulur.
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
