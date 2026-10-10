package com.writer.app.features.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.writer.app.design.components.GlassButton
import com.writer.app.design.components.GlassCard
import com.writer.app.design.components.GlassSegmentedControl
import com.writer.app.design.components.GlassToolbar
import com.writer.app.design.theme.WriterTheme
import com.writer.app.di.AppContainer
import com.writer.app.model.Language
import com.writer.app.model.UserPreferences

@Composable
fun TemplateDetailScreen(
    container: AppContainer,
    templateId: String,
    onBack: () -> Unit,
    onStartWriting: (String, Language) -> Unit
) {
    val glass = WriterTheme.colors
    val prefs by container.prefs.preferences.collectAsState(initial = UserPreferences())
    val template = remember(templateId) { container.templates.template(templateId) }

    if (template == null) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            GlassToolbar(title = "टेम्पलेट", onBack = onBack)
            Spacer(Modifier.height(24.dp))
            Text("टेम्पलेट नहीं मिला।", color = glass.textSecondary)
        }
        return
    }

    var language by remember { mutableStateOf(prefs.language) }
    var favorite by remember { mutableStateOf(container.templates.isFavorite(template.id)) }

    Column(
        Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        GlassToolbar(title = template.title(language), onBack = onBack)

        Column(
            Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(template.description(language), color = glass.textSecondary,
                style = MaterialTheme.typography.bodyLarge)

            GlassSegmentedControl(
                options = listOf("Hindi", "English"),
                selectedIndex = if (language == Language.HINDI) 0 else 1,
                onSelect = { language = if (it == 0) Language.HINDI else Language.ENGLISH }
            )

            Text("विवरण / Preview", style = MaterialTheme.typography.titleMedium, color = glass.textPrimary)

            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    template.defaultContent(language),
                    color = glass.textPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(Modifier.height(12.dp))
        GlassButton(
            text = "शुरू करें",
            onClick = { onStartWriting(template.id, language) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))
        Row(
            Modifier.fillMaxWidth().clickable {
                container.templates.toggleFavorite(template.id)
                favorite = !favorite
            }.padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                (if (favorite) "♥ " else "♡ ") + (if (favorite) "पसंदीदा से हटाएँ" else "पसंदीदा में जोड़ें"),
                color = if (favorite) MaterialTheme.colorScheme.error else glass.textSecondary,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
