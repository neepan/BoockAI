package com.neepan.boockai.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.neepan.boockai.core.domain.ReaderPreferencesDataSource
import com.neepan.boockai.core.model.ReaderPreferences
import com.neepan.boockai.core.model.ReaderTheme
import com.neepan.boockai.core.model.TextSizeOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DataStore-backed implementation of [ReaderPreferencesDataSource].
 */
@Singleton
class DataStoreReaderPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ReaderPreferencesDataSource {

    private companion object {
        val TEXT_SIZE_KEY = stringPreferencesKey("text_size")
        val THEME_KEY = stringPreferencesKey("reader_theme")
    }

    override fun getPreferences(): Flow<ReaderPreferences> {
        return dataStore.data.map { prefs ->
            ReaderPreferences(
                textSize = prefs[TEXT_SIZE_KEY]?.let {
                    runCatching { TextSizeOption.valueOf(it) }.getOrDefault(TextSizeOption.MEDIUM)
                } ?: TextSizeOption.MEDIUM,
                readerTheme = prefs[THEME_KEY]?.let {
                    runCatching { ReaderTheme.valueOf(it) }.getOrDefault(ReaderTheme.LIGHT)
                } ?: ReaderTheme.LIGHT
            )
        }
    }

    override suspend fun updateTextSize(size: TextSizeOption) {
        dataStore.edit { prefs ->
            prefs[TEXT_SIZE_KEY] = size.name
        }
    }

    override suspend fun updateTheme(theme: ReaderTheme) {
        dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme.name
        }
    }
}
