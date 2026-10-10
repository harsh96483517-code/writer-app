package com.writer.app.model

import java.util.UUID

/**
 * The core document model. A document is either created blank or derived from
 * a [Template]. All personal data stays on-device.
 */
data class Document(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val templateId: String? = null,
    val language: Language = Language.HINDI,
    val recipient: Recipient = Recipient(),
    val applicant: ApplicantDetails = ApplicantDetails(),
    val subject: String = "",
    val body: String = "",
    val closing: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val status: DocumentStatus = DocumentStatus.DRAFT,
    val isFavorite: Boolean = false,
    val formatting: DocumentFormatting = DocumentFormatting()
) {
    /** Simple completeness heuristic used to mark a document "पूर्ण". */
    fun isReadyToExport(): Boolean =
        (recipient.designation.isNotBlank() || recipient.officeName.isNotBlank()) &&
            subject.isNotBlank() && body.isNotBlank()
}
