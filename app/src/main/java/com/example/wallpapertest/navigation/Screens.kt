package com.example.wallpapertest.navigation

import androidx.navigation.NavController

class Screens(navController: NavController) {

    val splash: () -> Unit = {
        navController.navigate(route = "home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    val home: (Int) -> Unit = { imageId ->
        navController.navigate(route = "wallpaper/$imageId")
    }

    val wallpaper: () -> Unit = {
        navController.navigate(route = "home")
    }


}
