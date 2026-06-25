package com.neepan.boockai.feature.reader

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neepan.boockai.core.domain.BookLocalDataSource
import com.neepan.boockai.core.domain.ReaderPreferencesDataSource
import com.neepan.boockai.core.domain.ReadingProgressDataSource
import com.neepan.boockai.core.model.BookId
import com.neepan.boockai.core.model.ReadingProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReaderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookDataSource: BookLocalDataSource,
    private val progressDataSource: ReadingProgressDataSource,
    private val preferencesDataSource: ReaderPreferencesDataSource
) : ViewModel() {

    private val bookIdRaw: Int = checkNotNull(savedStateHandle["bookId"])
    private val bookId = BookId(bookIdRaw)

    private val _state = MutableStateFlow(ReaderState())
    val state = _state.asStateFlow()

    private val _events = Channel<ReaderEvent>()
    val events = _events.receiveAsFlow()

    private var saveProgressJob: Job? = null

    init {
        loadData()
        observePreferences()
    }

    fun onAction(action: ReaderAction) {
        when (action) {
            ReaderAction.OnBackClicked -> {
                viewModelScope.launch { _events.send(ReaderEvent.NavigateBack) }
            }
            ReaderAction.OnSettingsClicked -> _state.update { it.copy(showSettings = true) }
            ReaderAction.OnSettingsDismissed -> _state.update { it.copy(showSettings = false) }
            is ReaderAction.OnScrollPositionChanged -> handleScrollChanged(action.scrollPercent, action.chapterIndex)
            is ReaderAction.OnTextSizeChanged -> {
                viewModelScope.launch { preferencesDataSource.updateTextSize(action.size) }
            }
            is ReaderAction.OnThemeChanged -> {
                viewModelScope.launch { preferencesDataSource.updateTheme(action.theme) }
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val book = bookDataSource.getBookById(bookId)
            if (book == null || book.chapters.isEmpty()) {
                _state.update { it.copy(isLoading = false, error = "Book or chapters not found") }
                return@launch
            }

            val progress = progressDataSource.getProgress(bookId) ?: ReadingProgress(bookId)
            val currentChapter = book.chapters.getOrNull(progress.chapterIndex) ?: book.chapters.first()

            _state.update {
                it.copy(
                    book = book,
                    currentChapter = currentChapter,
                    progress = progress,
                    isLoading = false
                )
            }
        }
    }

    private fun observePreferences() {
        viewModelScope.launch {
            preferencesDataSource.getPreferences().collect { prefs ->
                _state.update { it.copy(preferences = prefs) }
            }
        }
    }

    private fun handleScrollChanged(scrollPercent: Float, chapterIndex: Int) {
        val currentProgress = _state.value.progress ?: return
        val newProgress = currentProgress.copy(
            chapterIndex = chapterIndex,
            scrollPosition = scrollPercent,
            progressPercent = scrollPercent
        )
        
        _state.update { it.copy(progress = newProgress) }

        // Debounce saving progress to DB
        saveProgressJob?.cancel()
        saveProgressJob = viewModelScope.launch {
            delay(500)
            progressDataSource.saveProgress(newProgress)
        }
    }
}
