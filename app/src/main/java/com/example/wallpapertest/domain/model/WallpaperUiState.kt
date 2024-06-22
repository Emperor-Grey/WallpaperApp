package com.example.wallpapertest.domain.model

import com.example.wallpapertest.data.remote.network.response.WallpaperDetailsResponse

data class WallpaperUiState(
    val isLoading: Boolean = false,
    val wallpaper: WallpaperDetailsResponse? = null,
    val errorMessage: String? = null
)
