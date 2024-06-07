package com.example.wallpapertest.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapertest.ui.theme.primaryContainerLight
import com.example.wallpapertest.ui.theme.salsaFontFamily

@Composable
fun SplashContent(navigateToHomeScreen: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(10f))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Explore 4k\nWallpapers",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 64.sp),
            fontWeight = FontWeight.Bold,
            fontFamily = salsaFontFamily,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = "Explore, Create, Share\nUltra 4k Wallpapers Now!",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            textAlign = TextAlign.Center,
            fontFamily = salsaFontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Button(
            onClick = navigateToHomeScreen,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp),
            colors = ButtonDefaults.buttonColors(primaryContainerLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 7.dp),
                text = "Start Exploring!",
                fontFamily = salsaFontFamily,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.weight(0.8f))
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashContentPrev() {
    SplashContent(navigateToHomeScreen = {})
}
