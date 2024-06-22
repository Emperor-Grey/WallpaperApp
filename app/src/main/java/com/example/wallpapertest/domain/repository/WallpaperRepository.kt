package com.example.wallpapertest.domain.repository

import com.example.wallpapertest.data.remote.network.response.WallpaperDetailsResponse
import com.example.wallpapertest.domain.model.WallpaperItem
import com.example.wallpapertest.utils.Result
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {

    suspend fun getWallpaperById(wallpaperId: String): Flow<Result<WallpaperDetailsResponse>>

    suspend fun getRandomWallpapers(page: Int = 1): Flow<Result<List<WallpaperItem>>>

    suspend fun getHotWallpapers(page: Int = 1): Flow<Result<List<WallpaperItem>>>

    suspend fun getPopularWallpapers(page: Int = 1): Flow<Result<List<WallpaperItem>>>

    suspend fun getFeaturedWallpapers(page: Int = 1): Flow<Result<List<WallpaperItem>>>

    suspend fun getMostViewedWallpapers(page: Int = 1): Flow<Result<List<WallpaperItem>>>

    suspend fun getLatestWallpapers(page: Int = 1): Flow<Result<List<WallpaperItem>>>
}
