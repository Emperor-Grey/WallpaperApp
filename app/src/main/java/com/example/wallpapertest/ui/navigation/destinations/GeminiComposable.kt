package com.example.wallpapertest.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.wallpapertest.ui.screens.gemini.GeminiScreen


fun NavGraphBuilder.geminiComposable() {
    composable(route = "gemini") {
        GeminiScreen()
    }
}
