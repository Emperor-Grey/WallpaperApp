package com.example.wallpapertest.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapertest.domain.model.WallpaperItem
import com.example.wallpapertest.domain.repository.WallpaperRepository
import com.example.wallpapertest.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WallpaperRepository
) : ViewModel() {
    private val _wallpaper = MutableStateFlow<Result<List<WallpaperItem>>>(Result.Loading())
    val wallpaper: StateFlow<Result<List<WallpaperItem>>> = _wallpaper

    fun fetchWallpapers(category: String, page: Int = 1) {
        viewModelScope.launch {
            when (category) {
                "Random" -> repository.getRandomWallpapers(page)
                "Latest" -> repository.getLatestWallpapers(page)
                "Popular" -> repository.getPopularWallpapers(page)
                "Featured" -> repository.getFeaturedWallpapers(page)
                "Hot" -> repository.getHotWallpapers(page)
                "MostViewed" -> repository.getMostViewedWallpapers(page)
                else -> return@launch
            }.collect {
                _wallpaper.value = it
            }
        }
    }
}
