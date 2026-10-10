package com.writer.app.features.files

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.writer.app.common.DateUtils
import com.writer.app.design.components.*
import com.writer.app.design.theme.WriterTheme
import com.writer.app.di.AppContainer
import com.writer.app.model.Document
import com.writer.app.navigation.Routes
import com.writer.app.ui.WriterBottomBar
import kotlinx.coroutines.launch

private enum class SortMode { DATE, NAME, TYPE }

@Composable
fun FilesScreen(
    container: AppContainer,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    onCreateClick: () -> Unit
) {
    val glass = WriterTheme.colors
    val scope = rememberCoroutineScope()
    val all by container.documents.observeAll().collectAsState(initial = emptyList())
    var tab by remember { mutableIntStateOf(0) }
    var query by remember { mutableStateOf("") }
    var sort by remember { mutableStateOf(SortMode.DATE) }
    var pendingDelete by remember { mutableStateOf<Document?>(null) }

    val tabs = listOf("सभी", "ड्राफ्ट", "पूर्ण", "पसंदीदा")
    val filtered = remember(all, tab, query, sort) {
        val base = all.filter { d ->
            when (tab) {
                1 -> d.status.name == "DRAFT"
                2 -> d.status.name == "COMPLETED"
                3 -> d.isFavorite
                else -> true
            }
        }.filter {
            query.isBlank() || it.title.contains(query, true) || it.subject.contains(query, true)
        }
        when (sort) {
            SortMode.DATE -> base.sortedByDescending { it.updatedAt }
            SortMode.NAME -> base.sortedBy { it.title }
            SortMode.TYPE -> base.sortedBy { it.templateId ?: "" }
        }
    }

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 48.dp, bottom = 110.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Text("मेरी फाइलें", style = MaterialTheme.typography.headlineMedium, color = glass.textPrimary) }
            item { GlassSearchBar(query, { query = it }, placeholder = "फ़ाइलें खोजें...") }
            item {
                GlassSegmentedControl(tabs, tab, { tab = it })
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(SortMode.DATE to "दिनांक", SortMode.NAME to "नाम", SortMode.TYPE to "प्रकार").forEach { (m, label) ->
                        Box {
                            GlassSegmentedControl(
                                options = listOf(label),
                                selectedIndex = if (sort == m) 0 else -1,
                                onSelect = { sort = m }
                            )
                        }
                    }
                }
            }
            if (filtered.isEmpty()) {
                item {
                    GlassEmptyState("🗂️", "अभी कोई दस्तावेज़ नहीं है",
                        actionText = "पहला दस्तावेज़ बनाएं", onAction = onCreateClick)
                }
            } else {
                items(filtered, key = { it.id }) { doc ->
                    GlassDocumentCard(
                        title = doc.title.ifBlank { "बिना शीर्षक" },
                        subtitle = DateUtils.display(doc.updatedAt),
                        status = if (doc.status.name == "COMPLETED") "पूर्ण" else "ड्राफ्ट",
                        onClick = { onNavigate(Routes.editor(doc.id, doc.templateId ?: "none")) },
                        onMore = { pendingDelete = doc }
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

    pendingDelete?.let { doc ->
        GlassDialog(
            title = "दस्तावेज़ हटाएँ?",
            message = "\"${doc.title}\" को हटाया जाएगा। यह वापस नहीं आएगा।",
            confirmText = "हटाएँ",
            dismissText = "रद्द करें",
            onConfirm = {
                scope.launch { container.documents.deleteById(doc.id) }
                pendingDelete = null
            },
            onDismiss = { pendingDelete = null }
        )
    }
}
