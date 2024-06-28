package com.example.wallpapertest.ui.screens.home

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToWallpaper: (String) -> Unit,
    homeViewModel: HomeViewModel,
    navigateToGemini: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        HomeTopAppBar(scrollBehavior)
    }, floatingActionButton = {
        SearchFab(onFabClick = {
            Toast.makeText(context, "Not yet Implemented", Toast.LENGTH_SHORT).show()
        })
    }, containerColor = MaterialTheme.colorScheme.background) {
        HomeContent(
            padding = it,
            navigateToWallpaper = navigateToWallpaper,
            scrollBehavior = scrollBehavior,
            viewModel = homeViewModel,
            onCardClick = navigateToGemini
        )
    }
}

@Composable
fun SearchFab(onFabClick: () -> Unit) {
    FloatingActionButton(onClick = onFabClick) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
    }
}
