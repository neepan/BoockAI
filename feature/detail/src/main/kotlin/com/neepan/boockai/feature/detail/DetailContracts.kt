package com.neepan.boockai.feature.detail

import com.neepan.boockai.core.model.Book
import com.neepan.boockai.core.model.BookId
import com.neepan.boockai.core.model.ReadingProgress

/**
 * Single immutable state for the Book Detail screen.
 */
data class BookDetailState(
    val book: Book? = null,
    val isSaved: Boolean = false,
    val progress: ReadingProgress? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)

sealed interface BookDetailAction {
    data object OnStartReadingClicked : BookDetailAction
    data object OnToggleSavedClicked : BookDetailAction
    data object OnBackClicked : BookDetailAction
}

sealed interface BookDetailEvent {
    data class NavigateToReader(val bookId: BookId) : BookDetailEvent
    data object NavigateBack : BookDetailEvent
}
