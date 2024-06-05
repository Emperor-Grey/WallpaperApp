package com.example.wallpapertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wallpapertest.navigation.SetupNavigation
import com.example.wallpapertest.ui.theme.WallpaperTestTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            WallpaperTestTheme {
                SetupNavigation(navController = navController)
            }
        }
    }
}
