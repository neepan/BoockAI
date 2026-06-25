package com.neepan.boockai.core.domain

import com.neepan.boockai.core.model.ReaderPreferences
import com.neepan.boockai.core.model.ReaderTheme
import com.neepan.boockai.core.model.TextSizeOption
import kotlinx.coroutines.flow.Flow

/**
 * Contract for reader preferences persistence (text size, theme).
 */
interface ReaderPreferencesDataSource {

    /** Observe reader preferences as a reactive stream. */
    fun getPreferences(): Flow<ReaderPreferences>

    /** Update the text size preference. */
    suspend fun updateTextSize(size: TextSizeOption)

    /** Update the reader theme preference. */
    suspend fun updateTheme(theme: ReaderTheme)
}
