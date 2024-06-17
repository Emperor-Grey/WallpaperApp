package com.example.wallpapertest.data.repository

import coil.network.HttpException
import com.example.wallpapertest.data.remote.network.api.WallHeavenApi
import com.example.wallpapertest.domain.model.WallpaperItem
import com.example.wallpapertest.domain.repository.WallpaperRepository
import com.example.wallpapertest.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(private val wallpaperService: WallHeavenApi) :
    WallpaperRepository {

//    private val wallpaperService: WallHeavenApi = retrofitInstance.getWallpaperService()

    override suspend fun getWallpaperById(wallpaperId: String): Flow<Result<WallpaperItem>> {
        return flow {
            emit(Result.Loading())
            val wallpaper = try {
                wallpaperService.getWallpaperById(wallpaperId = wallpaperId)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpaper"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpaper"))
                return@flow
            }
            emit(Result.Success(data = wallpaper))
        }
    }

    override suspend fun getRandomWallpapers(page: Int): Flow<Result<List<WallpaperItem>>> {
        return flow {
            emit(Result.Loading())
            val wallpapers = try {
                wallpaperService.getRandomWallpapers(page = page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            }
            emit(Result.Success(data = wallpapers.data))
        }
    }

    override suspend fun getHotWallpapers(page: Int): Flow<Result<List<WallpaperItem>>> {
        return flow {
            emit(Result.Loading())
            val wallpapers = try {
                wallpaperService.getHotWallpapers(page = page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            }
            emit(Result.Success(data = wallpapers.data))
        }
    }

    override suspend fun getPopularWallpapers(page: Int): Flow<Result<List<WallpaperItem>>> {
        return flow {
            emit(Result.Loading())
            val wallpapers = try {
                wallpaperService.getPopularWallpapers(page = page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            }
            emit(Result.Success(data = wallpapers.data))
        }
    }

    override suspend fun getFeaturedWallpapers(page: Int): Flow<Result<List<WallpaperItem>>> {
        return flow {
            emit(Result.Loading())
            val wallpapers = try {
                wallpaperService.getFeaturedWallpapers(page = page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            }
            emit(Result.Success(data = wallpapers.data))
        }
    }

    override suspend fun getMostViewedWallpapers(page: Int): Flow<Result<List<WallpaperItem>>> {
        return flow {
            emit(Result.Loading())
            val wallpapers = try {
                wallpaperService.getMostViewedWallpapers(page = page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            }
            emit(Result.Success(data = wallpapers.data))
        }
    }

    override suspend fun getLatestWallpapers(page: Int): Flow<Result<List<WallpaperItem>>> {
        return flow {
            emit(Result.Loading())
            val wallpapers = try {
                wallpaperService.getLatestWallpapers(page = page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error getting wallpapers"))
                return@flow
            }
            emit(Result.Success(data = wallpapers.data))
        }
    }
}
