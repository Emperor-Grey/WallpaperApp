package com.example.wallpapertest.utils.helpers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wallpapertest.navigation.SetupNavigation
import com.example.wallpapertest.ui.screens.home.HomeViewModel
import com.example.wallpapertest.ui.screens.wallpaper.WallpaperViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@ExperimentalPermissionsApi
@Composable
fun RequestMultiplePermissions(
    permissions: List<String>,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    wallpaperViewModel: WallpaperViewModel,
) {
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)

    HandleRequests(multiplePermissionsState = multiplePermissionsState,
        deniedContent = { shouldShowRationale ->
            PermissionDeniedContent(
                shouldShowRationale = shouldShowRationale,
                onRequestPermission = { multiplePermissionsState.launchMultiplePermissionRequest() },
                navController = navController,
                homeViewModel = homeViewModel,
                wallpaperViewModel = wallpaperViewModel
            )
        },
        content = {
            SetupNavigation(
                navController = navController,
                homeViewModel = homeViewModel,
                wallpaperViewModel = wallpaperViewModel
            )
        })
}

@ExperimentalPermissionsApi
@Composable
private fun HandleRequests(
    multiplePermissionsState: MultiplePermissionsState,
    deniedContent: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    if (multiplePermissionsState.allPermissionsGranted) {
        content()
    } else {
        deniedContent(multiplePermissionsState.shouldShowRationale)
    }
}

@Composable
fun PermissionDeniedContent(
    shouldShowRationale: Boolean,
    onRequestPermission: () -> Unit,
    // Don't remove this because iam using it above
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    wallpaperViewModel: WallpaperViewModel,
) {
    if (shouldShowRationale) {
        AlertDialog(onDismissRequest = {},
            title = {
                Text(
                    text = "Permission Request", style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = { Text("To use this app's functionalities, you need to give us the permission.") },
            confirmButton = {
                Button(onClick = onRequestPermission) {
                    Text("Give Permission")
                }
            })
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Give this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onRequestPermission) {
                Text("Request")
            }
        }
    }
}
