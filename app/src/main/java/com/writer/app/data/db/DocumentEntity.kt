package com.writer.app.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.writer.app.model.ApplicantDetails
import com.writer.app.model.Document
import com.writer.app.model.DocumentFormatting
import com.writer.app.model.DocumentStatus
import com.writer.app.model.Language
import com.writer.app.model.Recipient

@Entity(tableName = "documents")
data class DocumentEntity(
    @PrimaryKey val id: String,
    val title: String,
    val templateId: String?,
    val language: Language,
    @Embedded(prefix = "recipient_") val recipient: Recipient,
    @Embedded(prefix = "applicant_") val applicant: ApplicantDetails,
    val subject: String,
    val body: String,
    val closing: String,
    val createdAt: Long,
    val updatedAt: Long,
    val status: DocumentStatus,
    val isFavorite: Boolean,
    @Embedded(prefix = "fmt_") val formatting: DocumentFormatting
) {
    fun toDomain() = Document(
        id = id,
        title = title,
        templateId = templateId,
        language = language,
        recipient = recipient,
        applicant = applicant,
        subject = subject,
        body = body,
        closing = closing,
        createdAt = createdAt,
        updatedAt = updatedAt,
        status = status,
        isFavorite = isFavorite,
        formatting = formatting
    )

    companion object {
        fun fromDomain(d: Document) = DocumentEntity(
            id = d.id,
            title = d.title,
            templateId = d.templateId,
            language = d.language,
            recipient = d.recipient,
            applicant = d.applicant,
            subject = d.subject,
            body = d.body,
            closing = d.closing,
            createdAt = d.createdAt,
            updatedAt = d.updatedAt,
            status = d.status,
            isFavorite = d.isFavorite,
            formatting = d.formatting
        )
    }
}
