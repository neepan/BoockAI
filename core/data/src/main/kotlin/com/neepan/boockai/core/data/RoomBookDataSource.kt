package com.neepan.boockai.core.data

import com.neepan.boockai.core.data.json.BookJsonParser
import com.neepan.boockai.core.data.local.BookDao
import com.neepan.boockai.core.data.local.toBook
import com.neepan.boockai.core.data.local.toEntity
import com.neepan.boockai.core.domain.BookLocalDataSource
import com.neepan.boockai.core.model.Book
import com.neepan.boockai.core.model.BookId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Room-backed implementation of [BookLocalDataSource].
 * Named for what makes it unique (Room) — not suffixed with Impl.
 */
@Singleton
class RoomBookDataSource @Inject constructor(
    private val bookDao: BookDao,
    private val bookJsonParser: BookJsonParser
) : BookLocalDataSource {

    /**
     * Seeds the database from the JSON asset if empty.
     * Called once during app initialization.
     */
    override suspend fun seedIfEmpty() {
        if (bookDao.getBookCount() == 0) {
            val books = bookJsonParser.parseBooksFromAssets()
            bookDao.insertAll(books.map { it.toEntity() })
        }
    }

    override fun getAllBooks(): Flow<List<Book>> {
        return bookDao.observeAllBooks().map { entities ->
            entities.map { it.toBook() }
        }
    }

    override suspend fun getBookById(id: BookId): Book? {
        return bookDao.getBookById(id.value)?.toBook()
    }

    override suspend fun upsertBook(book: Book) {
        val existingEntity = bookDao.getBookById(book.id.value)
        bookDao.upsertBook(book.toEntity(isSaved = existingEntity?.isSaved ?: false))
    }

    override suspend fun removeBook(bookId: BookId) {
        bookDao.deleteBook(bookId.value)
    }

    override suspend fun isBookSaved(bookId: BookId): Boolean {
        return bookDao.isBookSaved(bookId.value)
    }

    override suspend fun saveToFavorites(bookId: BookId) {
        bookDao.saveToFavorites(bookId.value)
    }

    override suspend fun removeFromFavorites(bookId: BookId) {
        bookDao.removeFromFavorites(bookId.value)
    }
}
