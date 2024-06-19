package com.example.wallpapertest.domain.model

data class WallpaperItem(
    val id: String,
    val url: String,
    val shortUrl: String,
    val uploader: Uploader,
    val views: Int,
    val favorites: Int,
    val source: String,
    val purity: String,
    val category: String,
    val dimension_x: Int,
    val dimension_y: Int,
    val resolution: String,
    val ratio: String,
    val file_size: Int,
    val file_type: String,
    val created_at: String,
    val colors: List<String>,
    val path: String
)

data class Uploader(
    val username: String, val group: String
)