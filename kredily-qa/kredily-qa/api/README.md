# API Testing

The Kredily mobile API is not publicly documented, so the collection is a **ready-to-edit skeleton** with assertions already written.

## Capture the real endpoints
1. Run the APK on an emulator; set the emulator Wi-Fi proxy to your PC (Postman Proxy / Charles / mitmproxy / HTTP Toolkit).
2. Install the proxy CA certificate (user certs). If the app uses certificate pinning, capture on a rooted/debug emulator or note the limitation in the QA summary.
3. Perform: login, open dashboard, open leave balance. Copy the host and paths of the 3 requests.
4. In Postman: import `Kredily_API.postman_collection.json`, set `baseUrl`, replace each `REPLACE_ME` path and body field names, run the collection.

## Covered APIs (3) x scenarios
| API | Positive | Negative |
|-----|----------|----------|
| Login | valid creds -> 200 + token saved | wrong password, missing field, SQL-injection input |
| Profile | with token -> 200 | no token, tampered token (401/403) |
| Leave | balance with token -> 200 | no token, end-date before start-date |

Export the runner result (Postman Runner > Export Results) to `api/results.json` and mention it in `docs/QA_SUMMARY.md`.
