package com.writer.app.model

/**
 * A reusable letter template. `defaultContent` holds the pre-filled Hindi /
 * English body skeleton that a new document starts from.
 */
data class Template(
    val id: String,
    val titleHindi: String,
    val titleEnglish: String,
    val descriptionHindi: String,
    val descriptionEnglish: String,
    val category: TemplateCategory,
    val languageSupport: List<Language>,
    val defaultSubjectHindi: String = "",
    val defaultSubjectEnglish: String = "",
    val defaultRecipientDesignation: String = "",
    val defaultContentHindi: String = "",
    val defaultContentEnglish: String = "",
    val keywords: List<String> = emptyList(),
    val isBuiltIn: Boolean = true
) {
    fun title(language: Language) = if (language == Language.HINDI) titleHindi else titleEnglish
    fun description(language: Language) = if (language == Language.HINDI) descriptionHindi else descriptionEnglish
    fun defaultSubject(language: Language) = if (language == Language.HINDI) defaultSubjectHindi else defaultSubjectEnglish
    fun defaultContent(language: Language) = if (language == Language.HINDI) defaultContentHindi else defaultContentEnglish
}

data class Category(
    val id: String,
    val titleHindi: String,
    val titleEnglish: String,
    val emoji: String,
    val accentColorHex: Long
) {
    fun title(language: Language) = if (language == Language.HINDI) titleHindi else titleEnglish
}
