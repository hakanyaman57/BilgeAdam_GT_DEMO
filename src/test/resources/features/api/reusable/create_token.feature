Feature: Reusable auth token provider

  Scenario: Create auth token
    Given url apiBaseUrl
    And path 'auth'
    * print 'Base URL: ', apiBaseUrl 
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    * def tokenPayload =
    """
    {
      "username": "#(bookerUsername)",
      "password": "#(bookerPassword)"
    }
    """
    * print 'Request payload for token creation:', tokenPayload
    And request tokenPayload
    When method post
    Then status 200
    And match response.token == '#string'
    * def token = response.token
    * print 'Generated token:', token
