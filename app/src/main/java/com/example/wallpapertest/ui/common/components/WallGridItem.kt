package com.example.wallpapertest.ui.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun WallGridItem(image: String, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = { onItemClick(image) })
    ) {
        AnimatedVisibility(visible = true) {
            Image(
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun WallGridItemPrev() {
    WallGridItem(image = "https://example.com/path/to/image.jpg", onItemClick = {})
}
