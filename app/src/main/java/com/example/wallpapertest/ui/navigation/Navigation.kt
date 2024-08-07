package com.example.wallpapertest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.wallpapertest.ui.Constants
import com.example.wallpapertest.ui.navigation.destinations.geminiComposable
import com.example.wallpapertest.ui.navigation.destinations.homeComposable
import com.example.wallpapertest.ui.navigation.destinations.searchComposable
import com.example.wallpapertest.ui.navigation.destinations.splashComposable
import com.example.wallpapertest.ui.navigation.destinations.wallpaperComposable
import com.example.wallpapertest.ui.screens.home.HomeViewModel
import com.example.wallpapertest.ui.screens.wallpaper.main.WallpaperViewModel


@Composable
fun SetupNavigation(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    wallpaperViewModel: WallpaperViewModel
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = Constants.START_DESTINATION) {
        splashComposable(navigateToHomeScreen = screen.splash)
        homeComposable(
            navigateToWallpaper = screen.home,
            homeViewModel = homeViewModel,
            navigateToGemini = screen.gemini,
            navigateToSearch = screen.search
        )
        geminiComposable()
        searchComposable()
        wallpaperComposable(
            navigateToHomeScreen = screen.wallpaper, wallpaperViewModel = wallpaperViewModel
        )
    }
}
