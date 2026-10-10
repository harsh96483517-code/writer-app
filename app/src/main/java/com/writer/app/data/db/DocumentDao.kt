package com.writer.app.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao {

    @Query("SELECT * FROM documents ORDER BY updatedAt DESC")
    fun observeAll(): Flow<List<DocumentEntity>>

    @Query("SELECT * FROM documents WHERE status = 'DRAFT' ORDER BY updatedAt DESC")
    fun observeDrafts(): Flow<List<DocumentEntity>>

    @Query("SELECT * FROM documents WHERE status = 'COMPLETED' ORDER BY updatedAt DESC")
    fun observeCompleted(): Flow<List<DocumentEntity>>

    @Query("SELECT * FROM documents WHERE isFavorite = 1 ORDER BY updatedAt DESC")
    fun observeFavorites(): Flow<List<DocumentEntity>>

    @Query("SELECT * FROM documents WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): DocumentEntity?

    @Query("SELECT * FROM documents ORDER BY updatedAt DESC LIMIT :limit")
    fun observeRecent(limit: Int): Flow<List<DocumentEntity>>

    @Query(
        "SELECT * FROM documents WHERE title LIKE '%' || :query || '%' " +
            "OR subject LIKE '%' || :query || '%' ORDER BY updatedAt DESC"
    )
    fun search(query: String): Flow<List<DocumentEntity>>

    @Upsert
    suspend fun upsert(entity: DocumentEntity)

    @Delete
    suspend fun delete(entity: DocumentEntity)

    @Query("DELETE FROM documents WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM documents")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM documents")
    fun observeCount(): Flow<Int>
}
