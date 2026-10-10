PROJECT: WRITER — PREMIUM LIQUID GLASS DOCUMENT & LETTER WRITING APP

Act as a senior UI/UX designer, Android application developer, product designer, and software architect.

Your task is to design and build a complete, professional, production-oriented letter-writing application called Writer.

The application must have a premium Apple-inspired Liquid Glass interface, smooth animations, a clean document editor, professional Hindi and English letter templates, and reliable PDF generation and printing.

Do not create only a UI mockup or a static prototype. Build a functional application with working navigation, forms, document editing, saving, PDF export, and printing.

If you are working in an existing project, inspect the entire project before making changes. Preserve working features and avoid unnecessary rewrites.

If no project exists, create a new project with an appropriate Android-compatible technology stack.

---

1. APP IDENTITY

Application name: Writer

Tagline: Write • Apply • Get Results

Primary purpose:
Help users create professional applications, government letters, complaints, requests, personal letters, and other documents without needing advanced typing or formatting skills.

Target audience:

- Indian students
- College students
- General citizens
- Government-service applicants
- Job seekers
- People who need formal Hindi or English letters
- Users who need printable A4 documents

Supported languages:

1. Hindi
2. English

The UI must feel premium, minimal, elegant, modern, and easy to understand.

Avoid a cheap template-app appearance.

---

2. DESIGN SYSTEM — APPLE-INSPIRED LIQUID GLASS

Use an iOS-inspired Liquid Glass aesthetic throughout the application.

Do not simply place transparent cards on a black background. Create a coherent design system with depth, light, transparency, reflections, and layered surfaces.

A. Main visual style

Use:

- Translucent glass surfaces
- Background blur
- Subtle specular highlights
- Soft gradients
- Thin illuminated borders
- Layered translucent panels
- Smooth rounded corners
- Subtle ambient shadows
- Carefully controlled glow
- Floating navigation elements
- Premium SF-style typography where available and appropriately licensed

The design should look like a modern iPhone application while remaining fully usable on Android devices.

Do not imitate Apple's logo or imply that the application is an official Apple product.

B. Color palette

Dark theme:

Background:
#050816

Secondary background:
#0A1022

Glass surface:
RGBA(255,255,255,0.06)

Elevated glass surface:
RGBA(255,255,255,0.10)

Glass border:
RGBA(255,255,255,0.14)

Primary blue:
#3478F6

Electric blue:
#48A5FF

Purple:
#8557FF

Accent gradient:
Blue → Indigo → Purple

Primary text:
#F7F8FF

Secondary text:
#A5AEC4

Muted text:
#737E97

Success:
#37D6A0

Warning:
#FFBF69

Error:
#FF647C

Use gradients sparingly. Important actions should stand out without making every component glow.

C. Light theme

Create a complete light theme as well.

Background:
#F4F6FC

Primary glass surface:
RGBA(255,255,255,0.72)

Elevated surface:
RGBA(255,255,255,0.90)

Primary text:
#151B2C

Secondary text:
#647089

Border:
RGBA(30,45,80,0.10)

Primary gradient:
Soft blue → violet

Maintain excellent contrast and readability in both themes.

D. Glass component specifications

Create reusable components:

1. GlassCard
2. GlassButton
3. GlassIconButton
4. GlassSearchBar
5. GlassTextField
6. GlassBottomNavigation
7. GlassBottomSheet
8. GlassDialog
9. GlassTabBar
10. GlassToolbar
11. GlassFloatingActionButton
12. GlassCategoryCard
13. GlassTemplateCard
14. GlassDocumentCard
15. GlassSegmentedControl
16. GlassToast
17. GlassLoadingIndicator
18. GlassEmptyState

Cards should generally use 20–28 dp corner radii.

Buttons should generally use 16–22 dp corner radii.

Use 1 dp translucent borders and subtle shadows.

Keep padding consistent, generally using an 8 dp spacing system.

Use real background blur when supported. If expensive or unsupported, use a translucent color overlay with a subtle gradient instead.

Never apply expensive blur to every screen element simultaneously.

The interface must remain responsive on mid-range Android devices.

---

3. APP LAUNCH AND ONBOARDING

Create an elegant launch screen.

Display:

- A custom pen-and-paper icon
- Writer branding
- Tagline: Write • Apply • Get Results
- A subtle animated blue-purple light effect

Animation:

- Fade in the logo
- Slight scale-up
- Soft light sweep
- Smooth transition to the home screen

Avoid long startup animations.

Create a first-launch onboarding flow with three slides.

Slide 1:
Title: Write Smarter
Description: Create professional letters and applications in minutes.

Slide 2:
Title: Ready-to-Use Templates
Description: Choose from useful Hindi and English document templates.

Slide 3:
Title: Export and Print
Description: Save your documents as PDFs and print them in A4 format.

Buttons:

- Skip
- Next
- Get Started

Save onboarding completion locally so it does not appear on every launch.

Do not require an account to use basic writing features.

---

4. HOME SCREEN

Design an exceptional iPhone-inspired glass dashboard.

Top section

Display:

- Writer logo
- Greeting: "नमस्ते!"
- Optional personalized greeting name
- Small profile button
- Settings shortcut

Example:

"नमस्ते, Harsh 👋"

"आपका पत्र, आपका दस्तावेज़ — सब एक जगह।"

Do not force users to provide their name.

Search bar

Placeholder:
"किस प्रकार का पत्र लिखना है?"

Search must work across:

- Template titles
- Categories
- Keywords
- Hindi names
- English names

Include a clear button and search icon.

Display search results dynamically.

Hero card

Create a beautiful translucent blue-purple hero card.

Title:
"आवेदन लिखना अब आसान"

Description:
"सही फॉर्मेट में अपना आवेदन तैयार करें और PDF में सेव करें।"

Primary button:
"शुरू करें"

Add a subtle floating document illustration.

Keep the illustration lightweight and avoid distracting effects.

Quick actions

Create four quick-action buttons:

1. नया आवेदन
2. शिकायत पत्र
3. प्रमाण पत्र
4. खाली दस्तावेज़

Each action must open the appropriate screen.

Categories section

Display horizontally scrollable glass cards:

- सरकारी आवेदन
- शिक्षा
- नौकरी / करियर
- बैंकिंग
- राशन कार्ड
- बिजली / पानी
- व्यक्तिगत पत्र
- शिकायत पत्र

Include colorful but restrained icons.

Add a "सभी देखें" action.

Recent documents

Display the latest saved documents.

Each document card should show:

- Document title
- Last modified date
- Document type
- Draft or completed status
- Three-dot menu

Actions:

- Open
- Rename
- Duplicate
- Export PDF
- Delete

Ask for confirmation before deleting a document.

Bottom navigation

Use a floating Liquid Glass bottom navigation bar with five destinations:

1. Home
2. Templates
3. Create
4. My Files
5. Profile

The Create button should be visually emphasized.

Use a subtle active-state indicator and smooth transitions.

The bottom navigation must respect Android gesture navigation and screen insets.

---

5. TEMPLATE LIBRARY

Build a complete template browsing experience.

Screen title:
"टेम्पलेट्स"

Features:

- Search
- Category filters
- Recently used
- Favorites
- Most popular
- Recently added

Filter chips should use a premium segmented glass style.

Categories:

A. Government Applications

- Application to District Magistrate
- Application to Sub-Divisional Magistrate
- Application to Tehsildar
- Application to Block Development Officer
- Application to Gram Panchayat Officer
- Application to Village Development Officer
- Application to Lekhpal
- Application to Police Station
- Application for a character certificate
- Application for a residence certificate

B. Education

- School admission application
- College admission application
- Scholarship application
- Bonafide certificate request
- Transfer certificate request
- Correction in school records
- Examination-related application
- Leave application
- Fee concession request
- College principal application

C. Government Schemes and Documents

- Ration card correction request
- Ration card member addition request
- Ration card member removal request
- Aadhaar-related application
- Income certificate request
- Caste certificate request
- Domicile certificate request
- Birth certificate correction request
- Pension-related application
- PM-Kisan-related request

D. Complaints

- Electricity complaint
- Water supply complaint
- Road repair complaint
- Garbage collection complaint
- Drainage complaint
- Streetlight complaint
- Public-service complaint
- Consumer complaint
- Bank-service complaint
- Postal-service complaint

E. Banking and Finance

- Bank account correction request
- Address update request
- Passbook-related application
- ATM card request
- Transaction complaint
- Account statement request
- Loan-related request

F. Employment

- Job application
- Resume cover letter
- Internship application
- Experience certificate request
- Salary certificate request
- Resignation letter
- Leave request

G. Personal Letters

- General formal letter
- Thank-you letter
- Permission request
- Invitation letter
- Apology letter
- Request letter

Important:
Templates are editable examples, not official government forms.

Use neutral, professional language. Do not claim that a template is legally approved or guaranteed to be accepted.

Whenever the template concerns a government office, allow the user to edit the recipient's designation and address.

---

6. TEMPLATE CARD DESIGN

Each template should appear in a polished glass card.

Show:

- Document icon
- Template title
- Short description
- Language badge
- Favorite icon
- Arrow indicator

Use different accent colors for categories, but maintain a consistent design system.

Add press animations and haptic feedback when supported.

Tapping a card should open a template details screen.

Template details screen:

- Template title
- Description
- Available languages
- Short document preview
- Start Writing button
- Add to Favorites button

When the user starts writing, create a new editable document based on that template.

Never overwrite an existing document without explicit confirmation.

---

7. SMART LETTER EDITOR

This is the most important part of the application.

Build a practical form-based letter editor that generates a professional document.

Screen title:
"नया आवेदन पत्र"

A. Recipient section

Fields:

- Recipient designation
- Department or office name
- Office location
- District
- State

Example:
सेवा में,
जिला अधिकारी महोदय,
जनपद — [जिले का नाम]

Allow the user to modify the wording.

B. Applicant details

Fields:

- Applicant name
- Father's or mother's name, when relevant
- Full address
- Village
- Post office
- Block
- District
- State
- PIN code
- Mobile number, optional

Do not require unnecessary personal information.

Provide an option to save frequently used applicant details locally.

This option must be disabled until the user explicitly enables it.

C. Subject field

Label:
"विषय"

Allow a concise subject line.

Provide optional suggestions based on the selected template.

D. Main letter body

Provide a multiline editor.

For Hindi:

- Support Unicode Hindi text.
- Use a suitable Devanagari font.
- Preserve punctuation and line breaks.
- Ensure correct rendering in the editor and exported PDFs.

Include:

- Greeting
- Main body
- Closing
- Applicant signature/name

Users must be able to write entirely from scratch.

E. Editor controls

Create a floating glass toolbar with:

- Bold
- Italic
- Text alignment
- Font size
- Line spacing
- Undo
- Redo
- Insert date
- Insert applicant details
- Preview

Only show formatting controls supported by the document model.

If the main body uses a rich-text editor, ensure its formatting is preserved during PDF generation.

F. Keyboard experience

Optimize for Android keyboards.

Requirements:

- Correct scrolling when the keyboard opens
- No hidden input fields
- Next/Previous field navigation
- Clear focus indicators
- Proper multiline editing
- Draft autosave
- Character count where useful

Do not let the bottom navigation cover the keyboard or the active input.

G. Validation

Validate fields appropriately.

Examples:

- Display a clear message for a missing required field.
- Validate PIN code format when provided.
- Validate mobile number format when provided.
- Never block saving a draft simply because optional information is missing.

Keep validation messages friendly and understandable.

---

8. A4 DOCUMENT GENERATION ENGINE

Create a proper document layout engine.

Do not create the PDF by taking a screenshot of the editor.

The document must be rendered as actual selectable text with correct page dimensions.

Paper size:
A4 — 210 × 297 mm.

Default margins:

- Left: 25 mm
- Right: 20 mm
- Top: 20 mm
- Bottom: 20 mm

Provide optional margin settings.

Default typography:

- Hindi: Noto Sans Devanagari or another properly licensed Devanagari font
- English: a readable professional serif or sans-serif font

Suggested body text:
11–12 pt.

Suggested subject:
12 pt, bold.

Suggested line spacing:
1.3–1.5.

The document must look like a real Indian formal application, not a mobile screen printed onto paper.

Default Hindi application layout

सेवा में,

[प्राप्तकर्ता का पदनाम]
[कार्यालय का नाम]
[जिला / स्थान]

विषय: [आवेदन का विषय]

महोदय / महोदया,

[आवेदन की मुख्य सामग्री]

अतः आपसे विनम्र निवेदन है कि कृपया मेरी समस्या पर विचार करते हुए आवश्यक कार्यवाही करने की कृपा करें।

धन्यवाद।

भवदीय,

[आवेदक का नाम]
[पता]
[दिनांक]

This is only a default structure. Different templates must be able to use different layouts.

A4 formatting rules

- Preserve proper paragraph spacing.
- Support automatic text wrapping.
- Prevent text from extending beyond page boundaries.
- Support multiple pages.
- Keep the closing and signature together where practical.
- Avoid splitting short headings from their following content.
- Handle long paragraphs correctly.
- Preserve Hindi conjuncts and vowel marks.
- Embed or appropriately package licensed fonts when technically and legally permitted.
- Make text selectable and searchable in generated PDFs.

Provide a live preview that resembles the actual printed document.

The preview must use the same layout rules as the PDF renderer.

---

9. DOCUMENT PREVIEW SCREEN

Create a premium document preview screen.

The A4 page should appear as a clean white sheet floating over a dark glass background.

The page itself should not be transparent.

Provide:

- Zoom controls
- Fit-to-page option
- Page navigation for multipage documents
- Edit button
- Save button
- Export PDF button
- Print button
- Share button

Use subtle animations when opening the preview.

Never sacrifice document readability for decorative effects.

Provide a simple, distraction-free preview mode.

---

10. PDF EXPORT

PDF export must work reliably.

When the user taps "PDF बनाएं":

1. Validate the document.
2. Generate a properly formatted A4 PDF.
3. Preserve Hindi and English characters.
4. Preserve supported formatting.
5. Support multiple pages.
6. Display export progress if generation takes time.
7. Open the Android save/share interface or a suitable file picker.
8. Allow the user to choose a filename and save location.
9. Show success or error feedback.

Suggested filename:
Application_2026-10-09.pdf

Allow the user to change the filename.

Do not silently overwrite an existing file.

Do not show a fake download-success animation if PDF generation failed.

Handle:

- Storage permissions and Android scoped storage
- Missing fonts
- Empty documents
- Long documents
- Invalid filenames
- Insufficient storage
- Export cancellation

PDF generation must not require an internet connection.

---

11. PRINT FUNCTIONALITY

Implement native Android printing support where available.

The user should be able to:

- Select an available printer
- Print the document
- Use A4 paper
- Adjust copies and supported print settings
- Cancel printing

If no printer is available, show a clear message and provide the option to save or share the PDF.

Do not pretend that printing succeeded before the operating system confirms the print job was submitted.

Do not require a particular printer brand.

---

12. MY FILES SCREEN

Create a premium file-management screen.

Screen title:
"मेरी फाइलें"

Provide tabs:

- All
- Drafts
- Completed
- PDFs
- Favorites

Features:

- Search
- Sort by date
- Sort by name
- Sort by document type
- Grid/list view
- Rename
- Duplicate
- Edit
- Preview
- Export
- Share
- Delete

Each item should show:

- Filename
- Document type
- Last modified date
- Draft/completed status

Use distinct icons for documents and PDFs.

Empty state:
"अभी कोई दस्तावेज़ नहीं है"

Button:
"पहला दस्तावेज़ बनाएं"

Store documents locally by default.

Ensure documents remain available after the app is closed and reopened.

---

13. AUTOSAVE AND DOCUMENT STORAGE

Implement reliable local storage.

Requirements:

- Save a draft when the user edits a document.
- Avoid saving on every keystroke if doing so causes performance problems.
- Use debounced autosaving.
- Save immediately when the user explicitly presses Save.
- Preserve document IDs.
- Track created and modified timestamps.
- Restore unfinished drafts after reopening the app.
- Recover safely after unexpected app termination where practical.

Use a suitable local database or structured persistence layer.

For example, Room may be appropriate for a native Android application, while an appropriate local database may be used in a cross-platform framework.

Avoid using a single unstructured text file as the entire database.

Do not require cloud storage or login for basic functionality.

Never upload personal letters, addresses, or document contents to a server without explicit consent.

---

14. CREATE DOCUMENT FLOW

When the user taps the central Create button, open a beautiful glass bottom sheet.

Options:

1. Blank Document
2. Application Template
3. Complaint Letter
4. Personal Letter
5. Continue Recent Draft

Each option should have:

- An icon
- A concise description
- A clear tap target

Use a smooth spring-style entrance animation.

The bottom sheet must be dismissible and accessible.

---

15. PROFILE SCREEN

Create a simple profile and preferences screen.

Show:

- Optional user name
- Optional profile image
- Number of saved documents
- Number of exported PDFs
- App preferences
- Help and support
- Privacy policy
- About Writer

Do not force users to sign in.

Do not invent an email address or display personal details unless the user has provided them.

Add an optional setting to save frequently used applicant details locally.

Clearly explain what is saved and how the user can remove it.

---

16. SETTINGS SCREEN

Include:

Appearance:

- Light
- Dark
- System default

Language:

- Hindi
- English

Document settings:

- Default paper size
- Default font
- Default font size
- Default margins
- Default line spacing
- Default language

Editor settings:

- Autosave toggle
- Character count toggle
- Haptic feedback toggle

File settings:

- Default export filename format
- Recent documents display

Privacy:

- Clear recent searches
- Clear saved applicant details
- Delete all local documents

Require confirmation before destructive actions.

Do not promise that deleting local app data removes previously exported PDF files from external folders.

---

17. ANIMATIONS AND MICROINTERACTIONS

Create premium, restrained animations.

Home screen:

- Smooth fade-in
- Subtle card entrance
- Gentle gradient movement

Buttons:

- Small press-scale effect
- Soft highlight
- Clear pressed state

Cards:

- Smooth selection animation
- Subtle elevation transition

Navigation:

- Smooth screen transitions
- Animated active icon
- Stable layout

Bottom sheets:

- Spring-style entrance
- Smooth dismissal

Editor:

- Smooth focus transitions
- Animated formatting toolbar
- Non-disruptive autosave indicator

PDF preview:

- Page fade-in
- Smooth zoom
- Smooth page transitions

Loading:

- Lightweight progress indicator
- Clear status messages

Use animation durations appropriate to the action, generally around 150–350 ms.

Avoid excessive bounce effects, continuous glow animations, or animation that interferes with typing.

Respect Android's reduced-motion or animation settings where possible.

---

18. SOUND AND HAPTIC FEEDBACK

Sound is optional and disabled by default.

If sound effects are implemented, keep them extremely subtle.

Possible effects:

- Light confirmation sound
- Successful export sound

Provide a sound toggle.

Never play sounds automatically on every navigation action.

Use haptic feedback only when supported by the device and permitted by platform settings.

---

19. TECHNICAL ARCHITECTURE

First inspect the existing project and determine the best compatible implementation.

If starting from scratch, prefer a native Android implementation using:

- Kotlin
- Jetpack Compose
- Material 3 components customized to the Liquid Glass design
- Navigation Compose
- Room for structured local document storage
- DataStore for settings
- Android Storage Access Framework for saving files
- Android Print Framework for printing
- A reliable PDF generation implementation
- A suitable Unicode-capable text layout engine

If the existing project uses Flutter or another working cross-platform framework, preserve that framework unless there is a compelling technical reason to migrate.

Do not mix multiple frameworks unnecessarily.

Use a modular architecture.

Suggested modules:

app/
core/
design/
model/
database/
storage/
pdf/
common/

features/
onboarding/
home/
templates/
editor/
preview/
files/
profile/
settings/

Separate:

- UI
- Business logic
- Data models
- Storage
- PDF generation
- Navigation

Use reusable components and maintainable code.

Do not put the entire application into one giant source file.

---

20. DATA MODELS

Create appropriate models.

Document:

- id
- title
- templateId
- language
- recipient
- applicantDetails
- subject
- body
- closing
- createdAt
- updatedAt
- status
- isFavorite
- documentFormatting

Template:

- id
- titleHindi
- titleEnglish
- descriptionHindi
- descriptionEnglish
- category
- languageSupport
- defaultContent
- fieldDefinitions
- isBuiltIn

Category:

- id
- titleHindi
- titleEnglish
- icon
- accentColor

UserPreferences:

- theme
- language
- paperSize
- fontSize
- margins
- lineSpacing
- autosaveEnabled
- hapticsEnabled
- soundEnabled

Use appropriate database types rather than storing everything as arbitrary strings.

Make built-in templates available offline.

---

21. ACCESSIBILITY AND RESPONSIVENESS

The app must work on different Android screen sizes.

Requirements:

- Support small and large screens.
- Respect display cutouts and system bars.
- Support Android gesture navigation.
- Use appropriate text scaling.
- Maintain accessible contrast.
- Provide accessible labels for icons.
- Use minimum comfortable touch targets.
- Avoid fixed dimensions that cause text clipping.
- Ensure Hindi text is never cut off.
- Handle keyboard appearance correctly.
- Avoid horizontal overflow.

Do not assume every Android phone has the same aspect ratio or display density.

Use adaptive layouts where appropriate.

---

22. PERFORMANCE

Optimize for mid-range Android devices.

Requirements:

- Avoid excessive blur layers.
- Avoid expensive continuous animations.
- Avoid unnecessary recomposition.
- Use lazy lists for large template collections.
- Load document previews efficiently.
- Perform expensive PDF generation away from the main UI thread.
- Prevent duplicate export jobs.
- Avoid blocking the interface during database operations.
- Avoid unnecessary background services.

The app should remain responsive while scrolling, typing, saving, and switching screens.

---

23. ERROR HANDLING

Create polished, user-friendly error states.

Examples:

Save failed:
"दस्तावेज़ सेव नहीं हो सका। कृपया दोबारा प्रयास करें।"

PDF failed:
"PDF नहीं बन सकी। अपनी फाइल और फ़ॉन्ट सेटिंग्स जाँचें।"

Empty search:
"कोई टेम्पलेट नहीं मिला। दूसरे शब्दों से खोजें।"

Printer unavailable:
"अभी कोई प्रिंटर उपलब्ध नहीं है। आप PDF सेव कर सकते हैं।"

Always provide a useful next action.

Do not expose stack traces or internal exceptions to ordinary users.

Log technical details appropriately during development.

Never hide data-loss risks behind generic success messages.

---

24. SECURITY AND PRIVACY

The app may contain names, addresses, complaints, and other personal information.

Therefore:

- Store data locally by default.
- Request only necessary permissions.
- Do not require contacts access.
- Do not request SMS access.
- Do not request location access.
- Do not transmit documents to external servers by default.
- Do not include secret API keys in the app.
- Do not collect analytics containing document contents.
- Do not automatically share files.
- Make deletion actions explicit.

Any optional online service must be clearly identified and separately consented to.

---

25. APP ICON AND BRANDING

Create a premium Writer app icon.

Concept:

- Minimal pen nib
- Elegant document outline
- Deep navy background
- Blue-purple glass gradient
- Soft reflective highlight
- Clean silhouette
- No unnecessary text inside the icon

Create appropriate Android launcher assets and adaptive icon resources.

Keep the visual branding consistent throughout the app.

---

26. FINAL DESIGN QUALITY

The result must feel like a premium modern mobile application.

Avoid:

- Generic default Material UI appearance
- Excessive neon glow
- Random gradients
- Inconsistent corner radii
- Cluttered screens
- Too many colors
- Tiny text
- Fake buttons
- Placeholder interactions
- Broken navigation
- Decorative glass effects that reduce readability
- Screenshot-based PDF generation
- Hardcoded user information
- Nonfunctional export buttons

Prioritize:

1. Usability
2. Document quality
3. Reliable saving
4. Correct Hindi rendering
5. Premium Liquid Glass visuals
6. Smooth performance
7. Accessibility

The app should look beautiful, but the core writing and document-generation experience must be reliable.

---

27. REQUIRED WORKING FEATURES

The following features are mandatory:

[ ] Launch screen
[ ] Onboarding
[ ] Home dashboard
[ ] Searchable template library
[ ] Category filters
[ ] Hindi and English interface
[ ] Functional application templates
[ ] Functional complaint-letter templates
[ ] Editable recipient details
[ ] Editable applicant details
[ ] Editable subject
[ ] Editable letter body
[ ] Blank document creation
[ ] Local document storage
[ ] Autosave
[ ] Draft recovery
[ ] Document renaming
[ ] Document duplication
[ ] Favorites
[ ] Search saved documents
[ ] A4 preview
[ ] Real PDF generation
[ ] Hindi Unicode rendering
[ ] Multpage PDF support
[ ] Android print integration
[ ] PDF sharing
[ ] Theme switching
[ ] Language switching
[ ] Settings
[ ] Error handling
[ ] Responsive layout
[ ] Consistent Liquid Glass design

Do not mark a feature as complete unless it actually works.

---

28. IMPLEMENTATION WORKFLOW

Follow this order.

PHASE 1 — INSPECT

Inspect the project files, build configuration, existing assets, dependencies, and current application structure.

Identify:

- Framework
- Entry point
- Current navigation
- Existing UI
- Existing document functionality
- Missing dependencies
- Build problems

PHASE 2 — PLAN

Provide a concise implementation plan.

Identify any technical limitations before making major changes.

PHASE 3 — DESIGN SYSTEM

Implement:

- Colors
- Typography
- Spacing
- Glass components
- Buttons
- Cards
- Navigation
- Themes

PHASE 4 — MAIN SCREENS

Build the home screen, templates, editor, preview, files, profile, and settings.

Connect all navigation actions.

PHASE 5 — FUNCTIONALITY

Implement storage, autosave, document editing, template data, search, PDF generation, sharing, and printing.

PHASE 6 — TESTING

Run available build and test commands.

Fix compilation errors, runtime issues, navigation failures, and formatting problems.

Test Hindi and English output, especially long documents and multi-page PDFs.

PHASE 7 — FINAL DELIVERY

Provide:

- Complete source code
- Updated project files
- Build instructions
- Dependency information
- A list of implemented features
- A list of known limitations
- A debug APK if the environment supports Android builds
- Clear instructions for producing a release APK

Do not claim that an APK was generated unless the build actually succeeded.

Do not stop after generating a design description or a few example components.

Continue implementing the application in the available environment.

If a requirement cannot be completed, explain why and implement the best reliable alternative.

FINAL OBJECTIVE:

Deliver a functional, premium, iPhone-inspired Liquid Glass Writer application for Android that allows users to create professional Hindi and English letters, edit them, preview them on A4 paper, save documents locally, export real PDFs, and print or share them.

The visual quality should be exceptional, but functionality and document accuracy are equally important.