## Execution Model Overview

The repository is intentionally organized to keep each layer simple and trainer-friendly.

### 1. Web and Mobile
Web and mobile test flows use **Cucumber + TestNG**.

This combination is used to support:
- feature-file-driven execution
- step definition structure
- hooks and reusable test flow components
- business-readable BDD examples for training

This makes the UI-oriented layers easier to explain during training, especially when discussing:
- Features
- Scenarios
- Steps
- Hooks
- runners
- tag-based execution

### 2. API
API automation uses **Karate feature files** triggered by a **JUnit 5 Karate runner**.

This keeps API examples lightweight while preserving readable execution.

### Why this approach exists
This project is a training repository, not a production monolith.

Using:
- **Cucumber + TestNG** for web/mobile
- **Karate + JUnit 5** for API

keeps the execution model easy to explain live.

### Practical interpretation
- Use **web/mobile runners** when demonstrating UI-oriented BDD automation
- Use the **API Karate runner** when demonstrating API automation
- Treat this as one repository with clear layer separation chosen for training readability
