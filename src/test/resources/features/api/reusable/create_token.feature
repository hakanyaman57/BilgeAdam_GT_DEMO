Feature: Reusable auth token provider

  Scenario: Create auth token
    Given url apiBaseUrl
    And path 'auth'
    And header Accept = 'application/json'
    And header Content-Type = 'application/json'
    And request
    """
    {
      "username": "#(bookerUsername)",
      "password": "#(bookerPassword)"
    }
    """
    When method post
    Then status 200
    And match response.token == '#string'
    * def token = response.token
