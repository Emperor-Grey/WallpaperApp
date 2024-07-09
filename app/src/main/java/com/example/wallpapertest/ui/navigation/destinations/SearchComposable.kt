package com.example.wallpapertest.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.wallpapertest.ui.screens.home.search.SearchScreen

fun NavGraphBuilder.searchComposable() {
    composable(route = "search") {
        SearchScreen()
    }
}
