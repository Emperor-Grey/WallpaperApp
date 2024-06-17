package com.example.wallpapertest.data.remote.network.response

import com.example.wallpapertest.domain.model.MetaData
import com.example.wallpapertest.domain.model.WallpaperItem

data class WallpaperResponse(
    val data: List<WallpaperItem>, val meta: MetaData
)
