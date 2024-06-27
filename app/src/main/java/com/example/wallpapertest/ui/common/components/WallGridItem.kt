package com.example.wallpapertest.ui.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.wallpapertest.domain.model.WallpaperItem


@Composable
fun WallGridItem(wallpaper: WallpaperItem, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onItemClick)
    ) {
        SubcomposeAsyncImage(model = wallpaper.path,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Wallpaper Image",
            error = {
                Box(Modifier.matchParentSize()) {
                    Text("Image load failed", Modifier.align(Alignment.Center))
                }
            },
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            })
    }
}