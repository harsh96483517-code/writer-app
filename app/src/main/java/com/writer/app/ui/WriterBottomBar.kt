package com.writer.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Article
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import com.writer.app.design.components.GlassBottomNavigation
import com.writer.app.design.components.NavItem
import com.writer.app.navigation.Routes

@Composable
fun WriterBottomBar(
    currentRoute: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavItem(Routes.HOME, "होम", { Icon(Icons.Default.Home, null, tint = Color.White) }),
        NavItem(Routes.TEMPLATES, "टेम्पलेट्स", { Icon(Icons.Default.Article, null, tint = Color.White) }),
        NavItem("create", "बनाएँ", { Icon(Icons.Default.Add, "बनाएँ", tint = Color.White) }),
        NavItem(Routes.FILES, "फाइलें", { Icon(Icons.Default.Folder, null, tint = Color.White) }),
        NavItem(Routes.PROFILE, "प्रोफ़ाइल", { Icon(Icons.Default.Person, null, tint = Color.White) })
    )
    GlassBottomNavigation(
        items = items,
        currentRoute = currentRoute,
        centerRoute = "create",
        onSelect = onSelect,
        modifier = modifier
    )
}
