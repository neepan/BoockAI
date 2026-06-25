package com.neepan.boockai.feature.library

import com.neepan.boockai.core.model.BookId
import com.neepan.boockai.core.model.ReadingStatus

/**
 * Single immutable state for the Library screen.
 * All UI state is derived from this single source of truth (MVI).
 */
data class LibraryState(
    val books: List<BookUi> = emptyList(),
    val filteredBooks: List<BookUi> = emptyList(),
    val searchQuery: String = "",
    val selectedFilter: BookFilter = BookFilter.ALL,
    val isLoading: Boolean = true,
    val error: String? = null
)

/**
 * UI model for displaying a book in the library grid.
 * Suffixed with Ui per MVI skill convention.
 */
data class BookUi(
    val id: BookId,
    val title: String,
    val author: String,
    val coverUrl: String,
    val readingStatus: ReadingStatus,
    val progressPercent: Float
)

/**
 * Filter options for the segmented buttons (from Figma).
 */
enum class BookFilter(val label: String) {
    ALL("All Books"),
    READING("Reading"),
    FINISHED("Finished"),
    WANT_TO_READ("Want to Read")
}

/**
 * User-triggered actions (intents) on the Library screen.
 */
sealed interface LibraryAction {
    data class OnSearchQueryChanged(val query: String) : LibraryAction
    data class OnBookClicked(val bookId: BookId) : LibraryAction
    data class OnFilterSelected(val filter: BookFilter) : LibraryAction
}

/**
 * One-time side effects emitted by the ViewModel via Channel.
 */
sealed interface LibraryEvent {
    data class NavigateToDetail(val bookId: BookId) : LibraryEvent
}
