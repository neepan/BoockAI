package com.neepan.boockai.core.model

/**
 * Available text size options for the reader.
 * Mapped to specific sp values in the design system.
 */
enum class TextSizeOption {
    SMALL,
    MEDIUM,
    LARGE
}

/**
 * Reader color theme options matching the Figma design.
 * Light (#FDF8FD), Sepia (#F4ECD8), Dark (#121212).
 */
enum class ReaderTheme {
    LIGHT,
    SEPIA,
    DARK
}

/**
 * Persisted user preferences for the reader experience.
 */
data class ReaderPreferences(
    val textSize: TextSizeOption = TextSizeOption.MEDIUM,
    val readerTheme: ReaderTheme = ReaderTheme.LIGHT
)
