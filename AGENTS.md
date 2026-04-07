# AGENTS.md

## Project Context

This repository is a training-focused demo automation project.

The goal is to create a **small, clear, corporate-style mini framework** that can be used in a 3-day training for business analysts / test analysts.

This is **not** a full enterprise framework and should **not** become overengineered.

## Primary Goal

Build a runnable and understandable Java automation demo project using:

- Java 21
- Maven
- TestNG
- Cucumber
- Selenium
- Appium
- Karate

The repository must help explain:

- Pages / Screens / Steps / Hooks / Utils / Config / Test Data
- BDD structure and feature design
- locator strategy basics
- web / mobile / API separation
- basic CI/CD awareness
- reporting and first-level debugging

## Key Constraints

- Keep the repo small and training-friendly
- Prefer clarity over sophistication
- Do not over-abstract
- Use realistic naming and folder organization
- Avoid silently skipping requested capabilities
- If an exact request cannot be implemented, document the limitation clearly

## Required Project Feel

This project should feel like a **miniature version of a corporate framework**:
- clean
- modular
- understandable
- easy to explain live
- easy to run locally

## Required Modules

The repository should contain:

1. Web automation module
2. Mobile automation module
3. API automation module
4. Documentation for trainers
5. Good and bad example scenarios

## Web Expectations

Web examples should cover:
- login
- form filling
- dropdown
- iframe
- wait example
- shadow DOM
- nested shadow DOM

Prefer a banking-themed demo site for the main business flow.
If one site is not enough, it is acceptable to use multiple validated demo sites.

## Mobile Expectations

Mobile examples should cover:
- Android and iOS
- native flows
- Android hybrid/webview flow if feasible
- login
- list/search
- accessibility id
- context switching
- Android/iOS locator separation

iOS native coverage is sufficient.
iOS hybrid is not mandatory.

## API Expectations

Karate examples should cover:
- GET
- POST
- response assertions
- auth/token reusable flow
- config/environment setup
- reusable structure
- one documented UI/API-related illustrative example

## BDD Expectations

Use Given / When / Then style.

Include:
- good feature examples
- bad feature examples
- Background examples
- Rule examples
- the same scenario in bad / non-outline / outline-improved versions

## Reporting

Use:
- Cucumber reports
- TestNG default reports

Do not add Allure.

## Parallel Execution

Support parallel execution for:
- web
- api

Mobile does not need full parallel support.

## Documentation Requirements

Generate and maintain:
- README.md
- architecture overview
- class purpose guide
- training scenarios list
- bad examples guide
- BDD examples guide
- Git/Bitbucket guide
- known limitations
- trainer demo order
- target selection summary

## Demo Target Selection Rule

Before building implementation details:
- identify suitable demo web targets
- identify suitable demo mobile demo apps
- identify suitable API targets
- validate reachability / viability
- document why they were selected

Do not assume one target covers everything unless verified.

## Quality Standard

Every addition should be evaluated by these questions:

1. Is it understandable for training?
2. Is it small enough to explain live?
3. Does it preserve a corporate-style structure?
4. Is it actually useful for the training goals?
5. Is it better to simplify this instead of abstracting it?

## Avoid

- unnecessary design patterns
- deep abstraction layers
- excessive generic utility classes
- framework bloat
- advanced infrastructure that does not help training

## Preferred Style

- concise
- explicit
- readable
- realistic
- trainer-friendly

## Decision Rule

When choosing between abstraction and explainability, prefer explainability.
Clarity is more important than enterprise-level sophistication.
