package com.example.wallpapertest.data.di

import com.example.wallpapertest.data.remote.network.api.WallHeavenApi
import com.example.wallpapertest.data.repository.WallpaperRepositoryImpl
import com.example.wallpapertest.domain.repository.WallpaperRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(WallHeavenApi.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
    }

    @Provides
    @Singleton
    fun provideWallHeavenApi(retrofit: Retrofit): WallHeavenApi {
        return retrofit.create(WallHeavenApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWallpaperRepository(wallHeavenApi: WallHeavenApi): WallpaperRepository {
        return WallpaperRepositoryImpl(wallHeavenApi)
    }
}
