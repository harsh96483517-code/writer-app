package com.writer.app.data.templates

import com.writer.app.model.Category

/** Categories shown on the home screen and template library. */
object BuiltInCategories {
    val all = listOf(
        Category("government", "सरकारी आवेदन", "Government Applications", "🏛️", 0xFF3478F6),
        Category("education", "शिक्षा", "Education", "🎓", 0xFF8557FF),
        Category("schemes", "योजनाएँ एवं दस्तावेज़", "Schemes & Documents", "📑", 0xFF48A5FF),
        Category("complaints", "शिकायत पत्र", "Complaints", "⚠️", 0xFFFFBF69),
        Category("banking", "बैंकिंग", "Banking & Finance", "🏦", 0xFF37D6A0),
        Category("employment", "नौकरी / करियर", "Employment", "💼", 0xFFFF647C),
        Category("personal", "व्यक्तिगत पत्र", "Personal Letters", "✉️", 0xFF7C8CFF)
    )
}
