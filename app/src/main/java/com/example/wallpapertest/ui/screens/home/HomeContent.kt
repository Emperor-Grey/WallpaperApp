package com.example.wallpapertest.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.gestures.ScrollableDefaults
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
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapertest.data.utils.Result
import com.example.wallpapertest.ui.common.components.AICard
import com.example.wallpapertest.ui.common.components.ScrollTab
import com.example.wallpapertest.ui.common.components.WallGridItem
import com.example.wallpapertest.ui.theme.salsaFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    padding: PaddingValues,
    navigateToWallpaper: (String) -> Unit,
    onCardClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    viewModel: HomeViewModel
) {
    val categories by viewModel.categories
    val context = LocalContext.current
    var selectedIndex by viewModel.selectedIndex
    val wallpapersState by viewModel.wallpaper.collectAsState()
    val isDataFetched by viewModel.isDataFetched.collectAsState()

    LaunchedEffect(selectedIndex) {
        if (!isDataFetched) {
            viewModel.fetchWallpapers(categories[selectedIndex])
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val result = wallpapersState) {
            is Result.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is Result.Success -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 12.dp,
                    flingBehavior = ScrollableDefaults.flingBehavior(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp)
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .padding(top = padding.calculateTopPadding())
                ) {
                    item(span = StaggeredGridItemSpan.FullLine) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            AICard(onCardClick = {
                                Toast.makeText(
                                    context, "Feature Coming in the next update", Toast.LENGTH_SHORT
                                ).show()
                            })
                            Spacer(modifier = Modifier.height(12.dp))
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
                                ScrollTab(categories = categories, selectedIndex = selectedIndex) {
                                    selectedIndex = it
                                    viewModel.fetchWallpapers(categories[it])
                                }
                            }
                        }
                    }

                    items(result.data!!) { wallpaper ->
                        WallGridItem(
                            wallpaper = wallpaper,
                            onItemClick = { navigateToWallpaper(wallpaper.id) },
                        )
                    }
                }
            }

            is Result.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: ${result.message}", modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }


//    LazyVerticalStaggeredGrid(
//        columns = StaggeredGridCells.Fixed(2),
//        verticalItemSpacing = 8.dp,
//        flingBehavior = ScrollableDefaults.flingBehavior(),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 12.dp)
//            .nestedScroll(scrollBehavior.nestedScrollConnection)
//            .padding(top = padding.calculateTopPadding())
//    ) {
//        item(span = StaggeredGridItemSpan.FullLine) {
//            Column(modifier = Modifier.fillMaxWidth()) {
//                AICard(onCardClick = onCardClick)
//                Spacer(modifier = Modifier.height(12.dp))
//                Column(modifier = Modifier.fillMaxWidth()) {
//                    Text(
//                        text = "Discover",
//                        fontFamily = salsaFontFamily,
//                        fontWeight = FontWeight.Medium,
//                        style = MaterialTheme.typography.headlineLarge,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//                    Text(
//                        text = "Discover the best wallpapers",
//                        fontFamily = salsaFontFamily,
//                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
//                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
//                    )
//                    Spacer(modifier = Modifier.height(12.dp))
//                    ScrollTab(categories = categories, selectedIndex = selectedIndex) {
//                        selectedIndex = it
//                        viewModel.fetchWallpapers(categories[it])
//                    }
//                }
//            }
//        }
//
//        when (val result = wallpapersState) {
//            is Result.Loading -> {
//                item(span = StaggeredGridItemSpan.FullLine) {
//                    Box(modifier = Modifier.fillMaxWidth()) {
//                        CircularProgressIndicator(
//                            modifier = Modifier.align(Alignment.Center)
//                        )
//                    }
//                }
//            }
//
//            is Result.Success -> {
//                items(result.data!!) { wallpaper ->
//                    WallGridItem(
//                        wallpaper = wallpaper,
//                        onItemClick = { navigateToWallpaper(wallpaper.id) },
//                    )
//                }
//            }
//
//            is Result.Error -> {
//                item(span = StaggeredGridItemSpan.FullLine) {
//                    Box(
//                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = "Error: ${result.message}", modifier = Modifier.padding(16.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
}
