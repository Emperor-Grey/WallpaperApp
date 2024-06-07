package com.example.wallpapertest.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WallGrid() {
    val sections = (0 until 25).toList().chunked(5)

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {

        items(8) {
            Text(
                "Item $it",
                Modifier
                    .border(1.dp, Color.Blue)
                    .height(80.dp)
                    .wrapContentSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WallGridPrev() {
    WallGrid()
}
