# Target Selection Summary (Locked for Training)

This repository is built for a **3-day BA/TA training** and uses stable, public demo targets.

## Locked Target Set (Source of Truth)

### Web

- **ParaBank** (BUSINESS flow only)  
  `https://parabank.parasoft.com/parabank`
- **The Internet** (TECHNICAL web lab)  
  `https://the-internet.herokuapp.com/`  
  Used for: dropdown, iframe, waits/dynamic loading, introductory shadow DOM.
- **SelectorsHub Practice Page** (ADVANCED DOM lab)  
  `https://selectorshub.com/xpath-practice-page/`  
  Used for: nested shadow DOM and combined shadow/iframe edge cases.

### Mobile

- **TheApp (Android + iOS)** (NATIVE training app set)  
  - Source: `https://github.com/appium-pro/TheApp`
- **Android ApiDemos** (HYBRID/context switching teaching target only)  
  `https://github.com/appium/android-apidemos`

> Approved fallback: if “search” is not reliably available in My Demo App, we officially use a **list + filter/sort** flow instead.

### API

- **Restful-Booker** (single Karate target)  
  `https://restful-booker.herokuapp.com/`

## Why these targets

- **Trainer-friendly:** small flows that are easy to explain live.
- **Separation of concerns:** ParaBank for business realism; The Internet/SelectorsHub for technical DOM topics without mixing business logic.
- **Open and accessible:** public demo targets and open-source mobile apps.
- **Realistic failure/debugging:** waits, iframes, and shadow DOM are common real-world problems.

## Risks / Gaps / Limitations

- A single banking site does not reliably cover shadow DOM + nested shadow DOM + iframe topics → we intentionally use technical lab sites for those areas.
- SelectorsHub is content-rich and can change → we keep it as an **advanced lab** and keep locators resilient.
- Restful-Booker is a public demo service and may reset data → API scenarios must be short-lived and self-contained.
- Mobile “search” may vary by app version → the approved fallback is **list + filter/sort**.

## Coverage Mapping Table (Phase 1 intent preserved)

| Area | Required topic | Target(s) | Notes |
|---|---|---|---|
| Web | Login | ParaBank | Business login flow only. |
| Web | Form filling | ParaBank | Registration / bill pay-style forms (Phase 3). |
| Web | Dropdown | The Internet (primary); ParaBank (optional) | Teach mechanics in lab; show realism later in business flow. |
| Web | iframe | The Internet (primary) | Clean iframe example for training. |
| Web | Wait/synchronization | The Internet (primary) | Dynamic loading demos for stable wait teaching. |
| Web | Shadow DOM (intro) | The Internet (primary) | Intro/first exposure. |
| Web | Nested Shadow DOM | SelectorsHub (primary) | Advanced DOM lab only. |
| Web | Shadow + iframe edge cases | SelectorsHub (primary) | Advanced lab only. |
| Mobile | Android + iOS coverage | TheApp (Android + iOS) | Primary native demo app set. |
| Mobile | Login | TheApp | Login flow via accessibility-id-first locator strategy. |
| Mobile | List/search | TheApp | Approved fallback in this repo is list interaction/validation. |
| Mobile | Accessibility id example | TheApp | Core mobile locators are accessibility-id-first. |
| Mobile | Android/iOS locator separation | TheApp | Same flow, platform-specific locators in Screen Objects. |
| Mobile | Android hybrid + context switching | Android ApiDemos | Strictly for `NATIVE_APP` ↔ `WEBVIEW` teaching. |
| API | GET | Restful-Booker | Core runnable example uses `/ping` and booking read flow. |
| API | POST | Restful-Booker | Token + create booking in Phase 3. |
| API | Assertions | Restful-Booker | Response assertions in Karate. |
| API | Auth/token reusable flow | Restful-Booker | Reusable auth setup in Karate config (Phase 3). |
| API | Config/environment structure | Restful-Booker | `karate-config.js` + properties for base URL. |
| API | Optional UI/API linkage | ParaBank (illustrative only) | Documented concept only; Karate target remains Restful-Booker. |
