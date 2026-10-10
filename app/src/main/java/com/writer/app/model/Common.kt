package com.writer.app.model

enum class Language { HINDI, ENGLISH;
    val code: String get() = if (this == HINDI) "hi" else "en"
    companion object {
        fun fromCode(code: String) = if (code.startsWith("hi")) HINDI else ENGLISH
    }
}

enum class DocumentStatus { DRAFT, COMPLETED }

enum class TemplateCategory(val key: String) {
    GOVERNMENT("government"),
    EDUCATION("education"),
    SCHEMES("schemes"),
    COMPLAINTS("complaints"),
    BANKING("banking"),
    EMPLOYMENT("employment"),
    PERSONAL("personal")
}
