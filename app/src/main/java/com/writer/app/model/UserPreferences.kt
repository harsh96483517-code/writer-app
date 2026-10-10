package com.writer.app.model

import com.writer.app.design.theme.ThemeMode

/** All user-configurable settings, persisted with DataStore. */
data class UserPreferences(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val language: Language = Language.HINDI,
    val paperSize: String = "A4",
    val fontSizePt: Float = 12f,
    val lineSpacing: Float = 1.4f,
    val marginLeftMm: Int = 25,
    val marginRightMm: Int = 20,
    val marginTopMm: Int = 20,
    val marginBottomMm: Int = 20,
    val autosaveEnabled: Boolean = true,
    val characterCountEnabled: Boolean = true,
    val hapticsEnabled: Boolean = true,
    val soundEnabled: Boolean = false,
    val saveApplicantDetails: Boolean = false,
    val userName: String = ""
)
