package com.example.wallpapertest.ui.screens.wallpaper

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallpapertest.R
import com.example.wallpapertest.ui.theme.salsaFontFamily
import com.example.wallpapertest.utils.helpers.NotificationHelper
import com.example.wallpapertest.utils.helpers.downloadImage
import com.example.wallpapertest.utils.helpers.setAsWallpaper
import kotlinx.coroutines.launch

@Composable
fun WallpaperScreen(imageId: Int, navigateBack: () -> Unit) {
    var showButtons by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val notificationHelper = remember { NotificationHelper(context) }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { showButtons = !showButtons },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() })
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Wallpaper Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 22.dp)
        ) {
            UpperButtons(navigateBack = navigateBack, showButtons = showButtons, onFavClick = {})
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = showButtons, enter = slideInVertically(initialOffsetY = { it }) + fadeIn(
                    animationSpec = tween(durationMillis = 500)
                ), exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(
                    animationSpec = tween(durationMillis = 500)
                )
            ) {
                LowerButtons(onSetWallpaperClick = {
                    coroutineScope.launch {
                        setAsWallpaper(context, imageId)
                    }
                }, onPreviewClick = {}, onDownloadClick = {
                    coroutineScope.launch {
                        downloadImage(
                            imageId = imageId,
                            context = context,
                            notificationHelper = notificationHelper
                        )
                    }
                })
            }
        }
    }
}

@Composable
fun LowerButtons(
    onSetWallpaperClick: () -> Unit, onPreviewClick: () -> Unit, onDownloadClick: () -> Unit
) {
    val buttonHeight = 48.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Button(
            onClick = onSetWallpaperClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.primaryContainer.copy(1f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Set as Wallpaper",
                fontFamily = salsaFontFamily,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onPreviewClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8f)
                    .height(buttonHeight),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primaryContainer.copy(1f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 5.dp),
                    text = "Preview",
                    fontFamily = salsaFontFamily,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            FilledTonalIconButton(
                onClick = onDownloadClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .height(buttonHeight),
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    MaterialTheme.colorScheme.primaryContainer.copy(1f)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.download),
                    contentDescription = "download Icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 22.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun UpperButtons(navigateBack: () -> Unit, showButtons: Boolean, onFavClick: () -> Unit) {
    AnimatedVisibility(
        visible = showButtons, enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(
            animationSpec = tween(durationMillis = 500)
        ), exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledTonalIconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }

            FilledTonalIconButton(
                onClick = onFavClick,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(8.dp),
                    contentDescription = "Heart Icon"
                )
            }
        }
    }
}

@Preview
@Composable
private fun WallpaperScreenPrev() {
    WallpaperScreen(imageId = R.drawable.cat, navigateBack = {})
}
