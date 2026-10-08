# Bug Reports

> Only log a bug after reproducing it **at least twice** on the build under test. Attach a screenshot or screen recording
> (`adb shell screenrecord /sdcard/bug.mp4` then `adb pull /sdcard/bug.mp4 docs/evidence/`). Do not copy the placeholder
> items below as findings - they are *areas that tend to produce defects* in HRMS apps, to guide your exploration.

## Severity guide
- **Critical** - crash, data loss, security hole, blocks login or core flow
- **High** - core feature broken, no workaround
- **Medium** - feature works with workaround, wrong validation/messages
- **Low** - cosmetic, typo, alignment

## Where to probe first (from the test cases)
1. Error messages on failed login (TC02/TC03) - do they leak whether an email exists?
2. Check-in with GPS off / mock location / changed device clock (TC17/TC18).
3. Leave date validation: end < start, overlap, holiday, balance exceeded (TC20).
4. Session after force-kill, Back button after login (TC12/TC13).
5. Layout under dark mode, landscape, large fonts (TC15).
6. Double-tap on Login / Check-In creating duplicate actions (TC09).

---

## Bug template (copy one block per bug)

### BUG-001: _short, specific title_
| Field | Value |
|-------|-------|
| Module | _e.g. Leave_ |
| Severity / Priority | _Medium / P2_ |
| Build / Device / OS | `kredily-mobile-v2.apk` / _Pixel 6 emulator_ / _Android 14_ |
| Related test case | _TC20_ |

**Preconditions:** _logged in as peoplekredily1@yopmail.com_

**Steps to reproduce**
1. _step_
2. _step_
3. _step_

**Expected result:** _what should happen_

**Actual result:** _what happened_

**Evidence:** `docs/evidence/BUG-001.png` / `docs/evidence/BUG-001.mp4`

**Frequency:** _Always / 3 of 3 attempts_

**Notes:** _logcat excerpt, workaround, suspected cause_

---
(Repeat for BUG-002 ... BUG-005 minimum.)
