package com.writer.app.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.writer.app.data.templates.BuiltInCategories
import com.writer.app.design.components.*
import com.writer.app.design.theme.PrimaryBlue
import com.writer.app.design.theme.Purple
import com.writer.app.design.theme.WriterTheme
import com.writer.app.di.AppContainer
import com.writer.app.model.Language
import com.writer.app.model.UserPreferences
import com.writer.app.navigation.Routes
import com.writer.app.ui.WriterBottomBar

private val quickActions = listOf(
    "नया आवेदन" to "✍️",
    "शिकायत पत्र" to "⚠️",
    "प्रमाण पत्र" to "📑",
    "खाली दस्तावेज़" to "🗎"
)

@Composable
fun HomeScreen(
    container: AppContainer,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    onCreateClick: () -> Unit
) {
    val glass = WriterTheme.colors
    val prefs by container.prefs.preferences.collectAsState(initial = UserPreferences())
    var query by remember { mutableStateOf("") }

    val recent by container.documents.observeRecent(5)
        .collectAsState(initial = emptyList())

    val results = remember(query, prefs.language) {
        if (query.isBlank()) emptyList()
        else container.templates.search(query, prefs.language)
    }

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 48.dp, bottom = 110.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            "नमस्ते${if (prefs.userName.isNotBlank()) ", ${prefs.userName}" else ""}!",
                            style = MaterialTheme.typography.headlineMedium,
                            color = glass.textPrimary
                        )
                        Text(
                            "आपका पत्र, आपका दस्तावेज़ — सब एक जगह।",
                            style = MaterialTheme.typography.bodyMedium,
                            color = glass.textSecondary
                        )
                    }
                    GlassIconButton(onClick = { onNavigate(Routes.PROFILE) }) {
                        Text("👤", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }

            item {
                GlassSearchBar(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = "किस प्रकार का पत्र लिखना है?"
                )
            }

            if (query.isNotBlank()) {
                if (results.isEmpty()) {
                    item {
                        GlassEmptyState("🔍", "कोई टेम्पलेट नहीं मिला। दूसरे शब्दों से खोजें।")
                    }
                } else {
                    items(results) { template ->
                        GlassTemplateCard(
                            title = template.title(prefs.language),
                            description = template.description(prefs.language),
                            accent = PrimaryBlue,
                            languages = template.languageSupport.joinToString("/") { if (it == Language.HINDI) "Hindi" else "English" },
                            isFavorite = container.templates.isFavorite(template.id),
                            onFavoriteToggle = { container.templates.toggleFavorite(template.id) },
                            onClick = { onNavigate(Routes.templateDetail(template.id)) }
                        )
                    }
                }
            } else {
                item { HeroCard(onStart = { onNavigate(Routes.TEMPLATES) }) }

                item {
                    Text("त्वरित कार्य", style = MaterialTheme.typography.titleLarge, color = glass.textPrimary)
                }
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                        quickActions.forEach { (label, emoji) ->
                            QuickAction(label, emoji, Modifier.weight(1f)) { onNavigate(Routes.TEMPLATES) }
                        }
                    }
                }

                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("श्रेणियाँ", style = MaterialTheme.typography.titleLarge, color = glass.textPrimary, modifier = Modifier.weight(1f))
                        Text("सभी देखें →", color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.clickable { onNavigate(Routes.TEMPLATES) })
                    }
                }
                item {
                    Row(
                        Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        BuiltInCategories.all.forEach { cat ->
                            GlassCategoryCard(
                                title = cat.title(prefs.language),
                                emoji = cat.emoji,
                                accent = Color(cat.accentColorHex),
                                onClick = { onNavigate(Routes.TEMPLATES) }
                            )
                        }
                    }
                }

                item {
                    Text("हाल के दस्तावेज़", style = MaterialTheme.typography.titleLarge, color = glass.textPrimary)
                }
                if (recent.isEmpty()) {
                    item {
                        GlassEmptyState(
                            emoji = "🗂️",
                            title = "अभी कोई दस्तावेज़ नहीं है",
                            actionText = "पहला दस्तावेज़ बनाएं",
                            onAction = onCreateClick
                        )
                    }
                } else {
                    items(recent) { doc ->
                        GlassDocumentCard(
                            title = doc.title.ifBlank { "बिना शीर्षक" },
                            subtitle = com.writer.app.common.DateUtils.display(doc.updatedAt),
                            status = if (doc.status.name == "COMPLETED") "पूर्ण" else "ड्राफ्ट",
                            onClick = { onNavigate(Routes.editor(doc.id, doc.templateId ?: "none")) },
                            onMore = { onNavigate(Routes.preview(doc.id)) }
                        )
                    }
                }
            }
        }

        WriterBottomBar(
            currentRoute = currentRoute,
            onSelect = { if (it == "create") onCreateClick() else onNavigate(it) },
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)
        )
    }
}

@Composable
private fun HeroCard(onStart: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(Brush.linearGradient(listOf(PrimaryBlue, Purple)))
            .padding(20.dp)
    ) {
        Column {
            Text("आवेदन लिखना अब आसान", style = MaterialTheme.typography.titleLarge,
                color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(6.dp))
            Text("सही फॉर्मेट में अपना आवेदन तैयार करें और PDF में सेव करें।",
                style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.9f))
            Spacer(Modifier.height(14.dp))
            GlassButton(text = "शुरू करें →", onClick = onStart, filled = false)
        }
    }
}

@Composable
private fun QuickAction(label: String, emoji: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val glass = WriterTheme.colors
    Column(
        modifier = modifier
            .glassSurface(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(emoji, style = MaterialTheme.typography.titleLarge)
        Text(label, style = MaterialTheme.typography.labelSmall, color = glass.textPrimary,
            maxLines = 2, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}
