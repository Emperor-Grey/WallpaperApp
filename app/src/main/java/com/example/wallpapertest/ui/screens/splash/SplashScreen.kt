package com.example.wallpapertest.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wallpapertest.R

@Composable
fun SplashScreen(navigateToHomeScreen: () -> Unit) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.White), startY = 1300f, endY = 1700f
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        SplashContent(navigateToHomeScreen = navigateToHomeScreen)
    }
}

@Preview
@Composable
private fun SplashScreenPrev() {
    SplashScreen(navigateToHomeScreen = {})
}
