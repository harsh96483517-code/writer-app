package com.writer.app.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.writer.app.design.components.GlassBottomSheet
import com.writer.app.design.components.glassSurface
import com.writer.app.design.theme.WriterTheme

private data class CreateOption(val key: String, val title: String, val desc: String, val emoji: String)

private val options = listOf(
    CreateOption("blank", "Blank Document", "खाली दस्तावेज़ से शुरुआत करें", "🗎"),
    CreateOption("template", "Application Template", "सरकारी या अन्य आवेदन टेम्पलेट", "🏛️"),
    CreateOption("complaint", "Complaint Letter", "शिकायत पत्र तैयार करें", "⚠️"),
    CreateOption("personal", "Personal Letter", "व्यक्तिगत पत्र लिखें", "✉️"),
    CreateOption("recent", "Continue Recent Draft", "हाल का अधूरा ड्राफ्ट खोलें", "⏱️")
)

@Composable
fun CreateSheet(
    onDismiss: () -> Unit,
    onBlank: () -> Unit,
    onBrowseTemplates: () -> Unit,
    onComplaint: () -> Unit,
    onPersonal: () -> Unit,
    onContinueRecent: () -> Unit
) {
    val glass = WriterTheme.colors
    GlassBottomSheet(onDismiss = onDismiss) {
        Text("नया बनाएँ", style = MaterialTheme.typography.titleLarge, color = glass.textPrimary)
        Spacer(Modifier.height(12.dp))
        options.forEach { opt ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .glassSurface(com.writer.app.design.theme.GlassCorners.field)
                    .clickable {
                        when (opt.key) {
                            "blank" -> onBlank()
                            "template" -> onBrowseTemplates()
                            "complaint" -> onComplaint()
                            "personal" -> onPersonal()
                            "recent" -> onContinueRecent()
                        }
                    }
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(opt.emoji, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(opt.title, color = glass.textPrimary, style = MaterialTheme.typography.titleMedium)
                    Text(opt.desc, color = glass.textSecondary, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(Modifier.height(10.dp))
        }
        Spacer(Modifier.height(8.dp))
    }
}
