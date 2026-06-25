package com.neepan.boockai.core.model

import kotlinx.serialization.Serializable

/**
 * Value object wrapping a book's unique identifier.
 * Prevents primitive obsession and ensures type safety across layers.
 */
@JvmInline
@Serializable
value class BookId(val value: Int)
