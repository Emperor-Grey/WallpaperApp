package com.example.wallpapertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wallpapertest.navigation.SetupNavigation
import com.example.wallpapertest.ui.screens.home.HomeViewModel
import com.example.wallpapertest.ui.screens.wallpaper.WallpaperViewModel
import com.example.wallpapertest.ui.theme.WallpaperTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val homeViewModel: HomeViewModel by viewModels()
    private val wallpaperViewModel: WallpaperViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            WallpaperTestTheme {
                // Aurora - Awesome Wallpaper App
                SetupNavigation(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    wallpaperViewModel = wallpaperViewModel
                )
            }
        }
    }
}
