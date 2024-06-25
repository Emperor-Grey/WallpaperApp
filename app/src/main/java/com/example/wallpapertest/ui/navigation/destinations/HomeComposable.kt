package com.example.wallpapertest.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.wallpapertest.ui.screens.home.HomeScreen
import com.example.wallpapertest.ui.screens.home.HomeViewModel

fun NavGraphBuilder.homeComposable(
    navigateToWallpaper: (String) -> Unit,
    homeViewModel: HomeViewModel
) {
    composable(route = "home") {
        HomeScreen(navigateToWallpaper = navigateToWallpaper, homeViewModel = homeViewModel)
    }
}
