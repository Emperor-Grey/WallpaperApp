package com.example.wallpapertest.ui.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.wallpapertest.domain.model.WallpaperItem

@Composable
fun WallGridItem(onItemClick: (String) -> Unit, wallpaper: WallpaperItem) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = { onItemClick(wallpaper.id) })
    ) {
        AsyncImage(
            model = wallpaper.path,
            contentDescription = "image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
            imageLoader = ImageLoader.Builder(LocalContext.current).build()
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun WallGridItemPrev() {
//    WallGridItem(
//        image = "https://example.com/path/to/image.jpg", onItemClick = {}, wallpaper = wallpaper
//    )
//}
