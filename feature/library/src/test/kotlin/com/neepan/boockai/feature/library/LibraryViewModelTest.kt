package com.neepan.boockai.feature.library

import app.cash.turbine.test
import com.neepan.boockai.core.model.Book
import com.neepan.boockai.core.model.BookId
import com.neepan.boockai.core.model.ReadingProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LibraryViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var fakeBookDataSource: FakeBookLocalDataSource
    private lateinit var fakeProgressDataSource: FakeReadingProgressDataSource

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeBookDataSource = FakeBookLocalDataSource()
        fakeProgressDataSource = FakeReadingProgressDataSource()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel() = LibraryViewModel(
        bookDataSource = fakeBookDataSource,
        progressDataSource = fakeProgressDataSource
    )

    private fun sampleBook(id: Int = 1, title: String = "Test Book", author: String = "Author") =
        Book(
            id = BookId(id),
            title = title,
            author = author,
            summary = "Summary",
            coverUrl = "url",
            genre = "Fiction",
            rating = 4.5f,
            estimatedReadTime = "5h"
        )

    @Test
    fun `initial state is loading`() = runTest {
        val viewModel = createViewModel()
        // After init, books flow emits empty list, so loading becomes false
        assertFalse(viewModel.state.value.isLoading)
    }

    @Test
    fun `books are loaded into state`() = runTest {
        fakeBookDataSource.emitBooks(listOf(sampleBook()))
        val viewModel = createViewModel()

        assertEquals(1, viewModel.state.value.filteredBooks.size)
        assertEquals("Test Book", viewModel.state.value.filteredBooks.first().title)
    }

    @Test
    fun `search query filters books`() = runTest {
        fakeBookDataSource.emitBooks(
            listOf(sampleBook(1, "Alpha"), sampleBook(2, "Beta"))
        )
        val viewModel = createViewModel()

        viewModel.onAction(LibraryAction.OnSearchQueryChanged("Alpha"))
        assertEquals(1, viewModel.state.value.filteredBooks.size)
        assertEquals("Alpha", viewModel.state.value.filteredBooks.first().title)
    }

    @Test
    fun `filter by reading status`() = runTest {
        fakeBookDataSource.emitBooks(listOf(sampleBook(1), sampleBook(2)))
        fakeProgressDataSource.setProgress(
            BookId(1),
            ReadingProgress(BookId(1), progressPercent = 0.5f)
        )
        val viewModel = createViewModel()

        viewModel.onAction(LibraryAction.OnFilterSelected(BookFilter.READING))
        assertEquals(1, viewModel.state.value.filteredBooks.size)
        assertEquals(1, viewModel.state.value.filteredBooks.first().id.value)
    }

    @Test
    fun `clicking book emits NavigateToDetail event`() = runTest {
        val viewModel = createViewModel()

        viewModel.events.test {
            viewModel.onAction(LibraryAction.OnBookClicked(BookId(42)))
            val event = awaitItem()
            assertTrue(event is LibraryEvent.NavigateToDetail)
            assertEquals(42, (event as LibraryEvent.NavigateToDetail).bookId.value)
        }
    }

    @Test
    fun `empty search shows all books`() = runTest {
        fakeBookDataSource.emitBooks(listOf(sampleBook(1), sampleBook(2), sampleBook(3)))
        val viewModel = createViewModel()

        viewModel.onAction(LibraryAction.OnSearchQueryChanged("xyz"))
        assertEquals(0, viewModel.state.value.filteredBooks.size)

        viewModel.onAction(LibraryAction.OnSearchQueryChanged(""))
        assertEquals(3, viewModel.state.value.filteredBooks.size)
    }
}
