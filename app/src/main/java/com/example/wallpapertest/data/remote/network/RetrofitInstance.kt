package com.example.wallpapertest.data.remote.network

import com.example.wallpapertest.data.remote.network.api.WallHeavenApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// We are using dependency injection with dagger hilt
// if not then we can use this class
class RetrofitInstance {
    private val httpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(WallHeavenApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(httpClient).build()
    }

    val instance = getRetrofitInstance()
    fun getWallpaperService(): WallHeavenApi {
        return getRetrofitInstance().create(WallHeavenApi::class.java)
    }
}
