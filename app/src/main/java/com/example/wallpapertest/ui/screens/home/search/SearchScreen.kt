package com.example.wallpapertest.ui.screens.home.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen() {
    var isActive by remember {
        mutableStateOf(false)
    }
    var query by remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        MySearchBar(modifier = Modifier,
            placeHolderText = { Text("Enter Anything that you wanna search...") },
            query = query,
            onQueryChange = {
                query = it
            },
            onSearch = {},
            active = isActive,
            onActiveChange = {
                isActive = it
            })
    }) { padding ->
        val padding = padding
//        WallGridItem(wallpaper = WallpaperItem(
//            id = "123456",
//            url = "https://example.com/wallpapers/123456",
//            shortUrl = "https://example.com/w/123456",
//            uploader = Uploader(
//                username = "wallpaperFan", group = "premium"
//            ),
//            views = 5000,
//            favorites = 1500,
//            source = "example.com",
//            purity = "sfw",
//            category = "nature",
//            dimension_x = 1920,
//            dimension_y = 1080,
//            resolution = "1920x1080",
//            ratio = "16:9",
//            file_size = 2048000,
//            file_type = "image/jpeg",
//            created_at = "2024-07-09T12:00:00Z",
//            colors = listOf("#FFFFFF", "#000000", "#FF5733"),
//            path = "https://cdn.dribbble.com/userupload/4776751/file/original-da96b413619298d1e6accea9665c4d66.png?resize=752x"
//        ), onItemClick = {})
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Still in progress", style = MaterialTheme.typography.headlineLarge)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    modifier: Modifier,
    placeHolderText: @Composable () -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
) {

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        Modifier
            .fillMaxWidth()
            .semantics { isTraversalGroup = true }) {
        SearchBar(
            modifier = modifier
                .align(Alignment.TopCenter)
                .focusRequester(focusRequester)
                .padding(top = 8.dp)
                .semantics {
                    traversalIndex = 0f
                },
            placeholder = placeHolderText,
            query = query,
            enabled = true,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            active = active,
            onActiveChange = onActiveChange
        ) {
            // Suggestions Shit
        }
    }
}
