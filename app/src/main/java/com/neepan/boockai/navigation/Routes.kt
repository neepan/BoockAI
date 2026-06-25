package com.neepan.boockai.navigation

import kotlinx.serialization.Serializable

@Serializable
data object LibraryRoute

@Serializable
data class BookDetailRoute(val bookId: Int)

@Serializable
data class ReaderRoute(val bookId: Int)
