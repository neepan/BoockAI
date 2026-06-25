package com.neepan.boockai.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.neepan.boockai.core.model.Book
import com.neepan.boockai.core.model.BookId
import com.neepan.boockai.core.model.Chapter

/**
 * Room entity representing a book stored locally.
 * Chapters are stored as a JSON string for simplicity.
 */
@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val author: String,
    val summary: String,
    val coverUrl: String,
    val genre: String,
    val rating: Float,
    val estimatedReadTime: String,
    val chaptersJson: String,
    val isSaved: Boolean = false
)

/**
 * Room entity for tracking reading progress per book.
 */
@Entity(tableName = "reading_progress")
data class ReadingProgressEntity(
    @PrimaryKey val bookId: Int,
    val chapterIndex: Int = 0,
    val scrollPosition: Float = 0f,
    val progressPercent: Float = 0f,
    val lastReadTimestamp: Long = System.currentTimeMillis()
)
