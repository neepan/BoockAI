package com.neepan.boockai.core.data.di

import android.content.Context
import androidx.room.Room
import com.neepan.boockai.core.data.RoomBookDataSource
import com.neepan.boockai.core.data.RoomReadingProgressDataSource
import com.neepan.boockai.core.data.local.BoockDatabase
import com.neepan.boockai.core.data.local.BookDao
import com.neepan.boockai.core.data.local.ReadingProgressDao
import com.neepan.boockai.core.domain.BookLocalDataSource
import com.neepan.boockai.core.domain.ReadingProgressDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindsModule {

    @Binds
    @Singleton
    abstract fun bindBookDataSource(
        impl: RoomBookDataSource
    ): BookLocalDataSource

    @Binds
    @Singleton
    abstract fun bindReadingProgressDataSource(
        impl: RoomReadingProgressDataSource
    ): ReadingProgressDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DataProvidesModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BoockDatabase {
        return Room.databaseBuilder(
            context,
            BoockDatabase::class.java,
            "boock_database"
        ).build()
    }

    @Provides
    fun provideBookDao(database: BoockDatabase): BookDao {
        return database.bookDao()
    }

    @Provides
    fun provideReadingProgressDao(database: BoockDatabase): ReadingProgressDao {
        return database.readingProgressDao()
    }
}
