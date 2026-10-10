package com.writer.app.features.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.writer.app.design.components.GlassButton
import com.writer.app.design.components.GlassCard
import com.writer.app.design.components.GlassDialog
import com.writer.app.design.components.GlassSegmentedControl
import com.writer.app.design.components.GlassToolbar
import com.writer.app.design.theme.ThemeMode
import com.writer.app.design.theme.WriterTheme
import com.writer.app.di.AppContainer
import com.writer.app.model.Language
import com.writer.app.model.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(container: AppContainer, onBack: () -> Unit) {
    val glass = WriterTheme.colors
    val scope = rememberCoroutineScope()
    val prefs by container.prefs.preferences.collectAsState(initial = UserPreferences())
    var confirmDeleteAll by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        GlassToolbar(title = "सेटिंग्स", onBack = onBack)

        LazyColumn(
            Modifier.weight(1f).padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    SectionTitle("रूप (Appearance)")
                    GlassSegmentedControl(
                        options = listOf("हल्का", "गहरा", "सिस्टम"),
                        selectedIndex = when (prefs.themeMode) {
                            ThemeMode.LIGHT -> 0
                            ThemeMode.DARK -> 1
                            ThemeMode.SYSTEM -> 2
                        },
                        onSelect = {
                            val mode = when (it) {
                                0 -> ThemeMode.LIGHT
                                1 -> ThemeMode.DARK
                                else -> ThemeMode.SYSTEM
                            }
                            scope.launch { container.prefs.setTheme(mode) }
                        }
                    )
                }
            }
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    SectionTitle("भाषा (Language)")
                    GlassSegmentedControl(
                        options = listOf("हिंदी", "English"),
                        selectedIndex = if (prefs.language == Language.HINDI) 0 else 1,
                        onSelect = {
                            scope.launch {
                                container.prefs.setLanguage(if (it == 0) Language.HINDI else Language.ENGLISH)
                            }
                        }
                    )
                }
            }
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    SectionTitle("एडिटर (Editor)")
                    ToggleRow("ऑटोसेव", prefs.autosaveEnabled) {
                        scope.launch { container.prefs.setAutosave(it) }
                    }
                    ToggleRow("अक्षर गणना", prefs.characterCountEnabled) {
                        scope.launch { container.prefs.setCharacterCount(it) }
                    }
                    ToggleRow("हैप्टिक फ़ीडबैक", prefs.hapticsEnabled) {
                        scope.launch { container.prefs.setHaptics(it) }
                    }
                    ToggleRow("ध्वनि", prefs.soundEnabled) {
                        scope.launch { container.prefs.setSound(it) }
                    }
                }
            }
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    SectionTitle("गोपनीयता (Privacy)")
                    Text(
                        "सभी दस्तावेज़ केवल इस डिवाइस पर सुरक्षित रहते हैं। कोई डेटा सर्वर पर नहीं भेजा जाता।",
                        color = glass.textSecondary, style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(10.dp))
                    GlassButton(
                        text = "सभी स्थानीय दस्तावेज़ हटाएँ",
                        onClick = { confirmDeleteAll = true },
                        filled = false
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "नोट: पहले निर्यात की गई PDF फ़ाइलें बाहरी फ़ोल्डर से नहीं हटेंगी।",
                        color = glass.textMuted, style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    SectionTitle("ऐप के बारे में")
                    Text("Writer v1.0.0", color = glass.textPrimary, style = MaterialTheme.typography.bodyLarge)
                    Text("Write • Apply • Get Results", color = glass.textMuted,
                        style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }

    if (confirmDeleteAll) {
        GlassDialog(
            title = "सभी दस्तावेज़ हटाएँ?",
            message = "आपके सभी स्थानीय दस्तावेज़ हमेशा के लिए हट जाएंगे। यह वापस नहीं आएगा।",
            confirmText = "हाँ, हटाएँ",
            dismissText = "रद्द करें",
            onConfirm = {
                scope.launch { container.documents.deleteAll() }
                confirmDeleteAll = false
            },
            onDismiss = { confirmDeleteAll = false }
        )
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium,
        color = WriterTheme.colors.textPrimary, modifier = Modifier.padding(bottom = 8.dp))
}

@Composable
private fun ToggleRow(label: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = WriterTheme.colors.textPrimary,
            style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onChange)
    }
}
