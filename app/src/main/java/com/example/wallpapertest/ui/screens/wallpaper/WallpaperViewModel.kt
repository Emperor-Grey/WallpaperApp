package com.example.wallpapertest.ui.screens.wallpaper

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapertest.domain.model.WallpaperItem
import com.example.wallpapertest.domain.repository.WallpaperRepository
import com.example.wallpapertest.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val repository: WallpaperRepository
) : ViewModel() {
    private val _wallpaper = MutableStateFlow<Result<WallpaperItem>>(Result.Loading())
    val wallpaper: StateFlow<Result<WallpaperItem>> = _wallpaper.asStateFlow()

    fun loadWallpaper(wallpaperId: String) {
        viewModelScope.launch {
            repository.getWallpaperById(wallpaperId = wallpaperId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _wallpaper.value = Result.Success(data = result.data)
                        Log.d("WallpaperViewModel", "Wallpaper loaded successfully: ${result.data}")
                    }

                    is Result.Error -> {
                        Log.e("WallpaperViewModel", "Failed to load wallpaper: ${result.message}")
                    }

                    is Result.Loading -> {
                        Log.d("WallpaperViewModel", "Loading wallpaper...")
                    }
                }
            }
        }
    }
}
