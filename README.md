# VehicleHMI_Demo

A small Android app written in Kotlin that mimics a vehicle HMI (Human–Machine Interface) status screen. Tap **Fetch Vehicle Data**, and after a short delay the app shows the vehicle's engine state, speed, and fuel level.

> **Note:** This is a personal practice project. All vehicle data is simulated: the values are hard-coded and nothing connects to a real vehicle.

## What it demonstrates

- **Background work off the UI thread.** The "fetch" runs on a background thread (`thread { ... }`), so the screen stays responsive during the simulated 1.5-second delay.
- **Safe UI updates.** Results are posted back with `runOnUiThread`, because Android only allows the main thread to change views.
- **Classic Android views.** An XML layout (`activity_main.xml`) with a `TextView` and a `Button`, wired up in `MainActivity` with `findViewById`.

## Tech stack

| | |
|---|---|
| Language | Kotlin 2.0.21 |
| UI | XML layouts + AppCompat (`AppCompatActivity`) |
| Build | Gradle (Kotlin DSL) with Android Gradle Plugin 8.13 |
| Min / target SDK | 24 (Android 7.0) / 36 |

## Project structure

```
app/src/main/
├── java/com/example/vehiclehmi_demo/
│   ├── MainActivity.kt        # Button click → background fetch → update status text
│   └── ui/theme/              # Compose theme files from the project template (not used by this screen)
├── res/layout/activity_main.xml   # Status text + "Fetch Vehicle Data" button
├── res/values/themes.xml          # AppCompat theme
└── AndroidManifest.xml
```

## Running it

You need the Android SDK and JDK 17 or newer. You can use Android Studio (open the folder and press **Run**), or run it from the terminal.

```bash
# 1. Start an emulator (list yours with: emulator -list-avds)
~/Library/Android/sdk/emulator/emulator -avd <your_avd_name>

# 2. In a second terminal: build and install the debug app
./gradlew installDebug

# 3. Launch it
~/Library/Android/sdk/platform-tools/adb shell am start -n com.example.vehiclehmi_demo/.MainActivity
```

To build the APK only: `./gradlew assembleDebug`. The output goes to `app/build/outputs/apk/debug/app-debug.apk`.

## Possible next steps

- Replace the hard-coded values with randomized or streaming data so the display updates live.
- Move the fetch logic into a `ViewModel` with Kotlin coroutines instead of a raw thread.
- Add more signals (RPM, tire pressure, battery) and a dashboard-style layout.
