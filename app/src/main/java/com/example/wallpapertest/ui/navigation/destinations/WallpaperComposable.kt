package com.example.wallpapertest.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wallpapertest.ui.Constants
import com.example.wallpapertest.ui.screens.wallpaper.main.WallpaperScreen
import com.example.wallpapertest.ui.screens.wallpaper.main.WallpaperViewModel

fun NavGraphBuilder.wallpaperComposable(
    navigateToHomeScreen: () -> Unit, wallpaperViewModel: WallpaperViewModel
) {
    composable(
        route = "wallpaper/{${Constants.WALLPAPER_ARGUMENT_KEY}}",
        arguments = listOf(navArgument(Constants.WALLPAPER_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val imageId = backStackEntry.arguments?.getString(Constants.WALLPAPER_ARGUMENT_KEY)!!
        WallpaperScreen(
            imageId = imageId,
            navigateBack = navigateToHomeScreen,
            wallpaperViewModel = wallpaperViewModel
        )
    }
}
