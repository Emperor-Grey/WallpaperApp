package com.example.wallpapertest.domain.model

data class WallpaperItem(
    val id: String,
    val category: String,  // really important
    val colors: List<String>,
    val created_at: String,
    val dimension_x: Int,
    val dimension_y: Int,
    val favorites: Int,
    val file_type: String,
    val path: String,  // contains the wallpaper image
    val resolution: String,
    val views: Int
)
