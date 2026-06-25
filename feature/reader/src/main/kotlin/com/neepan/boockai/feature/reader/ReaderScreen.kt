package com.neepan.boockai.feature.reader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neepan.boockai.core.designsystem.component.BoockTopAppBar
import com.neepan.boockai.core.designsystem.theme.ReaderDarkBackground
import com.neepan.boockai.core.designsystem.theme.ReaderDarkSurface
import com.neepan.boockai.core.designsystem.theme.ReaderDarkText
import com.neepan.boockai.core.designsystem.theme.ReaderLightBackground
import com.neepan.boockai.core.designsystem.theme.ReaderSepiaBackground
import com.neepan.boockai.core.designsystem.theme.ReaderSepiaText
import com.neepan.boockai.core.model.ReaderPreferences
import com.neepan.boockai.core.model.ReaderTheme
import com.neepan.boockai.core.model.TextSizeOption

@Composable
fun ReaderRoot(
    onNavigateBack: () -> Unit,
    viewModel: ReaderViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            when (event) {
                is ReaderEvent.NavigateBack -> onNavigateBack()
            }
        }
    }

    ReaderScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    state: ReaderState,
    onAction: (ReaderAction) -> Unit
) {
    val theme = state.preferences.readerTheme
    val backgroundColor = when (theme) {
        ReaderTheme.LIGHT -> ReaderLightBackground
        ReaderTheme.SEPIA -> ReaderSepiaBackground
        ReaderTheme.DARK -> ReaderDarkBackground
    }
    val textColor = when (theme) {
        ReaderTheme.LIGHT -> MaterialTheme.colorScheme.onSurface
        ReaderTheme.SEPIA -> ReaderSepiaText
        ReaderTheme.DARK -> ReaderDarkText
    }
    val fontSize = when (state.preferences.textSize) {
        TextSizeOption.SMALL -> 14.sp
        TextSizeOption.MEDIUM -> 18.sp
        TextSizeOption.LARGE -> 24.sp
    }

    Scaffold(
        topBar = {
            BoockTopAppBar(
                title = state.currentChapter?.title ?: "",
                onBackClick = { onAction(ReaderAction.OnBackClicked) },
                actions = {
                    IconButton(onClick = { onAction(ReaderAction.OnSettingsClicked) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.onSurface)
                    }
                }
            )
        },
        bottomBar = {
            if (state.progress != null) {
                LinearProgressIndicator(
                    progress = { state.progress.progressPercent },
                    modifier = Modifier.fillMaxWidth().height(4.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primaryContainer
                )
            }
        },
        containerColor = backgroundColor
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null || state.currentChapter == null) {
                Text(
                    text = state.error ?: "Failed to load content",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                val scrollState = rememberScrollState()
                
                // Track scroll progress
                LaunchedEffect(scrollState.maxValue) {
                    val initialScroll = state.progress?.scrollPosition ?: 0f
                    if (scrollState.maxValue > 0) {
                        scrollState.scrollTo((initialScroll * scrollState.maxValue).toInt())
                    }
                }

                LaunchedEffect(scrollState) {
                    snapshotFlow { scrollState.value }.collect { value ->
                        if (scrollState.maxValue > 0) {
                            val percent = value.toFloat() / scrollState.maxValue
                            onAction(ReaderAction.OnScrollPositionChanged(percent, state.currentChapter.id - 1))
                        }
                    }
                }

                Text(
                    text = state.currentChapter.content,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = fontSize),
                    color = textColor,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(16.dp)
                )
            }
        }
    }

    if (state.showSettings) {
        ReaderSettingsSheet(
            preferences = state.preferences,
            onAction = onAction
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderSettingsSheet(
    preferences: ReaderPreferences,
    onAction: (ReaderAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onAction(ReaderAction.OnSettingsDismissed) },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "Text Size",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextSizeOption.entries.forEach { option ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (preferences.textSize == option) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                        onClick = { onAction(ReaderAction.OnTextSizeChanged(option)) }
                    ) {
                        Text(
                            text = "A",
                            fontSize = when(option) {
                                TextSizeOption.SMALL -> 14.sp
                                TextSizeOption.MEDIUM -> 18.sp
                                TextSizeOption.LARGE -> 24.sp
                            },
                            modifier = Modifier.padding(16.dp),
                            color = if (preferences.textSize == option) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Theme",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ThemeButton(
                    theme = ReaderTheme.LIGHT,
                    selectedTheme = preferences.readerTheme,
                    backgroundColor = ReaderLightBackground,
                    textColor = MaterialTheme.colorScheme.onSurface,
                    onAction = onAction
                )
                ThemeButton(
                    theme = ReaderTheme.SEPIA,
                    selectedTheme = preferences.readerTheme,
                    backgroundColor = ReaderSepiaBackground,
                    textColor = ReaderSepiaText,
                    onAction = onAction
                )
                ThemeButton(
                    theme = ReaderTheme.DARK,
                    selectedTheme = preferences.readerTheme,
                    backgroundColor = ReaderDarkSurface,
                    textColor = ReaderDarkText,
                    onAction = onAction
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun ThemeButton(
    theme: ReaderTheme,
    selectedTheme: ReaderTheme,
    backgroundColor: Color,
    textColor: Color,
    onAction: (ReaderAction) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = backgroundColor,
        border = if (theme == selectedTheme) androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        onClick = { onAction(ReaderAction.OnThemeChanged(theme)) }
    ) {
        Text(
            text = theme.name.lowercase().replaceFirstChar { it.uppercase() },
            color = textColor,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}
