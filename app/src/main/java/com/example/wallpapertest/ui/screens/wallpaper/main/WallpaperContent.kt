package com.example.wallpapertest.ui.screens.wallpaper.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.wallpapertest.domain.model.WallpaperUiState

@Composable
fun WallpaperContent(
    wallpaperState: WallpaperUiState, toggleShowButtons: () -> Unit
) {
    when {
        wallpaperState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        wallpaperState.errorMessage != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Your Internet Sucks.../(Error): ${wallpaperState.errorMessage}")
            }
        }

        wallpaperState.wallpaper != null -> {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = toggleShowButtons,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() })
            ) {
                AsyncImage(
                    model = wallpaperState.wallpaper.data.path,
                    contentDescription = "Wallpaper",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                    imageLoader = ImageLoader.Builder(LocalContext.current).build()
                )
            }
        }
    }
}
