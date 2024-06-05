package com.example.wallpapertest.navigation

import androidx.navigation.NavController

class Screens(navController: NavController) {

    val splash: () -> Unit = {
        navController.navigate(route = "home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    val home: () -> Unit = {
//        navController.navigate(route = "details/{id}")
    }

}
