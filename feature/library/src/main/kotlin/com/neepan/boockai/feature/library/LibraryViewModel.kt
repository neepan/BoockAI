package com.neepan.boockai.feature.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neepan.boockai.core.domain.BookLocalDataSource
import com.neepan.boockai.core.domain.ReadingProgressDataSource
import com.neepan.boockai.core.model.ReadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Library screen.
 * SRP: Only manages UI state. Data logic is delegated to data sources.
 * Uses MVI pattern: single State, Action dispatch, Event channel.
 */
@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val bookDataSource: BookLocalDataSource,
    private val progressDataSource: ReadingProgressDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(LibraryState())
    val state = _state.asStateFlow()

    private val _events = Channel<LibraryEvent>()
    val events = _events.receiveAsFlow()

    init {
        loadBooks()
    }

    fun onAction(action: LibraryAction) {
        when (action) {
            is LibraryAction.OnSearchQueryChanged -> {
                _state.update { it.copy(searchQuery = action.query) }
                applyFilters()
            }
            is LibraryAction.OnBookClicked -> {
                viewModelScope.launch {
                    _events.send(LibraryEvent.NavigateToDetail(action.bookId))
                }
            }
            is LibraryAction.OnFilterSelected -> {
                _state.update { it.copy(selectedFilter = action.filter) }
                applyFilters()
            }
        }
    }

    private fun loadBooks() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            combine(
                bookDataSource.getAllBooks(),
                progressDataSource.observeAllProgress()
            ) { books, allProgress ->
                val progressMap = allProgress.associateBy { it.bookId }
                books.map { book ->
                    val progress = progressMap[book.id]
                    val status = when {
                        progress == null || progress.progressPercent == 0f -> ReadingStatus.NOT_STARTED
                        progress.progressPercent >= 1f -> ReadingStatus.FINISHED
                        else -> ReadingStatus.READING
                    }
                    BookUi(
                        id = book.id,
                        title = book.title,
                        author = book.author,
                        coverUrl = book.coverUrl,
                        readingStatus = status,
                        progressPercent = progress?.progressPercent ?: 0f
                    )
                }
            }.collect { bookUis ->
                _state.update {
                    it.copy(
                        books = bookUis,
                        isLoading = false,
                        error = if (bookUis.isEmpty()) "No books found" else null
                    )
                }
                applyFilters()
            }
        }
    }

    private fun applyFilters() {
        val currentState = _state.value
        val filtered = currentState.books
            .filter { book ->
                val matchesSearch = currentState.searchQuery.isBlank() ||
                    book.title.contains(currentState.searchQuery, ignoreCase = true) ||
                    book.author.contains(currentState.searchQuery, ignoreCase = true)
                val matchesFilter = when (currentState.selectedFilter) {
                    BookFilter.ALL -> true
                    BookFilter.READING -> book.readingStatus == ReadingStatus.READING
                    BookFilter.FINISHED -> book.readingStatus == ReadingStatus.FINISHED
                    BookFilter.WANT_TO_READ -> book.readingStatus == ReadingStatus.NOT_STARTED
                }
                matchesSearch && matchesFilter
            }
        _state.update { it.copy(filteredBooks = filtered) }
    }
}
