package com.example.wallpapertest.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wallpapertest.R
import com.example.wallpapertest.ui.screens.wallpaper.WallpaperScreen

fun NavGraphBuilder.wallpaperComposable(navigateToHomeScreen: () -> Unit) {
    composable(route = "wallpaper/{imageId}", arguments = listOf(navArgument("imageId") {
        type = NavType.IntType
    })) { backStackEntry ->
        val imageId = backStackEntry.arguments?.getInt("imageId") ?: R.drawable.img
        WallpaperScreen(imageId = imageId, navigateBack = navigateToHomeScreen)
    }
}
