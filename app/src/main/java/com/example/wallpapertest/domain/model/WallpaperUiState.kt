package com.example.wallpapertest.domain.model

data class WallpaperUiState(
    val isLoading: Boolean = false,
    val wallpaper: WallpaperItem? = null,
    val errorMessage: String? = null
)
