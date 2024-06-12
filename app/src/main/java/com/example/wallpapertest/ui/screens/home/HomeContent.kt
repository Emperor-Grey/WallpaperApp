package com.example.wallpapertest.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapertest.R
import com.example.wallpapertest.components.AICard
import com.example.wallpapertest.components.ScrollTab
import com.example.wallpapertest.components.WallGridItem
import com.example.wallpapertest.ui.theme.salsaFontFamily

@Composable
fun HomeContent(
    padding: PaddingValues,
    navigateToWallpaper: (Int) -> Unit,
) {
    val categories = listOf("Random", "Popular", "Featured", "Anime", "Nature")
    val selectedIndex = remember { mutableIntStateOf(0) }

    val images = listOf(
        R.drawable.img,
        R.drawable.fox,
        R.drawable.tree,
        R.drawable.cat,
        R.drawable.bird,
        R.drawable.bike
    )

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 8.dp,
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .padding(12.dp),
    ) {

        item(span = StaggeredGridItemSpan.FullLine) {
            AICard()
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Discover",
                    fontFamily = salsaFontFamily,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Discover the best wallpapers",
                    fontFamily = salsaFontFamily,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(12.dp))
                ScrollTab(categories = categories, selectedIndex = selectedIndex.intValue) {
                    selectedIndex.intValue = it
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        itemsIndexed(images) { _, image ->
            WallGridItem(image = image, onItemClick = navigateToWallpaper)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPrev() {
    HomeContent(padding = PaddingValues(Dp.Unspecified), navigateToWallpaper = {})
}
