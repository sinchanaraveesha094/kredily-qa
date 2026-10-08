# AI-Assisted QA

**Tool:** Claude (Anthropic)  **Activity:** Generating the Java/Appium automation framework scaffold and the creative test-case set.

## Prompt used
> I am applying for a QA Engineer intern role. Assignment: test the Kredily HRMS Android APK - 20 test cases (positive/negative/edge),
> 5 bugs, 5 automated mobile journeys, 3 API tests with a Postman collection, and AI-assisted QA documentation.
> Use Java with Selenium for automation, provide it in the correct submission form, and make the test cases creative.

## AI-generated output (summary)
- Maven project: Appium Java client 9.x (Selenium WebDriver for native apps), TestNG, ExtentReports.
- Page Object Model (`LoginPage`, `DashboardPage`, `AttendancePage`, `LeavePage`), locators externalised to `locators.properties`.
- 5 journeys: valid login, data-driven invalid login (9 hostile inputs), dashboard validation, attendance check-in, leave workflow.
- 20 test cases in `docs/TEST_CASES.md`, Postman collection skeleton, documentation templates.

## What I changed / validated  _(complete this honestly before submitting)_
- [ ] Installed the APK and replaced every `REPLACE_ME` / guessed locator using Appium Inspector.
- [ ] Set `app.package` / `app.activity` from `aapt dump badging`.
- [ ] Ran the suite; fixed any failing assertion caused by wrong assumptions (e.g. different button text, extra onboarding screens).
- [ ] Verified AI-proposed expectations against actual app behaviour; removed or rewrote any test that asserted behaviour the app does not have.
- [ ] Captured real API endpoints with a proxy and rewrote the Postman requests.
- [ ] Cut or edited test cases that did not fit the real app.

## Reflection
AI saved time on boilerplate and on brainstorming edge cases, but it could not see the app: locators, button labels and API paths were assumptions
that had to be verified manually. _Add 2-3 concrete examples of what you corrected._
