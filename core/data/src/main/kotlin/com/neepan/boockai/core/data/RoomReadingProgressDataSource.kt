package com.neepan.boockai.core.data

import com.neepan.boockai.core.data.local.ReadingProgressDao
import com.neepan.boockai.core.data.local.toEntity
import com.neepan.boockai.core.data.local.toProgress
import com.neepan.boockai.core.domain.ReadingProgressDataSource
import com.neepan.boockai.core.model.BookId
import com.neepan.boockai.core.model.ReadingProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Room-backed implementation of [ReadingProgressDataSource].
 */
@Singleton
class RoomReadingProgressDataSource @Inject constructor(
    private val progressDao: ReadingProgressDao
) : ReadingProgressDataSource {

    override suspend fun getProgress(bookId: BookId): ReadingProgress? {
        return progressDao.getProgress(bookId.value)?.toProgress()
    }

    override suspend fun saveProgress(progress: ReadingProgress) {
        progressDao.upsertProgress(progress.toEntity())
    }

    override suspend fun getLastReadBookId(): BookId? {
        return progressDao.getLastReadProgress()?.let { BookId(it.bookId) }
    }

    override fun observeProgress(bookId: BookId): Flow<ReadingProgress?> {
        return progressDao.observeProgress(bookId.value).map { it?.toProgress() }
    }

    override fun observeAllProgress(): Flow<List<ReadingProgress>> {
        return progressDao.observeAllProgress().map { list -> list.map { it.toProgress() } }
    }
}
