package com.writer.app.data.repository

import com.writer.app.data.templates.BuiltInCategories
import com.writer.app.data.templates.BuiltInTemplates
import com.writer.app.model.Category
import com.writer.app.model.Language
import com.writer.app.model.Template
import com.writer.app.model.TemplateCategory

/** Provides built-in templates and categories entirely offline. */
class TemplateRepository {

    private val templates: List<Template> = BuiltInTemplates.all
    private val favorites = mutableSetOf<String>()

    fun allTemplates(): List<Template> = templates

    fun categories(): List<Category> = BuiltInCategories.all

    fun templatesIn(category: TemplateCategory): List<Template> =
        templates.filter { it.category == category }

    fun template(id: String): Template? = templates.firstOrNull { it.id == id }

    fun popular(): List<Template> = templates.take(10)

    fun recentlyAdded(): List<Template> = templates.takeLast(10).reversed()

    fun favorites(): List<Template> = templates.filter { it.id in favorites }

    fun toggleFavorite(id: String) {
        if (!favorites.add(id)) favorites.remove(id)
    }

    fun isFavorite(id: String) = id in favorites

    fun search(query: String, language: Language): List<Template> {
        if (query.isBlank()) return templates
        val q = query.trim().lowercase()
        return templates.filter { t ->
            t.titleHindi.lowercase().contains(q) ||
                t.titleEnglish.lowercase().contains(q) ||
                t.descriptionHindi.lowercase().contains(q) ||
                t.descriptionEnglish.lowercase().contains(q) ||
                t.keywords.any { it.lowercase().contains(q) }
        }
    }
}
