package com.example.wallpapertest.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.wallpapertest.ui.screens.home.HomeScreen

fun NavGraphBuilder.homeComposable() {
    composable(route = "home") {
        HomeScreen()
    }
}
