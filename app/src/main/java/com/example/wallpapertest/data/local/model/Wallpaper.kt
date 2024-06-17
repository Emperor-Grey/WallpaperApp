package com.example.wallpapertest.data.local.model

import com.example.wallpapertest.domain.model.MetaData
import com.example.wallpapertest.domain.model.WallpaperItem

data class WallpapersEntity(
    val data: List<WallpaperItem>, val meta: MetaData
)
