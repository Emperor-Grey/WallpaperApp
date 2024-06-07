package com.example.wallpapertest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallpapertest.R
import com.example.wallpapertest.ui.theme.salsaFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AICard() {
    val brush = Brush.linearGradient(
        listOf(
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.tertiaryContainer
        )
    )

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
    ) {
        val cardHeight = maxHeight * 0.25f
        Card(
            onClick = {}, modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = brush),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.stars),
                        contentDescription = "Start",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Create with AI",
                        fontFamily = salsaFontFamily,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Black,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AICardPrev() {
    AICard()
}
