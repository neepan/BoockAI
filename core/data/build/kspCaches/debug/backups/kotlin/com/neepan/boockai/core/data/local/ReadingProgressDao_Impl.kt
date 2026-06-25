package com.neepan.boockai.core.`data`.local

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Float
import kotlin.Int
import kotlin.Long
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
public class ReadingProgressDao_Impl(
  __db: RoomDatabase,
) : ReadingProgressDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfReadingProgressEntity: EntityInsertAdapter<ReadingProgressEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfReadingProgressEntity = object : EntityInsertAdapter<ReadingProgressEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `reading_progress` (`bookId`,`chapterIndex`,`scrollPosition`,`progressPercent`,`lastReadTimestamp`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ReadingProgressEntity) {
        statement.bindLong(1, entity.bookId.toLong())
        statement.bindLong(2, entity.chapterIndex.toLong())
        statement.bindDouble(3, entity.scrollPosition.toDouble())
        statement.bindDouble(4, entity.progressPercent.toDouble())
        statement.bindLong(5, entity.lastReadTimestamp)
      }
    }
  }

  public override suspend fun upsertProgress(progress: ReadingProgressEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfReadingProgressEntity.insert(_connection, progress)
  }

  public override suspend fun getProgress(bookId: Int): ReadingProgressEntity? {
    val _sql: String = "SELECT * FROM reading_progress WHERE bookId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId.toLong())
        val _columnIndexOfBookId: Int = getColumnIndexOrThrow(_stmt, "bookId")
        val _columnIndexOfChapterIndex: Int = getColumnIndexOrThrow(_stmt, "chapterIndex")
        val _columnIndexOfScrollPosition: Int = getColumnIndexOrThrow(_stmt, "scrollPosition")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfLastReadTimestamp: Int = getColumnIndexOrThrow(_stmt, "lastReadTimestamp")
        val _result: ReadingProgressEntity?
        if (_stmt.step()) {
          val _tmpBookId: Int
          _tmpBookId = _stmt.getLong(_columnIndexOfBookId).toInt()
          val _tmpChapterIndex: Int
          _tmpChapterIndex = _stmt.getLong(_columnIndexOfChapterIndex).toInt()
          val _tmpScrollPosition: Float
          _tmpScrollPosition = _stmt.getDouble(_columnIndexOfScrollPosition).toFloat()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpLastReadTimestamp: Long
          _tmpLastReadTimestamp = _stmt.getLong(_columnIndexOfLastReadTimestamp)
          _result = ReadingProgressEntity(_tmpBookId,_tmpChapterIndex,_tmpScrollPosition,_tmpProgressPercent,_tmpLastReadTimestamp)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeProgress(bookId: Int): Flow<ReadingProgressEntity?> {
    val _sql: String = "SELECT * FROM reading_progress WHERE bookId = ?"
    return createFlow(__db, false, arrayOf("reading_progress")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId.toLong())
        val _columnIndexOfBookId: Int = getColumnIndexOrThrow(_stmt, "bookId")
        val _columnIndexOfChapterIndex: Int = getColumnIndexOrThrow(_stmt, "chapterIndex")
        val _columnIndexOfScrollPosition: Int = getColumnIndexOrThrow(_stmt, "scrollPosition")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfLastReadTimestamp: Int = getColumnIndexOrThrow(_stmt, "lastReadTimestamp")
        val _result: ReadingProgressEntity?
        if (_stmt.step()) {
          val _tmpBookId: Int
          _tmpBookId = _stmt.getLong(_columnIndexOfBookId).toInt()
          val _tmpChapterIndex: Int
          _tmpChapterIndex = _stmt.getLong(_columnIndexOfChapterIndex).toInt()
          val _tmpScrollPosition: Float
          _tmpScrollPosition = _stmt.getDouble(_columnIndexOfScrollPosition).toFloat()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpLastReadTimestamp: Long
          _tmpLastReadTimestamp = _stmt.getLong(_columnIndexOfLastReadTimestamp)
          _result = ReadingProgressEntity(_tmpBookId,_tmpChapterIndex,_tmpScrollPosition,_tmpProgressPercent,_tmpLastReadTimestamp)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeAllProgress(): Flow<List<ReadingProgressEntity>> {
    val _sql: String = "SELECT * FROM reading_progress"
    return createFlow(__db, false, arrayOf("reading_progress")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfBookId: Int = getColumnIndexOrThrow(_stmt, "bookId")
        val _columnIndexOfChapterIndex: Int = getColumnIndexOrThrow(_stmt, "chapterIndex")
        val _columnIndexOfScrollPosition: Int = getColumnIndexOrThrow(_stmt, "scrollPosition")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfLastReadTimestamp: Int = getColumnIndexOrThrow(_stmt, "lastReadTimestamp")
        val _result: MutableList<ReadingProgressEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ReadingProgressEntity
          val _tmpBookId: Int
          _tmpBookId = _stmt.getLong(_columnIndexOfBookId).toInt()
          val _tmpChapterIndex: Int
          _tmpChapterIndex = _stmt.getLong(_columnIndexOfChapterIndex).toInt()
          val _tmpScrollPosition: Float
          _tmpScrollPosition = _stmt.getDouble(_columnIndexOfScrollPosition).toFloat()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpLastReadTimestamp: Long
          _tmpLastReadTimestamp = _stmt.getLong(_columnIndexOfLastReadTimestamp)
          _item = ReadingProgressEntity(_tmpBookId,_tmpChapterIndex,_tmpScrollPosition,_tmpProgressPercent,_tmpLastReadTimestamp)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLastReadProgress(): ReadingProgressEntity? {
    val _sql: String = "SELECT * FROM reading_progress ORDER BY lastReadTimestamp DESC LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfBookId: Int = getColumnIndexOrThrow(_stmt, "bookId")
        val _columnIndexOfChapterIndex: Int = getColumnIndexOrThrow(_stmt, "chapterIndex")
        val _columnIndexOfScrollPosition: Int = getColumnIndexOrThrow(_stmt, "scrollPosition")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfLastReadTimestamp: Int = getColumnIndexOrThrow(_stmt, "lastReadTimestamp")
        val _result: ReadingProgressEntity?
        if (_stmt.step()) {
          val _tmpBookId: Int
          _tmpBookId = _stmt.getLong(_columnIndexOfBookId).toInt()
          val _tmpChapterIndex: Int
          _tmpChapterIndex = _stmt.getLong(_columnIndexOfChapterIndex).toInt()
          val _tmpScrollPosition: Float
          _tmpScrollPosition = _stmt.getDouble(_columnIndexOfScrollPosition).toFloat()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpLastReadTimestamp: Long
          _tmpLastReadTimestamp = _stmt.getLong(_columnIndexOfLastReadTimestamp)
          _result = ReadingProgressEntity(_tmpBookId,_tmpChapterIndex,_tmpScrollPosition,_tmpProgressPercent,_tmpLastReadTimestamp)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
