package com.neepan.boockai.core.domain

import com.neepan.boockai.core.model.BookId
import com.neepan.boockai.core.model.ReadingProgress
import kotlinx.coroutines.flow.Flow

/**
 * Contract for reading progress persistence.
 */
interface ReadingProgressDataSource {

    /** Get progress for a specific book. */
    suspend fun getProgress(bookId: BookId): ReadingProgress?

    /** Save or update reading progress. */
    suspend fun saveProgress(progress: ReadingProgress)

    /** Get the ID of the last book that was being read. */
    suspend fun getLastReadBookId(): BookId?

    /** Observe progress for a specific book reactively. */
    fun observeProgress(bookId: BookId): Flow<ReadingProgress?>

    /** Observe all progress reactively. */
    fun observeAllProgress(): Flow<List<ReadingProgress>>
}
