package com.example.wallpapertest.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToWallpaper: (Int) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        HomeTopAppBar(scrollBehavior)
    }, floatingActionButton = {
        SearchFab()
    }, containerColor = MaterialTheme.colorScheme.background) {
        HomeContent(padding = it, navigateToWallpaper = navigateToWallpaper,scrollBehavior)
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
