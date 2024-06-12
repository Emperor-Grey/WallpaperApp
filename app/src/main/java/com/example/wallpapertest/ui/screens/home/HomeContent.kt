package com.example.wallpapertest.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.wallpapertest.components.AICard
import com.example.wallpapertest.components.ScrollTab
import com.example.wallpapertest.components.WallGrid
import com.example.wallpapertest.ui.theme.salsaFontFamily

@Composable
fun HomeContent(padding: PaddingValues, navigateToWallpaper: (Int) -> Unit) {
    val categories = listOf("Random", "Popular", "Featured", "Anime", "Nature")
    val selectedIndex = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        AICard()
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
        WallGrid(onItemClick = navigateToWallpaper)
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeContentPrev() {
    HomeContent(padding = PaddingValues(Dp.Unspecified), navigateToWallpaper = {})
}
