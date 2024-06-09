package com.example.wallpapertest.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
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
fun WallGrid(onItemClick: (Int) -> Unit) {

    val images = listOf(
        R.drawable.img, R.drawable.fox, R.drawable.bike, R.drawable.cat, R.drawable.guy
    )

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
    ) {
        itemsIndexed(images) { _i, image ->
            Card(modifier = Modifier
                .clip(RoundedCornerShape(12))
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
    }
}

@Preview(showBackground = true)
@Composable
private fun WallGridPrev() {
    WallGrid(onItemClick = {})
}
