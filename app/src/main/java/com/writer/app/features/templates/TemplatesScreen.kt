package com.writer.app.features.templates

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.writer.app.data.templates.BuiltInCategories
import com.writer.app.design.components.*
import com.writer.app.design.theme.PrimaryBlue
import com.writer.app.design.theme.WriterTheme
import com.writer.app.di.AppContainer
import com.writer.app.model.Language
import com.writer.app.model.UserPreferences
import com.writer.app.navigation.Routes
import com.writer.app.ui.WriterBottomBar

@Composable
fun TemplatesScreen(
    container: AppContainer,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    onCreateClick: () -> Unit
) {
    val glass = WriterTheme.colors
    val prefs by container.prefs.preferences.collectAsState(initial = UserPreferences())
    var query by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    val chips = listOf("सभी") + BuiltInCategories.all.map { it.title(prefs.language) }

    val templates = remember(query, selectedCategory, prefs.language) {
        val bySearch = container.templates.search(query, prefs.language)
        if (selectedCategory == null) bySearch
        else {
            val cat = BuiltInCategories.all.firstOrNull { it.title(prefs.language) == selectedCategory }
            if (cat == null) bySearch
            else bySearch.filter { it.category.key == cat.id }
        }
    }

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 48.dp, bottom = 110.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("टेम्पलेट्स", style = MaterialTheme.typography.headlineMedium, color = glass.textPrimary)
            }
            item {
                GlassSearchBar(value = query, onValueChange = { query = it }, placeholder = "टेम्पलेट खोजें...")
            }
            item {
                Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    chips.forEach { chip ->
                        val selected = (chip == "सभी" && selectedCategory == null) || chip == selectedCategory
                        Box(Modifier) {
                            GlassSegmentedControl(
                                options = listOf(chip),
                                selectedIndex = if (selected) 0 else -1,
                                onSelect = { selectedCategory = if (chip == "सभी") null else chip }
                            )
                        }
                    }
                }
            }
            if (templates.isEmpty()) {
                item { GlassEmptyState("🔍", "कोई टेम्पलेट नहीं मिला। दूसरे शब्दों से खोजें।") }
            } else {
                items(templates) { template ->
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
        }
        WriterBottomBar(
            currentRoute = currentRoute,
            onSelect = { if (it == "create") onCreateClick() else onNavigate(it) },
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)
        )
    }
}
