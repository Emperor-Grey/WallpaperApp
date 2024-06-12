package com.example.wallpapertest.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallpapertest.R

@Composable
fun WallGridItem(image: Int, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = { onItemClick(image) })
    ) {
        AnimatedVisibility(visible = true) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun WallGridItemPrev() {
    WallGridItem(image = R.drawable.cat, onItemClick = {})
}
