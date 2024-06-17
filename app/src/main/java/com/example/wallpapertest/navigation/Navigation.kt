package com.example.wallpapertest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.wallpapertest.navigation.destinations.homeComposable
import com.example.wallpapertest.navigation.destinations.splashComposable
import com.example.wallpapertest.navigation.destinations.wallpaperComposable
import com.example.wallpapertest.ui.screens.home.HomeViewModel


@Composable
fun SetupNavigation(navController: NavHostController, homeViewModel: HomeViewModel,) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = "splash") {
        splashComposable(navigateToHomeScreen = screen.splash)
        homeComposable(navigateToWallpaper = screen.home,homeViewModel = homeViewModel)
        wallpaperComposable(navigateToHomeScreen = screen.wallpaper)
    }
}
