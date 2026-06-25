package com.neepan.boockai.feature.library

import com.neepan.boockai.core.domain.BookLocalDataSource
import com.neepan.boockai.core.model.Book
import com.neepan.boockai.core.model.BookId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * In-memory fake for BookLocalDataSource — fakes over mocks per testing skill.
 */
class FakeBookLocalDataSource : BookLocalDataSource {

    private val books = MutableStateFlow<List<Book>>(emptyList())
    private val savedIds = mutableSetOf<Int>()

    fun emitBooks(list: List<Book>) {
        books.value = list
    }

    override suspend fun seedIfEmpty() { /* no-op in tests */ }

    override fun getAllBooks(): Flow<List<Book>> = books

    override suspend fun getBookById(id: BookId): Book? =
        books.value.find { it.id == id }

    override suspend fun upsertBook(book: Book) {
        val current = books.value.toMutableList()
        current.removeAll { it.id == book.id }
        current.add(book)
        books.value = current
    }

    override suspend fun removeBook(bookId: BookId) {
        books.value = books.value.filter { it.id != bookId }
    }

    override suspend fun isBookSaved(bookId: BookId): Boolean =
        bookId.value in savedIds

    override suspend fun saveToFavorites(bookId: BookId) {
        savedIds.add(bookId.value)
    }

    override suspend fun removeFromFavorites(bookId: BookId) {
        savedIds.remove(bookId.value)
    }
}
