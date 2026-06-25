package com.neepan.boockai.core.domain

import com.neepan.boockai.core.model.Book
import com.neepan.boockai.core.model.BookId
import kotlinx.coroutines.flow.Flow

/**
 * Contract for accessing the local book data store.
 * Implementations handle Room persistence and JSON seeding.
 */
interface BookLocalDataSource {

    /** Seed the database from bundled JSON if it's empty (first launch). */
    suspend fun seedIfEmpty()

    /** Observe all books in the library as a reactive stream. */
    fun getAllBooks(): Flow<List<Book>>

    /** Get a single book by its ID. Returns null if not found. */
    suspend fun getBookById(id: BookId): Book?

    /** Insert or update a book in the local store. */
    suspend fun upsertBook(book: Book)

    /** Remove a book from saved/favorites. */
    suspend fun removeBook(bookId: BookId)

    /** Check if a book is saved to the user's library. */
    suspend fun isBookSaved(bookId: BookId): Boolean

    /** Save a book to favorites. */
    suspend fun saveToFavorites(bookId: BookId)

    /** Remove a book from favorites. */
    suspend fun removeFromFavorites(bookId: BookId)
}
