package com.example.wallpapertest

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wallpapertest.data.utils.helpers.RequestMultiplePermissions
import com.example.wallpapertest.ui.screens.home.HomeViewModel
import com.example.wallpapertest.ui.screens.wallpaper.main.WallpaperViewModel
import com.example.wallpapertest.ui.theme.WallpaperTestTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val homeViewModel: HomeViewModel by viewModels()
    private val wallpaperViewModel: WallpaperViewModel by viewModels()

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
                val context = LocalContext.current

                RequestMultiplePermissions(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    wallpaperViewModel = wallpaperViewModel,
                    context = context,
                    permissions = listOfNotNull(
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.SET_WALLPAPER,
                        Manifest.permission.INTERNET,
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.POST_NOTIFICATIONS else null
                    )
                )
            }
        }
    }
}
