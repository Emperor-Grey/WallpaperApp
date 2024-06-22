package com.example.wallpapertest.ui.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapertest.ui.theme.salsaFontFamily

@Composable
fun ScrollTab(categories: List<String>, selectedIndex: Int, onTabSelected: (Int) -> Unit) {
    Column {
        LazyRow {
            items(categories.size) { index ->
                val isSelected = index == selectedIndex
                Card(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(
                            onClick = {
                                onTabSelected(index)
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true),
                        ), colors = CardDefaults.cardColors(
                        if (isSelected) MaterialTheme.colorScheme.primaryContainer
                        else Color.Gray.copy(
                            alpha = 0.15f
                        )
                    )
                ) {
                    Text(
                        categories[index],
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = salsaFontFamily,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScrollTabPrev() {
    val categories = listOf("Random", "Popular", "Featured", "Anime", "Nature")
    val selectedIndex = remember { mutableIntStateOf(0) }

    ScrollTab(categories = categories, selectedIndex = selectedIndex.intValue) {
        selectedIndex.intValue = it
    }
}
