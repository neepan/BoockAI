package com.neepan.boockai.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.neepan.boockai.core.datastore.DataStoreReaderPreferences
import com.neepan.boockai.core.domain.ReaderPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreBindsModule {

    @Binds
    @Singleton
    abstract fun bindReaderPreferencesDataSource(
        impl: DataStoreReaderPreferences
    ): ReaderPreferencesDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DataStoreProvidesModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("boock_preferences")
        }
    }
}
