# Kredily HRMS (Android) - Test Cases

**Build under test:** `kredily-mobile-v2.apk`  **Tester:** _your name_  **Device/OS:** _fill in_  **Date:** _fill in_
**Result legend:** Pass / Fail / Blocked / N-A.  All results start as `Not Executed` - update the *Actual / Result* columns as you run each case.
Test type: **P** = Positive, **N** = Negative, **E** = Edge.  `Auto` = covered by an automated test.

## Major HRMS workflows identified
Authentication -> Dashboard -> Attendance (check-in/out) -> Leave (balance, apply, history) -> Profile -> Notifications / session handling.

| ID | Module | Type | Scenario | Steps | Expected Result | Pri | Auto | Actual / Result |
|----|--------|------|----------|-------|-----------------|-----|------|-----------------|
| TC01 | Login | P | Valid login | Launch app > enter valid email + password > tap Login | Dashboard opens; no error | High | `LoginTest` | Not Executed |
| TC02 | Login | N | Wrong password | Valid email + wrong password > Login | Clear error; stays on login; password field not echoed in error | High | `InvalidLoginTest` | Not Executed |
| TC03 | Login | N | Unregistered email | Random yopmail address + any password | Error shown; message must not reveal whether the email exists (account-enumeration check) | High | `InvalidLoginTest` | Not Executed |
| TC04 | Login | N | Empty fields | Leave email and/or password blank > Login | Field-level "required" validation; no network call crash | High | `InvalidLoginTest` | Not Executed |
| TC05 | Login | E | Email with leading/trailing spaces and UPPERCASE | Enter `  PEOPLEKREDILY1@YOPMAIL.COM  ` + valid password | Either trimmed + case-insensitively accepted, or a clear message - never a crash/blank error | Med | - | Not Executed |
| TC06 | Login | N | Injection / hostile input | Email = `' OR '1'='1' --`, then `<script>alert(1)</script>` | Rejected as invalid; no stack trace, no login bypass | High | `InvalidLoginTest` | Not Executed |
| TC07 | Login | E | 300-char email and emoji password | Paste 300 chars into email; password with emoji | Input limited or rejected gracefully; no ANR/freeze | Low | `InvalidLoginTest` | Not Executed |
| TC08 | Login | E | Password masking and show/hide | Type password > toggle visibility (if present) > background app and return | Masked by default; hidden again after returning; not visible in recent-apps thumbnail | Med | - | Not Executed |
| TC09 | Login | E | Double-tap Login rapidly | Valid creds > tap Login 5x quickly | One request only; no duplicate session, no crash | Med | - | Not Executed |
| TC10 | Network | N | Login in airplane mode | Enable airplane mode > attempt login | Friendly "no internet" message; button re-enabled; no infinite spinner | High | - | Not Executed |
| TC11 | Network | E | Connection drops mid-login | Tap Login > toggle airplane mode within 1 s > restore | App recovers; retry works; no half-logged-in state | Med | - | Not Executed |
| TC12 | Session | E | Kill app and relaunch | Login > swipe app away > relaunch | Session persists (or sensibly asks to re-login); no blank screen | High | - | Not Executed |
| TC13 | Session | E | Back button after login | Login > press device Back repeatedly | App exits or asks to confirm - must not return to login screen with an active session | Med | - | Not Executed |
| TC14 | Dashboard | P | Dashboard content | Login and inspect dashboard | Greeting, user context, nav to Attendance / Leave / Profile all visible and tappable | High | `DashboardTest` | Not Executed |
| TC15 | Dashboard | E | Rotation, dark mode, 200% font scale | Rotate device; enable dark theme; set largest font in Android settings | Layout stays usable; no clipped/overlapping text or buttons | Med | `DashboardTest` (rotation) | Not Executed |
| TC16 | Attendance | P | Check-in | Open Attendance > tap Check In (allow location) | Success feedback; status changes; Check Out becomes available | High | `AttendanceTest` | Not Executed |
| TC17 | Attendance | N | Check-in with location off / permission denied | Disable GPS or deny permission > tap Check In | Clear message prompting to enable location; no silent failure; no check-in recorded | High | - | Not Executed |
| TC18 | Attendance | E | Device clock tampering and mock location | Change phone date/time manually; or use a mock-location app > check in | Server time is authoritative (or tampering rejected); punch time not shifted by the device clock | High | - | Not Executed |
| TC19 | Leave | P/N | Leave balance + apply with empty form | Open Leave > verify balance > Apply > Submit with nothing filled | Balance shown per leave type; validation messages on mandatory fields | High | `LeaveTest` | Not Executed |
| TC20 | Leave | E | Invalid date logic | Apply leave with End date before Start date; overlapping an existing leave; on a weekend/holiday; exceeding balance | Each is blocked or warned with a specific message; no duplicate/negative-balance request created | High | - | Not Executed |

## Coverage mix
Positive: TC01, TC14, TC16, TC19 | Negative: TC02-TC04, TC06, TC10, TC17 | Edge: TC05, TC07-TC09, TC11-TC13, TC15, TC18, TC20
