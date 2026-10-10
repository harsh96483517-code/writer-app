# Writer — Write • Apply • Get Results

A premium, Apple-inspired **Liquid Glass** document and letter-writing app for
Android. Writer helps students, citizens and job-seekers produce professional
Hindi and English applications, complaints, requests and personal letters —
then preview them on A4 paper, save them locally, export real PDFs, and print
or share them.

> Built from the brief in [`docs/SPEC.md`](docs/SPEC.md). Design mockups live in
> [`docs/mockups/`](docs/mockups).

## Tech stack

| Area | Choice |
|------|--------|
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 (customised into a Liquid Glass system) |
| Navigation | Navigation Compose |
| Local storage | Room (documents) |
| Settings | DataStore (Preferences) |
| PDF | `android.graphics.pdf.PdfDocument` + `StaticLayout` (real, selectable text) |
| Printing | Android Print Framework (`PrintDocumentAdapter`) |
| File output | Storage Access Framework + `FileProvider` sharing |
| Min / target SDK | 24 / 34 |

## Project structure

```
app/src/main/java/com/writer/app/
├── design/          # Liquid Glass design system
│   ├── theme/       # Color, Type, Shape, Theme (+ light/dark palettes)
│   └── components/  # GlassCard, GlassButton, GlassSearchBar, ... (18 components)
├── model/           # Document, Template, Category, Recipient, ApplicantDetails, UserPreferences
├── data/
│   ├── db/          # Room entities, DAO, converters, database
│   ├── prefs/       # DataStore preferences repository
│   ├── repository/  # DocumentRepository, TemplateRepository
│   └── templates/   # 30 built-in Hindi/English templates + 7 categories
├── pdf/             # DocumentLayoutEngine, PdfGenerator, PdfPrinter, DocGeometry
├── storage/         # FileExporter (FileProvider-backed)
├── common/          # DateUtils, document spec constants
├── di/              # AppContainer + ViewModel factory
├── navigation/      # Routes + NavHost
├── ui/              # WriterApp root + floating bottom navigation
└── features/        # onboarding, home, templates, editor, preview, files, profile, settings
```

## Features implemented

- [x] Animated splash-style launch + 3-slide onboarding (saved locally)
- [x] Glass dashboard: greeting, working search, hero card, quick actions, categories, recent documents
- [x] Searchable template library with category filters, favourites and language badges
- [x] 30 built-in templates across Government, Education, Schemes, Complaints, Banking, Employment, Personal
- [x] Form-based editor: recipient, applicant, subject, body, closing — with validation (PIN / mobile)
- [x] Debounced autosave + draft status + local Room persistence
- [x] Hindi and English content (Unicode Devanagari preserved)
- [x] A4 preview sheet with page count
- [x] Real multi-page A4 PDF export via the Android create-document picker (custom filename, no silent overwrite)
- [x] Native print integration and PDF share via `FileProvider`
- [x] My Files: tabs (all/draft/completed/favourites), search, sort, delete with confirmation
- [x] Profile + Settings: theme (light/dark/system), language, editor toggles, privacy actions
- [x] Light and dark Liquid Glass themes, responsive layouts, edge-to-edge

## Building

The project targets **AGP 8.5.2 / Kotlin 1.9.24 / Gradle 8.7 / JDK 17**.

1. Open the folder in **Android Studio (Ladybug or newer)** — it will generate the
   Gradle wrapper automatically — or run `gradle wrapper --gradle-version 8.7` once
   with a local Gradle install.
2. Build a debug APK:

   ```bash
   ./gradlew assembleDebug
   ```

3. Output: `app/build/outputs/apk/debug/app-debug.apk`.
4. Release APK: add a signing config, then `./gradlew assembleRelease`.

## Known limitations

- No pre-built APK is committed. The environment this project was generated in
  has **no Android SDK**, so it has **not been compiled**. Treat the first
  `assembleDebug` as the real build gate and expect to resolve any small
  dependency/version drift.
- The Gradle wrapper JAR is intentionally not committed (binary); let Android
  Studio or a local `gradle wrapper` task create it.
- Templates are editable **examples**, not official government forms and carry no
  legal guarantee of acceptance.
- The preview screen renders a faithful Compose approximation of the printed
  page; the exported PDF uses the real `StaticLayout`-based layout engine.
- No cloud sync, accounts or analytics — by design, all data stays on-device.

## Privacy

Documents, addresses and letters are stored **locally only** and are never
uploaded. No contacts, SMS or location permissions are requested. Export and
share are always explicit user actions.
