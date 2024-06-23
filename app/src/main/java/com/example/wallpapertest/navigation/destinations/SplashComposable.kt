package com.example.wallpapertest.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.wallpapertest.ui.screens.splash.SplashScreen

fun NavGraphBuilder.splashComposable(
    navigateToHomeScreen: () -> Unit
) {
    composable(route = "splash") {
        SplashScreen(navigateToHomeScreen = navigateToHomeScreen)
    }
}
