package com.neepan.boockai.core.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class BoockDatabase_Impl : BoockDatabase() {
  private val _bookDao: Lazy<BookDao> = lazy {
    BookDao_Impl(this)
  }

  private val _readingProgressDao: Lazy<ReadingProgressDao> = lazy {
    ReadingProgressDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "b9ae1bc28255080b4c3885d0b0b9499e", "d43adf23592dcb69599a8497cff0fb15") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `books` (`id` INTEGER NOT NULL, `title` TEXT NOT NULL, `author` TEXT NOT NULL, `summary` TEXT NOT NULL, `coverUrl` TEXT NOT NULL, `genre` TEXT NOT NULL, `rating` REAL NOT NULL, `estimatedReadTime` TEXT NOT NULL, `chaptersJson` TEXT NOT NULL, `isSaved` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `reading_progress` (`bookId` INTEGER NOT NULL, `chapterIndex` INTEGER NOT NULL, `scrollPosition` REAL NOT NULL, `progressPercent` REAL NOT NULL, `lastReadTimestamp` INTEGER NOT NULL, PRIMARY KEY(`bookId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b9ae1bc28255080b4c3885d0b0b9499e')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `books`")
        connection.execSQL("DROP TABLE IF EXISTS `reading_progress`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsBooks: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBooks.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("author", TableInfo.Column("author", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("summary", TableInfo.Column("summary", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("coverUrl", TableInfo.Column("coverUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("genre", TableInfo.Column("genre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("rating", TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("estimatedReadTime", TableInfo.Column("estimatedReadTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("chaptersJson", TableInfo.Column("chaptersJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("isSaved", TableInfo.Column("isSaved", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBooks: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBooks: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBooks: TableInfo = TableInfo("books", _columnsBooks, _foreignKeysBooks, _indicesBooks)
        val _existingBooks: TableInfo = read(connection, "books")
        if (!_infoBooks.equals(_existingBooks)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |books(com.neepan.boockai.core.data.local.BookEntity).
              | Expected:
              |""".trimMargin() + _infoBooks + """
              |
              | Found:
              |""".trimMargin() + _existingBooks)
        }
        val _columnsReadingProgress: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsReadingProgress.put("bookId", TableInfo.Column("bookId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsReadingProgress.put("chapterIndex", TableInfo.Column("chapterIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsReadingProgress.put("scrollPosition", TableInfo.Column("scrollPosition", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsReadingProgress.put("progressPercent", TableInfo.Column("progressPercent", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsReadingProgress.put("lastReadTimestamp", TableInfo.Column("lastReadTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysReadingProgress: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesReadingProgress: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoReadingProgress: TableInfo = TableInfo("reading_progress", _columnsReadingProgress, _foreignKeysReadingProgress, _indicesReadingProgress)
        val _existingReadingProgress: TableInfo = read(connection, "reading_progress")
        if (!_infoReadingProgress.equals(_existingReadingProgress)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |reading_progress(com.neepan.boockai.core.data.local.ReadingProgressEntity).
              | Expected:
              |""".trimMargin() + _infoReadingProgress + """
              |
              | Found:
              |""".trimMargin() + _existingReadingProgress)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "books", "reading_progress")
  }

  public override fun clearAllTables() {
    super.performClear(false, "books", "reading_progress")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(BookDao::class, BookDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ReadingProgressDao::class, ReadingProgressDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun bookDao(): BookDao = _bookDao.value

  public override fun readingProgressDao(): ReadingProgressDao = _readingProgressDao.value
}
