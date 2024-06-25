package com.example.wallpapertest.ui.screens.wallpaper

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapertest.domain.model.WallpaperUiState
import com.example.wallpapertest.domain.repository.WallpaperRepository
import com.example.wallpapertest.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val repository: WallpaperRepository
) : ViewModel() {
    private val _wallpaperState = MutableStateFlow(WallpaperUiState())
    val wallpaperState: StateFlow<WallpaperUiState> = _wallpaperState.asStateFlow()

    fun loadWallpaper(wallpaperId: String) {
        viewModelScope.launch {
            repository.getWallpaperById(wallpaperId = wallpaperId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _wallpaperState.update {
                            it.copy(
                                isLoading = false, wallpaper = result.data
                            )
                        }
                        Log.d("WallpaperViewModel", "Wallpaper loaded successfully: ${result.data}")
                    }

                    is Result.Error -> {
                        _wallpaperState.update {
                            it.copy(
                                isLoading = false, errorMessage = result.message
                            )
                        }
                        Log.e("WallpaperViewModel", "Failed to load wallpaper: ${result.message}")
                    }

                    is Result.Loading -> {
                        _wallpaperState.update { it.copy(isLoading = true) }
                        Log.d("WallpaperViewModel", "Loading wallpaper...")
                    }
                }
            }
        }
    }
}
