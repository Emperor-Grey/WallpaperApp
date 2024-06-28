package com.example.wallpapertest.ui.screens.wallpaper.preview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage

@Composable
fun WallpaperPreviewScreen() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = {},
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
    ) {
        AsyncImage(
            model = "",
            contentDescription = "Wallpaper",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
            imageLoader = ImageLoader.Builder(LocalContext.current).build()
        )
    }
}