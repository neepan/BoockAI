package com.neepan.boockai.feature.reader

import com.neepan.boockai.core.model.Book
import com.neepan.boockai.core.model.Chapter
import com.neepan.boockai.core.model.ReaderPreferences
import com.neepan.boockai.core.model.ReaderTheme
import com.neepan.boockai.core.model.ReadingProgress
import com.neepan.boockai.core.model.TextSizeOption

data class ReaderState(
    val book: Book? = null,
    val currentChapter: Chapter? = null,
    val progress: ReadingProgress? = null,
    val preferences: ReaderPreferences = ReaderPreferences(),
    val showSettings: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null
)

sealed interface ReaderAction {
    data object OnSettingsClicked : ReaderAction
    data object OnSettingsDismissed : ReaderAction
    data class OnTextSizeChanged(val size: TextSizeOption) : ReaderAction
    data class OnThemeChanged(val theme: ReaderTheme) : ReaderAction
    data class OnScrollPositionChanged(val scrollPercent: Float, val chapterIndex: Int) : ReaderAction
    data object OnBackClicked : ReaderAction
}

sealed interface ReaderEvent {
    data object NavigateBack : ReaderEvent
}
