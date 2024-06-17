package com.example.wallpapertest.di

import com.example.wallpapertest.data.remote.network.RetrofitInstance
import com.example.wallpapertest.data.repository.WallpaperRepositoryImpl
import com.example.wallpapertest.domain.repository.WallpaperRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(): RetrofitInstance {
        return RetrofitInstance()
    }

    @Provides
    @Singleton
    fun provideWallpaperRepository(retrofitInstance: RetrofitInstance): WallpaperRepository {
        return WallpaperRepositoryImpl(retrofitInstance)
    }
}
