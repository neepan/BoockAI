package com.neepan.boockai.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neepan.boockai.core.domain.BookLocalDataSource
import com.neepan.boockai.core.domain.ReadingProgressDataSource
import com.neepan.boockai.core.model.BookId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookDataSource: BookLocalDataSource,
    private val progressDataSource: ReadingProgressDataSource
) : ViewModel() {

    // Retrieve bookId passed via navigation route
    private val bookIdRaw: Int = checkNotNull(savedStateHandle["bookId"])
    private val bookId = BookId(bookIdRaw)

    private val _state = MutableStateFlow(BookDetailState())
    val state = _state.asStateFlow()

    private val _events = Channel<BookDetailEvent>()
    val events = _events.receiveAsFlow()

    init {
        loadBookData()
    }

    fun onAction(action: BookDetailAction) {
        when (action) {
            BookDetailAction.OnBackClicked -> {
                viewModelScope.launch { _events.send(BookDetailEvent.NavigateBack) }
            }
            BookDetailAction.OnStartReadingClicked -> {
                viewModelScope.launch { _events.send(BookDetailEvent.NavigateToReader(bookId)) }
            }
            BookDetailAction.OnToggleSavedClicked -> toggleSaved()
        }
    }

    private fun loadBookData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val book = bookDataSource.getBookById(bookId)
            if (book == null) {
                _state.update { it.copy(isLoading = false, error = "Book not found") }
                return@launch
            }

            val isSaved = bookDataSource.isBookSaved(bookId)
            val progress = progressDataSource.getProgress(bookId)

            _state.update {
                it.copy(
                    book = book,
                    isSaved = isSaved,
                    progress = progress,
                    isLoading = false
                )
            }
        }
    }

    private fun toggleSaved() {
        viewModelScope.launch {
            val currentState = _state.value
            val newSavedState = !currentState.isSaved

            if (newSavedState) {
                bookDataSource.saveToFavorites(bookId)
            } else {
                bookDataSource.removeFromFavorites(bookId)
            }

            _state.update { it.copy(isSaved = newSavedState) }
        }
    }
}
