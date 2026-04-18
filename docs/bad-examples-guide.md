# Bad Examples Guide

## Purpose
These examples are intentionally wrong and are used only for refactoring/debugging workshops.

## Feature file
- `src/test/resources/features/training/anti-patterns/do-not-use-examples.feature`

## Included anti-patterns
- Wrong wait strategy (`Thread.sleep` mindset)
- Flaky expectation with random behavior
- Bad locator strategy (deep absolute XPath mindset)
- Test data dependency between tests
- Missing context switch in hybrid automation
- Android/iOS locator separation not handled

## Trainer usage
- Run through anti-pattern intent first.
- Ask trainees to identify why each is brittle.
- Refactor into project-standard patterns (page/screen objects + explicit waits + stable data/setup).
