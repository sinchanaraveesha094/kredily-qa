# Kredily HRMS - Intern QA Assignment

Manual + automated QA of the Kredily HRMS Android app.

> **Selenium vs Appium:** Selenium WebDriver drives browsers; native Android apps are driven through **Appium**, whose Java client is built directly on
> Selenium's `WebDriver` API. This repo therefore uses *Java + Selenium WebDriver API via Appium (UiAutomator2)* + TestNG.

## Repository layout
```
docs/TEST_CASES.md       20 test cases (positive / negative / edge) + result columns
docs/BUG_REPORTS.md      bug template, severity guide, areas to probe
docs/AI_USAGE.md         prompt, AI output, what was changed/validated
docs/QA_SUMMARY.md       final QA summary
docs/evidence/           screenshots and screen recordings (create this folder)
api/                     Postman collection + capture guide
src/test/java/...        Page Objects, base classes, 5 automated journeys
src/test/resources/      config.properties, locators.properties
reports/                 extent-report.html + failure screenshots (generated)
testng.xml               suite definition
```

## Prerequisites
- JDK 17+, Maven 3.9+
- Android SDK (platform-tools, an emulator image or a real device with USB debugging)
- Node.js 18+ and Appium 2: `npm i -g appium` then `appium driver install uiautomator2`
- APK downloaded to `apps/kredily-mobile-v2.apk`:
  `https://download.aiagent.kredily.com/static/kredily-mobile-v2.apk`

## One-time setup
1. Start an emulator / connect a device: `adb devices` must list it.
2. Get the package and launch activity: `aapt dump badging apps/kredily-mobile-v2.apk | grep -E "package:|launchable"` and put them in `config.properties` (optional).
3. Open **Appium Inspector**, connect, and replace every `REPLACE_ME` and guessed locator in `src/test/resources/locators.properties`.
   (Locators are isolated there so no Java change is needed.)

## Run
```bash
appium                          # terminal 1
mvn clean test                  # terminal 2
mvn clean test -Ddevice.name=Pixel_6 -Dvalid.password='Pass@9865'   # config overrides
```
Report: open `reports/extent-report.html`. Failure screenshots land in `reports/screenshots/`.

## Automated journeys
| # | Journey | Class |
|---|---------|-------|
| 1 | Valid login | `LoginTest` |
| 2 | Invalid login (9 data-driven scenarios) | `InvalidLoginTest` |
| 3 | Dashboard validation + rotation | `DashboardTest` |
| 4 | Attendance check-in + state exclusivity | `AttendanceTest` |
| 5 | Leave balance + form validation | `LeaveTest` |

## Design notes
- Page Object Model; explicit waits only (no `Thread.sleep`).
- Fresh app session per test -> tests are independent and order-agnostic.
- Check-in test handles both "already checked in" and "not yet" starting states.
- Credentials are in `config.properties` for this assignment only (test account); never commit real credentials.

## Status
Code was written without access to the live APK, so locators/assertion text are best-guess placeholders until updated per step 3.
