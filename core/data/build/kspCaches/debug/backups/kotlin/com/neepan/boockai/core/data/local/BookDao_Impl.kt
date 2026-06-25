package com.neepan.boockai.core.`data`.local

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class BookDao_Impl(
  __db: RoomDatabase,
) : BookDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfBookEntity: EntityInsertAdapter<BookEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfBookEntity = object : EntityInsertAdapter<BookEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `books` (`id`,`title`,`author`,`summary`,`coverUrl`,`genre`,`rating`,`estimatedReadTime`,`chaptersJson`,`isSaved`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BookEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.author)
        statement.bindText(4, entity.summary)
        statement.bindText(5, entity.coverUrl)
        statement.bindText(6, entity.genre)
        statement.bindDouble(7, entity.rating.toDouble())
        statement.bindText(8, entity.estimatedReadTime)
        statement.bindText(9, entity.chaptersJson)
        val _tmp: Int = if (entity.isSaved) 1 else 0
        statement.bindLong(10, _tmp.toLong())
      }
    }
  }

  public override suspend fun upsertBook(book: BookEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBookEntity.insert(_connection, book)
  }

  public override suspend fun insertAll(books: List<BookEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBookEntity.insert(_connection, books)
  }

  public override fun observeAllBooks(): Flow<List<BookEntity>> {
    val _sql: String = "SELECT * FROM books ORDER BY title ASC"
    return createFlow(__db, false, arrayOf("books")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSummary: Int = getColumnIndexOrThrow(_stmt, "summary")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfEstimatedReadTime: Int = getColumnIndexOrThrow(_stmt, "estimatedReadTime")
        val _columnIndexOfChaptersJson: Int = getColumnIndexOrThrow(_stmt, "chaptersJson")
        val _columnIndexOfIsSaved: Int = getColumnIndexOrThrow(_stmt, "isSaved")
        val _result: MutableList<BookEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSummary: String
          _tmpSummary = _stmt.getText(_columnIndexOfSummary)
          val _tmpCoverUrl: String
          _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          val _tmpGenre: String
          _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          val _tmpRating: Float
          _tmpRating = _stmt.getDouble(_columnIndexOfRating).toFloat()
          val _tmpEstimatedReadTime: String
          _tmpEstimatedReadTime = _stmt.getText(_columnIndexOfEstimatedReadTime)
          val _tmpChaptersJson: String
          _tmpChaptersJson = _stmt.getText(_columnIndexOfChaptersJson)
          val _tmpIsSaved: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSaved).toInt()
          _tmpIsSaved = _tmp != 0
          _item = BookEntity(_tmpId,_tmpTitle,_tmpAuthor,_tmpSummary,_tmpCoverUrl,_tmpGenre,_tmpRating,_tmpEstimatedReadTime,_tmpChaptersJson,_tmpIsSaved)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBookById(bookId: Int): BookEntity? {
    val _sql: String = "SELECT * FROM books WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSummary: Int = getColumnIndexOrThrow(_stmt, "summary")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfEstimatedReadTime: Int = getColumnIndexOrThrow(_stmt, "estimatedReadTime")
        val _columnIndexOfChaptersJson: Int = getColumnIndexOrThrow(_stmt, "chaptersJson")
        val _columnIndexOfIsSaved: Int = getColumnIndexOrThrow(_stmt, "isSaved")
        val _result: BookEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSummary: String
          _tmpSummary = _stmt.getText(_columnIndexOfSummary)
          val _tmpCoverUrl: String
          _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          val _tmpGenre: String
          _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          val _tmpRating: Float
          _tmpRating = _stmt.getDouble(_columnIndexOfRating).toFloat()
          val _tmpEstimatedReadTime: String
          _tmpEstimatedReadTime = _stmt.getText(_columnIndexOfEstimatedReadTime)
          val _tmpChaptersJson: String
          _tmpChaptersJson = _stmt.getText(_columnIndexOfChaptersJson)
          val _tmpIsSaved: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsSaved).toInt()
          _tmpIsSaved = _tmp != 0
          _result = BookEntity(_tmpId,_tmpTitle,_tmpAuthor,_tmpSummary,_tmpCoverUrl,_tmpGenre,_tmpRating,_tmpEstimatedReadTime,_tmpChaptersJson,_tmpIsSaved)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun isBookSaved(bookId: Int): Boolean {
    val _sql: String = "SELECT EXISTS(SELECT 1 FROM books WHERE id = ? AND isSaved = 1)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId.toLong())
        val _result: Boolean
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp != 0
        } else {
          _result = false
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBookCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM books"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteBook(bookId: Int) {
    val _sql: String = "DELETE FROM books WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun saveToFavorites(bookId: Int) {
    val _sql: String = "UPDATE books SET isSaved = 1 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeFromFavorites(bookId: Int) {
    val _sql: String = "UPDATE books SET isSaved = 0 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
