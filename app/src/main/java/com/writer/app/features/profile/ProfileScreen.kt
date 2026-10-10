package com.writer.app.features.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.writer.app.design.components.GlassCard
import com.writer.app.design.components.GlassToolbar
import com.writer.app.design.theme.WriterTheme
import com.writer.app.di.AppContainer
import com.writer.app.model.UserPreferences
import com.writer.app.navigation.Routes
import com.writer.app.ui.WriterBottomBar

@Composable
fun ProfileScreen(
    container: AppContainer,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    onCreateClick: () -> Unit
) {
    val glass = WriterTheme.colors
    val prefs by container.prefs.preferences.collectAsState(initial = UserPreferences())
    val docs by container.documents.observeAll().collectAsState(initial = emptyList())
    val favorites = docs.count { it.isFavorite }

    val menu = listOf(
        "मेरी फ़ाइलें" to Routes.FILES,
        "सेटिंग्स" to Routes.SETTINGS,
        "थीम" to Routes.SETTINGS,
        "भाषा" to Routes.SETTINGS,
        "मदद और समर्थन" to Routes.SETTINGS,
        "गोपनीयता नीति" to Routes.SETTINGS,
        "ऐप के बारे में" to Routes.SETTINGS
    )

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 48.dp, bottom = 110.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text("👤", style = MaterialTheme.typography.displaySmall)
                    Text(prefs.userName.ifBlank { "Writer User" },
                        style = MaterialTheme.typography.titleLarge, color = glass.textPrimary)
                }
            }
            item {
                GlassCard(Modifier.fillMaxWidth()) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        Stat("${docs.size}", "दस्तावेज़")
                        Stat("${docs.count { it.status.name == "COMPLETED" }}", "पूर्ण")
                        Stat("$favorites", "पसंदीदा")
                    }
                }
            }
            items(menu) { (label, route) ->
                GlassCard(Modifier.fillMaxWidth(), onClick = { onNavigate(route) }) {
                    Text(label, color = glass.textPrimary, style = MaterialTheme.typography.titleMedium)
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
private fun Stat(value: String, label: String) {
    val glass = WriterTheme.colors
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.titleLarge, color = glass.textPrimary)
        Text(label, style = MaterialTheme.typography.labelSmall, color = glass.textMuted)
    }
}
