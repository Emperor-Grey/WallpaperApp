package com.example.wallpapertest.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wallpapertest.ui.screens.wallpaper.main.WallpaperScreen
import com.example.wallpapertest.ui.screens.wallpaper.main.WallpaperViewModel

fun NavGraphBuilder.wallpaperComposable(
    navigateToHomeScreen: () -> Unit, wallpaperViewModel: WallpaperViewModel
) {
    composable(route = "wallpaper/{imageId}", arguments = listOf(navArgument("imageId") {
        type = NavType.StringType
    })) { backStackEntry ->
        val imageId = backStackEntry.arguments?.getString("imageId")
        WallpaperScreen(
            imageId = imageId!!,
            navigateBack = navigateToHomeScreen,
            wallpaperViewModel = wallpaperViewModel
        )
    }
}
