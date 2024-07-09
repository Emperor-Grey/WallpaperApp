package com.example.wallpapertest.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapertest.ui.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val showSplash: StateFlow<Boolean> =
        dataStoreManager.showSplash.stateIn(viewModelScope, SharingStarted.Eagerly, true)

    fun setShowSplash(show: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setShowSplash(show)
        }
    }
}
