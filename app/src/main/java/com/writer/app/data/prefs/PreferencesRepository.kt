package com.writer.app.data.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.writer.app.design.theme.ThemeMode
import com.writer.app.model.Language
import com.writer.app.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "writer_prefs")

/** Persists [UserPreferences] and onboarding state via DataStore. */
class PreferencesRepository(private val context: Context) {

    private object Keys {
        val theme = stringPreferencesKey("theme")
        val language = stringPreferencesKey("language")
        val paperSize = stringPreferencesKey("paper_size")
        val fontSize = floatPreferencesKey("font_size")
        val lineSpacing = floatPreferencesKey("line_spacing")
        val marginLeft = intPreferencesKey("margin_left")
        val marginRight = intPreferencesKey("margin_right")
        val marginTop = intPreferencesKey("margin_top")
        val marginBottom = intPreferencesKey("margin_bottom")
        val autosave = booleanPreferencesKey("autosave")
        val characterCount = booleanPreferencesKey("character_count")
        val haptics = booleanPreferencesKey("haptics")
        val sound = booleanPreferencesKey("sound")
        val saveApplicant = booleanPreferencesKey("save_applicant")
        val userName = stringPreferencesKey("user_name")
        val onboardingDone = booleanPreferencesKey("onboarding_done")
    }

    val preferences: Flow<UserPreferences> = context.dataStore.data.map { p ->
        val defaults = UserPreferences()
        UserPreferences(
            themeMode = p[Keys.theme]?.let { ThemeMode.valueOf(it) } ?: defaults.themeMode,
            language = p[Keys.language]?.let { Language.valueOf(it) } ?: defaults.language,
            paperSize = p[Keys.paperSize] ?: defaults.paperSize,
            fontSizePt = p[Keys.fontSize] ?: defaults.fontSizePt,
            lineSpacing = p[Keys.lineSpacing] ?: defaults.lineSpacing,
            marginLeftMm = p[Keys.marginLeft] ?: defaults.marginLeftMm,
            marginRightMm = p[Keys.marginRight] ?: defaults.marginRightMm,
            marginTopMm = p[Keys.marginTop] ?: defaults.marginTopMm,
            marginBottomMm = p[Keys.marginBottom] ?: defaults.marginBottomMm,
            autosaveEnabled = p[Keys.autosave] ?: defaults.autosaveEnabled,
            characterCountEnabled = p[Keys.characterCount] ?: defaults.characterCountEnabled,
            hapticsEnabled = p[Keys.haptics] ?: defaults.hapticsEnabled,
            soundEnabled = p[Keys.sound] ?: defaults.soundEnabled,
            saveApplicantDetails = p[Keys.saveApplicant] ?: defaults.saveApplicantDetails,
            userName = p[Keys.userName] ?: defaults.userName
        )
    }

    val onboardingCompleted: Flow<Boolean> =
        context.dataStore.data.map { it[Keys.onboardingDone] ?: false }

    suspend fun setOnboardingCompleted(done: Boolean) {
        context.dataStore.edit { it[Keys.onboardingDone] = done }
    }

    suspend fun setTheme(mode: ThemeMode) = edit { it[Keys.theme] = mode.name }
    suspend fun setLanguage(language: Language) = edit { it[Keys.language] = language.name }
    suspend fun setAutosave(enabled: Boolean) = edit { it[Keys.autosave] = enabled }
    suspend fun setCharacterCount(enabled: Boolean) = edit { it[Keys.characterCount] = enabled }
    suspend fun setHaptics(enabled: Boolean) = edit { it[Keys.haptics] = enabled }
    suspend fun setSound(enabled: Boolean) = edit { it[Keys.sound] = enabled }
    suspend fun setSaveApplicantDetails(enabled: Boolean) = edit { it[Keys.saveApplicant] = enabled }
    suspend fun setUserName(name: String) = edit { it[Keys.userName] = name }
    suspend fun setFontSize(pt: Float) = edit { it[Keys.fontSize] = pt }
    suspend fun setLineSpacing(spacing: Float) = edit { it[Keys.lineSpacing] = spacing }

    suspend fun setMargins(left: Int, right: Int, top: Int, bottom: Int) = edit {
        it[Keys.marginLeft] = left
        it[Keys.marginRight] = right
        it[Keys.marginTop] = top
        it[Keys.marginBottom] = bottom
    }

    private suspend fun edit(block: (androidx.datastore.preferences.core.MutablePreferences) -> Unit) {
        context.dataStore.edit(block)
    }
}
