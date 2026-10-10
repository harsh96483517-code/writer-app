package com.writer.app.features.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.writer.app.design.components.GlassButton
import com.writer.app.design.components.GlassSegmentedControl
import com.writer.app.design.theme.WriterTheme

private data class Slide(val title: String, val desc: String, val emoji: String)

private val slides = listOf(
    Slide("Write Smarter", "कुछ ही मिनटों में बेहतरीन पत्र और आवेदन तैयार करें।", "✍️"),
    Slide("Ready-to-Use Templates", "उपयोगी हिंदी और अंग्रेज़ी टेम्पलेट्स में से चुनें।", "📚"),
    Slide("Export and Print", "दस्तावेज़ को PDF में सेव करें और A4 में प्रिंट करें।", "🖨️")
)

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val glass = WriterTheme.colors
    var index by remember { mutableIntStateOf(0) }
    val slide = slides[index]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                "Skip",
                color = glass.textSecondary,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(glass.glassSurface)
                    .clickable(onClick = onFinish)
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            )
        }

        Spacer(Modifier.weight(1f))
        Text(slide.emoji, style = MaterialTheme.typography.displaySmall)
        Spacer(Modifier.height(20.dp))
        Text(
            slide.title,
            style = MaterialTheme.typography.headlineMedium,
            color = glass.textPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(10.dp))
        Text(
            slide.desc,
            style = MaterialTheme.typography.bodyLarge,
            color = glass.textSecondary,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.weight(1f))

        GlassSegmentedControl(
            options = slides.indices.map { "•" },
            selectedIndex = index,
            onSelect = { index = it }
        )
        Spacer(Modifier.height(20.dp))
        GlassButton(
            text = if (index == slides.lastIndex) "Get Started" else "Next →",
            onClick = { if (index == slides.lastIndex) onFinish() else index++ },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
