package com.example.wallpapertest.data.remote.network.api

import com.example.wallpapertest.data.remote.network.response.WallpaperResponse
import com.example.wallpapertest.domain.model.WallpaperItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WallHeavenApi {
    companion object {
        const val BASE_URL = "https://wallhaven.cc/api/v1/"
    }

    // https://wallhaven.cc/api/v1/w/6d6k3w  6d6k3w ---> wallpaperId
    @GET("w/{wallpaperId}")
    suspend fun getWallpaperById(
        @Path("wallpaperId") wallpaperId: String, @Query("page") page: Int = 1
    ): WallpaperItem

    // https://wallhaven.cc/api/v1/search?sorting=random
    @GET("search")
    suspend fun getRandomWallpapers(
        @Query("sorting") sorting: String = "random", @Query("page") page: Int = 1
    ): WallpaperResponse

    // https://wallhaven.cc/api/v1/search?sorting=hot
    @GET("search")
    suspend fun getHotWallpapers(
        @Query("sorting") sorting: String = "hot", @Query("page") page: Int = 1
    ): WallpaperResponse

    // https://wallhaven.cc/api/v1/search?sorting=toplist
    @GET("search")
    suspend fun getPopularWallpapers(
        @Query("sorting") sorting: String = "toplist", @Query("page") page: Int = 1
    ): WallpaperResponse

    // https://wallhaven.cc/api/v1/search?sorting=favorites
    @GET("search")
    suspend fun getFeaturedWallpapers(
        @Query("sorting") sorting: String = "favorites", @Query("page") page: Int = 1
    ): WallpaperResponse

    // https://wallhaven.cc/api/v1/search?sorting=views
    @GET("search")
    suspend fun getMostViewedWallpapers(
        @Query("sorting") sorting: String = "views", @Query("page") page: Int = 1
    ): WallpaperResponse

    // https://wallhaven.cc/api/v1/search?sorting=date_added
    @GET("search")
    suspend fun getLatestWallpapers(
        @Query("sorting") sorting: String = "date_added", @Query("page") page: Int = 1
    ): WallpaperResponse

}
