package com.neepan.boockai.core.data.local

import com.neepan.boockai.core.model.Book
import com.neepan.boockai.core.model.BookId
import com.neepan.boockai.core.model.Chapter
import com.neepan.boockai.core.model.ReadingProgress
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

/**
 * Mappers between Room entities and domain models.
 * Extension functions live alongside the entity definitions (data layer).
 */

private val json = Json { ignoreUnknownKeys = true }

fun BookEntity.toBook(): Book = Book(
    id = BookId(id),
    title = title,
    author = author,
    summary = summary,
    coverUrl = coverUrl,
    genre = genre,
    rating = rating,
    estimatedReadTime = estimatedReadTime,
    chapters = try {
        json.decodeFromString<List<Chapter>>(chaptersJson)
    } catch (_: Exception) {
        emptyList()
    }
)

fun Book.toEntity(isSaved: Boolean = false): BookEntity = BookEntity(
    id = id.value,
    title = title,
    author = author,
    summary = summary,
    coverUrl = coverUrl,
    genre = genre,
    rating = rating,
    estimatedReadTime = estimatedReadTime,
    chaptersJson = json.encodeToString(chapters),
    isSaved = isSaved
)

fun ReadingProgressEntity.toProgress(): ReadingProgress = ReadingProgress(
    bookId = BookId(bookId),
    chapterIndex = chapterIndex,
    scrollPosition = scrollPosition,
    progressPercent = progressPercent
)

fun ReadingProgress.toEntity(): ReadingProgressEntity = ReadingProgressEntity(
    bookId = bookId.value,
    chapterIndex = chapterIndex,
    scrollPosition = scrollPosition,
    progressPercent = progressPercent
)
