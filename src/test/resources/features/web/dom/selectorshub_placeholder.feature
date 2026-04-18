@web @smoke @advanced-dom
Feature: SelectorsHub advanced DOM lab
  Advanced examples for nested shadow DOM and shadow/iframe edge flow.

  Scenario: Nested shadow DOM input interaction
    Given I open SelectorsHub advanced DOM page
    When I enter "trainer-open-shadow" inside open shadow root input
    And I enter "trainer-nested-shadow" inside nested shadow root input
    Then open shadow root input should keep value "trainer-open-shadow"

  Scenario: Combined shadow and iframe context edge case
    Given I open SelectorsHub advanced DOM page
    When I enter "edge-case-user" inside open shadow root input
    And I switch into an iframe and back to default content
    Then open shadow root input should keep value "edge-case-user"
