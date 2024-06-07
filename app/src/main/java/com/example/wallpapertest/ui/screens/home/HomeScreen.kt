package com.example.wallpapertest.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        HomeTopAppBar()
    }, containerColor = MaterialTheme.colorScheme.background) {
        HomeContent(modifier = Modifier.padding(it))
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}
