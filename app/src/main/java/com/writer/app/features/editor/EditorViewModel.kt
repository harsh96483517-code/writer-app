package com.writer.app.features.editor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.writer.app.di.AppContainer
import com.writer.app.model.ApplicantDetails
import com.writer.app.model.Document
import com.writer.app.model.DocumentStatus
import com.writer.app.model.Language
import com.writer.app.model.Recipient
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** Owns the editing session for one document, including debounced autosave. */
class EditorViewModel(
    private val container: AppContainer,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val docId: String = savedStateHandle.get<String>("docId") ?: "new"
    private val templateId: String? = savedStateHandle.get<String>("templateId")?.takeIf { it != "none" }

    private val _document = MutableStateFlow(Document())
    val document: StateFlow<Document> = _document.asStateFlow()

    private val _savedTick = MutableStateFlow(0L)
    val savedTick: StateFlow<Long> = _savedTick.asStateFlow()

    private var autosaveJob: Job? = null
    private var loaded = false

    init {
        viewModelScope.launch {
            val existing = if (docId != "new") container.documents.get(docId) else null
            _document.value = existing ?: newFromTemplate()
            loaded = true
        }
    }

    private fun newFromTemplate(): Document {
        val template = templateId?.let { container.templates.template(it) }
        return if (template == null) {
            Document(title = "बिना शीर्षक")
        } else {
            val lang = template.languageSupport.firstOrNull() ?: Language.HINDI
            Document(
                title = template.title(lang),
                templateId = template.id,
                language = lang,
                recipient = Recipient(designation = template.defaultRecipientDesignation),
                subject = template.defaultSubject(lang),
                body = template.defaultContent(lang)
            )
        }
    }

    fun updateTitle(title: String) = mutate { it.copy(title = title) }
    fun updateRecipient(recipient: Recipient) = mutate { it.copy(recipient = recipient) }
    fun updateApplicant(applicant: ApplicantDetails) = mutate { it.copy(applicant = applicant) }
    fun updateSubject(subject: String) = mutate { it.copy(subject = subject) }
    fun updateBody(body: String) = mutate { it.copy(body = body) }
    fun updateClosing(closing: String) = mutate { it.copy(closing = closing) }
    fun updateLanguage(language: Language) = mutate { it.copy(language = language) }
    fun updateBold(bold: Boolean) = mutate { it.copy(formatting = it.formatting.copy(bold = bold)) }
    fun updateItalic(italic: Boolean) = mutate { it.copy(formatting = it.formatting.copy(italic = italic)) }
    fun updateFontSize(pt: Float) = mutate { it.copy(formatting = it.formatting.copy(fontSizePt = pt)) }
    fun updateLineSpacing(v: Float) = mutate { it.copy(formatting = it.formatting.copy(lineSpacing = v)) }

    private inline fun mutate(block: (Document) -> Document) {
        _document.value = block(_document.value)
        scheduleAutosave()
    }

    /** Set from the screen based on user preferences. */
    var autosaveEnabled: Boolean = true

    private fun scheduleAutosave() {
        if (!loaded || !autosaveEnabled) return
        autosaveJob?.cancel()
        autosaveJob = viewModelScope.launch {
            delay(800) // debounce
            saveNow()
        }
    }

    fun saveNow() {
        viewModelScope.launch {
            val doc = _document.value.let {
                it.copy(status = if (it.isReadyToExport()) DocumentStatus.COMPLETED else DocumentStatus.DRAFT)
            }
            _document.value = doc
            container.documents.save(doc)
            _savedTick.value = System.currentTimeMillis()
        }
    }

    fun toggleFavorite() = mutate { it.copy(isFavorite = !it.isFavorite) }
}
