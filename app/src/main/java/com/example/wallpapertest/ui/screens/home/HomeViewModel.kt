package com.example.wallpapertest.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapertest.data.utils.Result
import com.example.wallpapertest.domain.model.WallpaperItem
import com.example.wallpapertest.domain.repository.WallpaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val _selectedIndex = mutableIntStateOf(0)
    val selectedIndex: MutableState<Int> = _selectedIndex

    private val _isDataFetched = MutableStateFlow(false)
    val isDataFetched: StateFlow<Boolean> = _isDataFetched

    private val _categories = mutableStateOf(
        listOf("Random", "Latest", "Popular", "Featured", "Hot", "MostViewed")
    )
    val categories: MutableState<List<String>> = _categories

    private var currentCategory: String? = null

    fun fetchWallpapers(category: String, page: Int = 1) {
        if (!_isDataFetched.value || currentCategory != category) {
            currentCategory = category
            viewModelScope.launch(Dispatchers.IO) {
                val wallpaperResult = when (category) {
                    "Random" -> repository.getRandomWallpapers(page)
                    "Latest" -> repository.getLatestWallpapers(page)
                    "Popular" -> repository.getPopularWallpapers(page)
                    "Featured" -> repository.getFeaturedWallpapers(page)
                    "Hot" -> repository.getHotWallpapers(page)
                    "MostViewed" -> repository.getMostViewedWallpapers(page)
                    else -> return@launch
                }

                wallpaperResult.collect {
                    _wallpaper.value = it
                    _isDataFetched.value = true
                }
            }
        }
    }
}
