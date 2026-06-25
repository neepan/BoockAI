package com.neepan.boockai.core.model

/**
 * Tracks a user's reading progress within a specific book.
 */
data class ReadingProgress(
    val bookId: BookId,
    val chapterIndex: Int = 0,
    val scrollPosition: Float = 0f,
    val progressPercent: Float = 0f
)
