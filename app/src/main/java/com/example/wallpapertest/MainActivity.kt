package com.example.wallpapertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wallpapertest.ui.screens.home.HomeViewModel
import com.example.wallpapertest.ui.screens.wallpaper.WallpaperViewModel
import com.example.wallpapertest.ui.theme.WallpaperTestTheme
import com.example.wallpapertest.utils.helpers.RequestMultiplePermissions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val homeViewModel: HomeViewModel by viewModels()
    private val wallpaperViewModel: WallpaperViewModel by viewModels()

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT
            ),
        )
        setContent {
            navController = rememberNavController()
            WallpaperTestTheme {
                RequestMultiplePermissions(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    wallpaperViewModel = wallpaperViewModel,
                    permissions = listOf(
                        android.Manifest.permission.SET_WALLPAPER,
                        android.Manifest.permission.READ_MEDIA_IMAGES,
                        android.Manifest.permission.POST_NOTIFICATIONS
                    )
                )
            }
        }
    }
}
