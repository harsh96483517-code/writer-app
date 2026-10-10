package com.writer.app.design.components

import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.writer.app.design.theme.GlassCorners
import com.writer.app.design.theme.WriterTheme

/* ------------------------------------------------------------------ */
/*  Shared glass surface modifier                                     */
/* ------------------------------------------------------------------ */

@Composable
fun Modifier.glassSurface(
    shape: Shape = GlassCorners.card,
    elevated: Boolean = false,
    border: Boolean = true
): Modifier {
    val glass = WriterTheme.colors
    val fill = if (elevated) glass.glassSurfaceElevated else glass.glassSurface
    // Alpha must stay within 0f..1f — Compose throws if it is out of range.
    val highlightAlpha = (fill.alpha * 1.15f).coerceIn(0f, 1f)
    return this
        .clip(shape)
        .background(
            brush = Brush.verticalGradient(
                listOf(fill.copy(alpha = highlightAlpha), fill)
            )
        )
        .then(
            if (border) Modifier.border(BorderStroke(1.dp, glass.glassBorder), shape)
            else Modifier
        )
}

/** True specular highlight for a subtle top light sweep. */
@Composable
fun Modifier.specularHighlight(shape: Shape = GlassCorners.card): Modifier {
    val glass = WriterTheme.colors
    return this.background(
        brush = Brush.verticalGradient(
            colors = listOf(Color.White.copy(alpha = 0.10f), Color.Transparent),
            startY = 0f,
            endY = 120f
        ),
        shape = shape
    )
}

/* ------------------------------------------------------------------ */
/*  1. GlassCard                                                      */
/* ------------------------------------------------------------------ */

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    elevated: Boolean = false,
    shape: Shape = GlassCorners.card,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(if (pressed) 0.98f else 1f, tween(150), label = "cardScale")

    Column(
        modifier = modifier
            .scale(scale)
            .glassSurface(shape, elevated)
            .then(
                if (onClick != null)
                    Modifier.clickable(
                        interactionSource = interaction,
                        indication = null,
                        onClick = onClick
                    )
                else Modifier
            )
            .padding(16.dp),
        content = content
    )
}

/* ------------------------------------------------------------------ */
/*  2. GlassButton                                                    */
/* ------------------------------------------------------------------ */

@Composable
fun GlassButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    filled: Boolean = true,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    val glass = WriterTheme.colors
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(if (pressed) 0.96f else 1f, tween(120), label = "btnScale")
    val alpha = if (enabled) 1f else 0.45f

    Box(
        modifier = modifier
            .scale(scale)
            .clip(GlassCorners.button)
            .then(
                if (filled) Modifier.background(glass.accentGradient)
                else Modifier.glassSurface(GlassCorners.button)
            )
            .clickable(
                enabled = enabled,
                interactionSource = interaction,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 22.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            leadingIcon?.invoke()
            Text(
                text = text,
                color = if (filled) Color.White else glass.textPrimary,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

/* ------------------------------------------------------------------ */
/*  3. GlassIconButton                                                */
/* ------------------------------------------------------------------ */

@Composable
fun GlassIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 44.dp,
    icon: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(size)
            .glassSurface(RoundedCornerShape(size / 2))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) { icon() }
}

/* ------------------------------------------------------------------ */
/*  4. GlassSearchBar                                                 */
/* ------------------------------------------------------------------ */

@Composable
fun GlassSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "खोजें..."
) {
    val glass = WriterTheme.colors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .glassSurface(GlassCorners.field, elevated = true)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(Icons.Default.Search, contentDescription = null, tint = glass.textSecondary)
        Box(Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(placeholder, color = glass.textMuted, style = MaterialTheme.typography.bodyMedium)
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = glass.textPrimary),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (value.isNotEmpty()) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Clear",
                tint = glass.textSecondary,
                modifier = Modifier.clickable { onValueChange("") }
            )
        }
    }
}

/* ------------------------------------------------------------------ */
/*  5. GlassTextField                                                 */
/* ------------------------------------------------------------------ */

@Composable
fun GlassTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    singleLine: Boolean = true,
    required: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    minLines: Int = 1,
    isError: Boolean = false,
    supportingText: String? = null
) {
    val glass = WriterTheme.colors
    val borderColor by animateColorAsState(
        if (isError) MaterialTheme.colorScheme.error else glass.glassBorder,
        tween(200), label = "fieldBorder"
    )
    Column(modifier) {
        Text(
            text = if (required) "$label *" else label,
            style = MaterialTheme.typography.labelLarge,
            color = glass.textSecondary,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .clip(GlassCorners.field)
                .background(glass.glassSurface)
                .border(1.dp, borderColor, GlassCorners.field)
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            if (value.isEmpty() && placeholder.isNotEmpty()) {
                Text(placeholder, color = glass.textMuted, style = MaterialTheme.typography.bodyMedium)
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = singleLine,
                minLines = minLines,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = glass.textPrimary),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (supportingText != null) {
            Text(
                supportingText,
                color = if (isError) MaterialTheme.colorScheme.error else glass.textMuted,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
        }
    }
}

/* ------------------------------------------------------------------ */
/*  6. GlassBottomNavigation                                          */
/* ------------------------------------------------------------------ */

data class NavItem(val route: String, val label: String, val icon: @Composable () -> Unit)

@Composable
fun GlassBottomNavigation(
    items: List<NavItem>,
    currentRoute: String?,
    centerRoute: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val glass = WriterTheme.colors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .glassSurface(GlassCorners.cardLarge, elevated = true)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            val selected = item.route == currentRoute
            if (item.route == centerRoute) {
                Box(
                    Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(glass.accentGradient)
                        .clickable { onSelect(item.route) },
                    contentAlignment = Alignment.Center
                ) { item.icon() }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(GlassCorners.chip)
                        .clickable { onSelect(item.route) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    item.icon()
                    Spacer(Modifier.height(2.dp))
                    Text(
                        item.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (selected) MaterialTheme.colorScheme.primary else glass.textMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

/* ------------------------------------------------------------------ */
/*  7. GlassBottomSheet                                               */
/* ------------------------------------------------------------------ */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlassBottomSheet(
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val glass = WriterTheme.colors
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = glass.backgroundSecondary,
        shape = GlassCorners.sheet,
        dragHandle = {
            Box(
                Modifier
                    .padding(top = 12.dp)
                    .size(width = 40.dp, height = 4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(glass.textMuted)
            )
        }
    ) {
        Column(
            Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            content = content
        )
    }
}

/* ------------------------------------------------------------------ */
/*  8. GlassDialog                                                    */
/* ------------------------------------------------------------------ */

@Composable
fun GlassDialog(
    title: String,
    message: String,
    confirmText: String = "ठीक है",
    dismissText: String? = null,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val glass = WriterTheme.colors
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = glass.backgroundSecondary,
        shape = GlassCorners.card,
        title = { Text(title, color = glass.textPrimary) },
        text = { Text(message, color = glass.textSecondary) },
        confirmButton = { TextButton(onClick = onConfirm) { Text(confirmText) } },
        dismissButton = dismissText?.let {
            { TextButton(onClick = onDismiss) { Text(it) } }
        }
    )
}

/* ------------------------------------------------------------------ */
/*  9. GlassSegmentedControl / GlassTabBar                            */
/* ------------------------------------------------------------------ */

@Composable
fun GlassSegmentedControl(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val glass = WriterTheme.colors
    Row(
        modifier = modifier
            .glassSurface(GlassCorners.chip, elevated = true)
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        options.forEachIndexed { index, label ->
            val selected = index == selectedIndex
            Box(
                Modifier
                    .clip(GlassCorners.chip)
                    .then(
                        if (selected) Modifier.background(glass.accentGradient) else Modifier
                    )
                    .clickable { onSelect(index) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    label,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (selected) Color.White else glass.textSecondary
                )
            }
        }
    }
}

/* ------------------------------------------------------------------ */
/*  10. GlassToolbar                                                  */
/* ------------------------------------------------------------------ */

@Composable
fun GlassToolbar(
    title: String,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier
) {
    val glass = WriterTheme.colors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .glassSurface(GlassCorners.card, elevated = true)
            .padding(horizontal = 8.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBack != null) {
            IconButton(onClick = onBack) {
                Text("←", color = glass.textPrimary, style = MaterialTheme.typography.titleLarge)
            }
        }
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            color = glass.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        actions()
    }
}

/* ------------------------------------------------------------------ */
/*  11. GlassFloatingActionButton                                     */
/* ------------------------------------------------------------------ */

@Composable
fun GlassFloatingActionButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val glass = WriterTheme.colors
    Box(
        modifier = modifier
            .size(60.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(glass.accentGradient)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) { icon() }
}

/* ------------------------------------------------------------------ */
/*  12. GlassCategoryCard                                             */
/* ------------------------------------------------------------------ */

@Composable
fun GlassCategoryCard(
    title: String,
    emoji: String,
    accent: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val glass = WriterTheme.colors
    Column(
        modifier = modifier
            .width(120.dp)
            .glassSurface(GlassCorners.card)
            .clickable(onClick = onClick)
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(accent.copy(alpha = 0.22f)),
            contentAlignment = Alignment.Center
        ) { Text(emoji, style = MaterialTheme.typography.titleLarge) }
        Text(
            title,
            style = MaterialTheme.typography.labelLarge,
            color = glass.textPrimary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/* ------------------------------------------------------------------ */
/*  13. GlassTemplateCard                                             */
/* ------------------------------------------------------------------ */

@Composable
fun GlassTemplateCard(
    title: String,
    description: String,
    accent: Color,
    languages: String,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val glass = WriterTheme.colors
    GlassCard(modifier = modifier.fillMaxWidth(), onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(accent.copy(alpha = 0.22f)),
                contentAlignment = Alignment.Center
            ) { Text("📄", style = MaterialTheme.typography.titleMedium) }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    color = glass.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = glass.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = onFavoriteToggle) {
                    Text(if (isFavorite) "♥" else "♡", color = if (isFavorite) MaterialTheme.colorScheme.error else glass.textMuted)
                }
                Text(languages, style = MaterialTheme.typography.labelSmall, color = glass.textMuted)
            }
        }
    }
}

/* ------------------------------------------------------------------ */
/*  14. GlassDocumentCard                                             */
/* ------------------------------------------------------------------ */

@Composable
fun GlassDocumentCard(
    title: String,
    subtitle: String,
    status: String,
    onClick: () -> Unit,
    onMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    val glass = WriterTheme.colors
    GlassCard(modifier = modifier.fillMaxWidth(), onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) { Text("🗎", style = MaterialTheme.typography.titleMedium) }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    color = glass.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(subtitle, style = MaterialTheme.typography.labelSmall, color = glass.textMuted)
            }
            Text(
                status,
                style = MaterialTheme.typography.labelSmall,
                color = if (status == "पूर्ण") MaterialTheme.colorScheme.tertiary else glass.textSecondary
            )
            IconButton(onClick = onMore) { Text("⋮", color = glass.textSecondary) }
        }
    }
}

/* ------------------------------------------------------------------ */
/*  15. GlassToast                                                    */
/* ------------------------------------------------------------------ */

@Composable
fun GlassToast(message: String, modifier: Modifier = Modifier) {
    val glass = WriterTheme.colors
    Box(
        modifier = modifier
            .glassSurface(GlassCorners.chip, elevated = true)
            .padding(horizontal = 18.dp, vertical = 12.dp)
    ) {
        Text(message, color = glass.textPrimary, style = MaterialTheme.typography.bodyMedium)
    }
}

/* ------------------------------------------------------------------ */
/*  16. GlassLoadingIndicator                                         */
/* ------------------------------------------------------------------ */

@Composable
fun GlassLoadingIndicator(label: String = "कृपया प्रतीक्षा करें...") {
    val glass = WriterTheme.colors
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        Text(label, color = glass.textSecondary, style = MaterialTheme.typography.bodyMedium)
    }
}

/* ------------------------------------------------------------------ */
/*  17. GlassEmptyState                                               */
/* ------------------------------------------------------------------ */

@Composable
fun GlassEmptyState(
    emoji: String,
    title: String,
    actionText: String? = null,
    onAction: (() -> Unit)? = null
) {
    val glass = WriterTheme.colors
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(32.dp)
    ) {
        Text(emoji, style = MaterialTheme.typography.displaySmall)
        Text(title, color = glass.textSecondary, style = MaterialTheme.typography.bodyLarge)
        if (actionText != null && onAction != null) {
            GlassButton(text = actionText, onClick = onAction)
        }
    }
}
