package com.example.wallpapertest.ui.screens.wallpaper.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.wallpapertest.data.utils.helpers.downloadImage
import com.example.wallpapertest.data.utils.helpers.setAsWallpaper
import kotlinx.coroutines.launch

@Composable
fun WallpaperScreen(
    imageId: String, navigateBack: () -> Unit, wallpaperViewModel: WallpaperViewModel
) {
    val wallpaperState by wallpaperViewModel.wallpaperState.collectAsState()

    var showButtons by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(imageId) {
        wallpaperViewModel.loadWallpaper(imageId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        WallpaperContent(
            wallpaperState = wallpaperState,
            toggleShowButtons = { showButtons = !showButtons })

        WallpaperControls(navigateBack = navigateBack,
            showButtons = showButtons,
            context = context,
            onSetWallpaperClick = {
                coroutineScope.launch {
                    wallpaperState.wallpaper?.data?.path?.let {
                        setAsWallpaper(
                            context, imageUrl = it
                        )
                    }
                }
            },
            onPreviewClick = {
                Toast.makeText(context, "Preview Clicked", Toast.LENGTH_SHORT).show()
            },
            onDownloadClick = {
                coroutineScope.launch {
                    try {
                        wallpaperState.wallpaper?.data?.path?.let {
                            downloadImage(
                                imageUrl = it, context = context
                            )
                        }
                    } catch (e: Exception) {
                        // Handle exception or show error message
                        e.printStackTrace()
                        Log.d("WallpaperScreen", "Got error $e")
                    }
                }
            })
    }
}