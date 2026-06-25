package com.neepan.boockai.core.data.json

import android.content.Context
import com.neepan.boockai.core.model.Book
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Parses the bundled books.json from the app's assets folder.
 * SRP: Only responsible for reading and deserializing the JSON file.
 */
@Singleton
class BookJsonParser @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val json = Json { ignoreUnknownKeys = true }

    fun parseBooksFromAssets(): List<Book> {
        val jsonString = context.assets
            .open("books.json")
            .bufferedReader()
            .use { it.readText() }

        return json.decodeFromString<List<Book>>(jsonString)
    }
}
