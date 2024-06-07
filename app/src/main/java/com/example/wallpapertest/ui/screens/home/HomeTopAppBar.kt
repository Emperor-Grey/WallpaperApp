package com.example.wallpapertest.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallpapertest.R
import com.example.wallpapertest.ui.theme.salsaFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                NavigationIcon()
                Spacer(modifier = Modifier.width(16.dp))
                HomeTopAppBarText()
            }
        },
        actions = {
            NotificationAction(onNotificationClicked = {}, hasNotification = true)
        })
}

@Composable
fun HomeTopAppBarText() {
    Column {
        Text(
            text = "Welcome,",
            fontFamily = salsaFontFamily,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
        Text(
            text = "King Grey",
            fontFamily = salsaFontFamily,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun NavigationIcon() {
    Image(
        painter = painterResource(R.drawable.profile_avatar),
        contentDescription = "Contact profile picture",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationAction(
    onNotificationClicked: () -> Unit, hasNotification: Boolean? = null
) {
    Box(modifier = Modifier.padding(end = 12.dp)) {
        if (hasNotification!!) {
            Badge(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(10.dp)
            )
            IconButton(onClick = onNotificationClicked) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier.size(42.dp)
                )
            }
        } else {
            IconButton(onClick = onNotificationClicked) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier.size(42.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeTopAppBarPrev() {
    HomeTopAppBar()
}
