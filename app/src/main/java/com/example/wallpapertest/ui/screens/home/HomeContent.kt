package com.example.wallpapertest.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapertest.ui.common.components.AICard
import com.example.wallpapertest.ui.common.components.ScrollTab
import com.example.wallpapertest.ui.common.components.WallGridItem
import com.example.wallpapertest.ui.theme.salsaFontFamily
import com.example.wallpapertest.utils.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    padding: PaddingValues,
    navigateToWallpaper: (String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    viewModel: HomeViewModel
) {
    val categories = listOf("Random", "Latest", "Popular", "Featured", "Hot", "MostViewed")
    val selectedIndex = remember { mutableIntStateOf(0) }
    val wallpapersState by viewModel.wallpaper.collectAsState()


//    val images = listOf(
//        R.drawable.img,
//        R.drawable.fox,
//        R.drawable.tree,
//        R.drawable.cat,
//        R.drawable.bird,
//        R.drawable.bike
//    )

    LaunchedEffect(selectedIndex.intValue) {
        viewModel.fetchWallpapers(categories[selectedIndex.intValue])
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp,
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .padding(paddingValues = padding)
            .padding(horizontal = 12.dp),
    ) {

        item(span = StaggeredGridItemSpan.FullLine) {
            AICard()
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            Column(modifier = Modifier.fillMaxWidth()) {
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
            }
        }

        when (val result = wallpapersState) {
            is Result.Loading -> {
                item(span = StaggeredGridItemSpan.FullLine) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is Result.Success -> {
                if (result.data != null) {
                    itemsIndexed(result.data) { _, wallpaper ->
                        WallGridItem(wallpaper = wallpaper, onItemClick = navigateToWallpaper)
                    }
                }
            }

            is Result.Error -> {
                item(span = StaggeredGridItemSpan.FullLine) {
                    Text(text = "Error: ${result.message}", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
