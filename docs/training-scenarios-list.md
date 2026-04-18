# Training Scenarios List

## Web Runnable (Cucumber + TestNG)
- ParaBank business flow:
  - Invalid login shows business error
  - Registration form fill keeps entered values
- The Internet technical lab:
  - Dropdown selection
  - iframe editor interaction
  - Explicit wait with dynamic loading
  - Introductory shadow DOM read
- SelectorsHub advanced DOM:
  - Nested shadow DOM interaction
  - Shadow + iframe context edge case

## Mobile Runnable Structure (Cucumber + TestNG)
- TheApp native flow:
  - Login with accessibility id locators
  - List interaction fallback (list validation)
- Android ApiDemos hybrid flow:
  - Open WebView screen from native menu
  - Switch `NATIVE_APP` to `WEBVIEW`
  - Interact with a web element in WEBVIEW

## API Runnable (Karate + JUnit 5)
- Health check: `GET /ping`
- Booking create: `POST /booking`
- Booking read: `GET /booking/{id}` after create
- Reusable token flow:
  - `call read('classpath:features/api/reusable/create_token.feature')`
  - Authenticated cleanup example with `DELETE /booking/{id}`

## Training-Only BDD Examples
- `features/training/bdd/scenario-quality-comparison.feature`
  - Poorly written scenario
  - Improved scenario
  - Non-outline duplication
  - Scenario Outline recommended version
- `features/training/bdd/background-and-rule-example.feature`
  - Background usage
  - Rule usage

## Training-Only Anti-pattern Examples (DO NOT USE)
- `features/training/anti-patterns/do-not-use-examples.feature`
  - wrong wait
  - flaky expectation
  - bad locator
  - test data dependency
  - missing context switch
  - platform-specific locator issue
