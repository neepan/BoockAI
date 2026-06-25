package com.neepan.boockai.core.model

import kotlinx.serialization.Serializable

/**
 * Core domain model representing a book in the library.
 */
@Serializable
data class Book(
    val id: BookId,
    val title: String,
    val author: String,
    val summary: String,
    val coverUrl: String,
    val genre: String,
    val rating: Float,
    val estimatedReadTime: String,
    val chapters: List<Chapter> = emptyList()
)

/**
 * A single chapter within a book.
 */
@Serializable
data class Chapter(
    val id: Int,
    val title: String,
    val content: String
)
