package com.example.wallpapertest.domain.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class IconData(
    val iconResId: Int, val contentDescription: String, val position: Offset, val size: Dp = 48.dp
)



