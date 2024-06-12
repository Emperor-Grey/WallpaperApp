package com.example.wallpapertest.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(navigateToWallpaper: (Int) -> Unit) {
    Scaffold(topBar = {
        HomeTopAppBar()
    }, floatingActionButton = {
        SearchFab()
    }, containerColor = MaterialTheme.colorScheme.background) {padding->
        HomeContent(padding, navigateToWallpaper = navigateToWallpaper)
    }
}

@Composable
fun SearchFab() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(navigateToWallpaper = {})
}
