package com.writer.app.data.repository

import com.writer.app.data.db.DocumentDao
import com.writer.app.data.db.DocumentEntity
import com.writer.app.model.Document
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/** Single source of truth for saved documents. */
class DocumentRepository(private val dao: DocumentDao) {

    fun observeAll(): Flow<List<Document>> = dao.observeAll().map { it.map(DocumentEntity::toDomain) }
    fun observeDrafts(): Flow<List<Document>> = dao.observeDrafts().map { it.map(DocumentEntity::toDomain) }
    fun observeCompleted(): Flow<List<Document>> = dao.observeCompleted().map { it.map(DocumentEntity::toDomain) }
    fun observeFavorites(): Flow<List<Document>> = dao.observeFavorites().map { it.map(DocumentEntity::toDomain) }
    fun observeRecent(limit: Int = 5): Flow<List<Document>> =
        dao.observeRecent(limit).map { it.map(DocumentEntity::toDomain) }

    fun search(query: String): Flow<List<Document>> =
        dao.search(query).map { it.map(DocumentEntity::toDomain) }

    suspend fun get(id: String): Document? = dao.getById(id)?.toDomain()

    suspend fun save(document: Document) =
        dao.upsert(DocumentEntity.fromDomain(document.copy(updatedAt = System.currentTimeMillis())))

    suspend fun delete(document: Document) = dao.delete(DocumentEntity.fromDomain(document))
    suspend fun deleteById(id: String) = dao.deleteById(id)
    suspend fun deleteAll() = dao.deleteAll()

    suspend fun duplicate(document: Document): Document {
        val copy = document.copy(
            id = java.util.UUID.randomUUID().toString(),
            title = document.title + " (कॉपी)",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            status = com.writer.app.model.DocumentStatus.DRAFT
        )
        dao.upsert(DocumentEntity.fromDomain(copy))
        return copy
    }
}
