package com.neepan.boockai.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.neepan.boockai.core.designsystem.component.BoockTopAppBar
import kotlin.math.roundToInt

@Composable
fun BookDetailRoot(
    onNavigateBack: () -> Unit,
    onNavigateToReader: (Int) -> Unit,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            when (event) {
                is BookDetailEvent.NavigateBack -> onNavigateBack()
                is BookDetailEvent.NavigateToReader -> onNavigateToReader(event.bookId.value)
            }
        }
    }

    BookDetailScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun BookDetailScreen(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit
) {
    Scaffold(
        topBar = {
            BoockTopAppBar(
                title = "Details",
                onBackClick = { onAction(BookDetailAction.OnBackClicked) },
                actions = {
                    if (state.book != null) {
                        IconButton(onClick = { onAction(BookDetailAction.OnToggleSavedClicked) }) {
                            Icon(
                                imageVector = if (state.isSaved) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = if (state.isSaved) "Remove from saved" else "Save book",
                                tint = if (state.isSaved) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (state.book != null) {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 8.dp
                ) {
                    val isStarted = state.progress != null && state.progress.progressPercent > 0f
                    val progressText = if (isStarted) {
                        "Continue Reading (${(state.progress!!.progressPercent * 100).roundToInt()}%)"
                    } else {
                        "Start Reading"
                    }
                    Button(
                        onClick = { onAction(BookDetailAction.OnStartReadingClicked) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = progressText,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null || state.book == null) {
                Text(
                    text = state.error ?: "Book not found",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                val book = state.book
                
                // Spotify-like background glow
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = book.coverUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .align(Alignment.TopCenter)
                            .blur(radius = 80.dp)
                            .clip(RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp))
                    )
                    
                    // Gradient overlay to blend it smoothly into the background
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
                                        MaterialTheme.colorScheme.background
                                    ),
                                    startY = 0f,
                                    endY = 1000f
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    // Cover
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        shadowElevation = 12.dp,
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        AsyncImage(
                            model = book.coverUrl,
                            contentDescription = "Cover of ${book.title}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(200.dp)
                                .aspectRatio(2f / 3f)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = book.author,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Badges row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BadgeItem(label = "Rating", value = "${book.rating} / 5", modifier = Modifier.weight(1f).fillMaxHeight())
                        BadgeItem(label = "Genre", value = book.genre, modifier = Modifier.weight(1f).fillMaxHeight())
                        BadgeItem(label = "Time", value = book.estimatedReadTime, modifier = Modifier.weight(1f).fillMaxHeight())
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Summary
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Summary",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = book.summary,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp)) // Extra padding for bottom bar
                }
            }
        }
    }
}
}

@Composable
private fun BadgeItem(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp, vertical = 12.dp)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
        }
    }
}
