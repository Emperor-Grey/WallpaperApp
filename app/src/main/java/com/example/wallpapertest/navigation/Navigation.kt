package com.example.wallpapertest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.wallpapertest.navigation.destinations.homeComposable
import com.example.wallpapertest.navigation.destinations.splashComposable
import com.example.wallpapertest.navigation.destinations.wallpaperComposable


@Composable
fun SetupNavigation(navController: NavHostController) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = "splash") {
        splashComposable(navigateToHomeScreen = screen.splash)
        homeComposable(navigateToWallpaper = screen.home)
        wallpaperComposable(navigateToHomeScreen = screen.wallpaper)
    }
}
