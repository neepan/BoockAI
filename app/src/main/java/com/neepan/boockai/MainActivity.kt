package com.neepan.boockai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neepan.boockai.core.domain.BookLocalDataSource
import com.neepan.boockai.core.designsystem.theme.BoockTheme
import com.neepan.boockai.feature.detail.BookDetailRoot
import com.neepan.boockai.feature.library.LibraryRoot
import com.neepan.boockai.feature.reader.ReaderRoot
import com.neepan.boockai.navigation.BookDetailRoute
import com.neepan.boockai.navigation.LibraryRoute
import com.neepan.boockai.navigation.ReaderRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var bookDataSource: BookLocalDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Seed DB from JSON on first launch
        lifecycleScope.launch {
            bookDataSource.seedIfEmpty()
        }

        enableEdgeToEdge()
        setContent {
            BoockTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = LibraryRoute
                    ) {
                        composable<LibraryRoute> {
                            LibraryRoot(
                                onNavigateToDetail = { bookId ->
                                    navController.navigate(BookDetailRoute(bookId))
                                }
                            )
                        }

                        composable<BookDetailRoute> {
                            BookDetailRoot(
                                onNavigateBack = { navController.popBackStack() },
                                onNavigateToReader = { bookId ->
                                    navController.navigate(ReaderRoute(bookId))
                                }
                            )
                        }

                        composable<ReaderRoute> {
                            ReaderRoot(
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}