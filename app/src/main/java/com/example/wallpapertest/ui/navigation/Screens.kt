package com.example.wallpapertest.ui.navigation

import androidx.navigation.NavController

class Screens(navController: NavController) {

    val splash: () -> Unit = {
        navController.navigate(route = "home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    val home: (String) -> Unit = { imageId ->
        navController.navigate(route = "wallpaper/$imageId")
    }

    val wallpaper: () -> Unit = {
        navController.navigate(route = "home")
    }

    val gemini: () -> Unit = {
        navController.navigate(route = "gemini")
    }
    val search: () -> Unit = {
        navController.navigate(route = "search")
    }
}
