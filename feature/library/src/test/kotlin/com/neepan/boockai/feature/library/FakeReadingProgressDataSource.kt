package com.neepan.boockai.feature.library

import com.neepan.boockai.core.domain.ReadingProgressDataSource
import com.neepan.boockai.core.model.BookId
import com.neepan.boockai.core.model.ReadingProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * In-memory fake for ReadingProgressDataSource.
 */
class FakeReadingProgressDataSource : ReadingProgressDataSource {

    private val progressMap = mutableMapOf<Int, ReadingProgress>()
    private val progressFlows = mutableMapOf<Int, MutableStateFlow<ReadingProgress?>>()
    private val allProgressFlow = MutableStateFlow<List<ReadingProgress>>(emptyList())

    fun setProgress(bookId: BookId, progress: ReadingProgress) {
        progressMap[bookId.value] = progress
        progressFlows.getOrPut(bookId.value) { MutableStateFlow(null) }.value = progress
        updateAllProgressFlow()
    }

    override suspend fun getProgress(bookId: BookId): ReadingProgress? =
        progressMap[bookId.value]

    override suspend fun saveProgress(progress: ReadingProgress) {
        progressMap[progress.bookId.value] = progress
        progressFlows.getOrPut(progress.bookId.value) { MutableStateFlow(null) }.value = progress
        updateAllProgressFlow()
    }

    private fun updateAllProgressFlow() {
        allProgressFlow.value = progressMap.values.toList()
    }

    override suspend fun getLastReadBookId(): BookId? =
        progressMap.values.maxByOrNull { it.progressPercent }?.bookId

    override fun observeProgress(bookId: BookId): Flow<ReadingProgress?> =
        progressFlows.getOrPut(bookId.value) { MutableStateFlow(progressMap[bookId.value]) }

    override fun observeAllProgress(): Flow<List<ReadingProgress>> = allProgressFlow
}
