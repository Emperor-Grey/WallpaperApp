package com.example.wallpapertest.data.remote.network.response

import com.example.wallpapertest.domain.model.MetaData
import com.example.wallpapertest.domain.model.WallpaperItem
import com.google.gson.annotations.SerializedName

data class WallpaperResponse(
    @SerializedName("data") val data: List<WallpaperItem>,
    @SerializedName("meta") val meta: MetaData
)
