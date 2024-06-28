package com.example.wallpapertest.data.utils.helpers

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.wallpapertest.ui.navigation.SetupNavigation
import com.example.wallpapertest.ui.screens.home.HomeViewModel
import com.example.wallpapertest.ui.screens.wallpaper.main.WallpaperViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@ExperimentalPermissionsApi
@Composable
fun RequestMultiplePermissions(
    permissions: List<String>,
    deniedMessage: String = "Give this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",
    rationaleMessage: String = "To use this app's functionalities, you need to give us the permission.",
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    wallpaperViewModel: WallpaperViewModel,
    context: Context,
) {
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)

    HandleRequests(multiplePermissionsState = multiplePermissionsState,
        deniedContent = { shouldShowRationale ->
            PermissionDeniedContent(
                deniedMessage = deniedMessage,
                rationaleMessage = rationaleMessage,
                shouldShowRationale = shouldShowRationale,
                onRequestPermission = {
                    Log.d("Permissions", "Requesting permissions")
                    multiplePermissionsState.launchMultiplePermissionRequest()
                },
                onSettingClick = {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                    context.startActivity(intent)
                },
                navController = navController,
                homeViewModel = homeViewModel,
                wallpaperViewModel = wallpaperViewModel
            )
        },
        content = {
            Log.d("Permissions", "Permissions granted, setting up navigation")
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
    val allPermissionsGranted = multiplePermissionsState.allPermissionsGranted
    val shouldShowRationale = multiplePermissionsState.shouldShowRationale

    if (allPermissionsGranted) {
        content()
    } else {
        deniedContent(shouldShowRationale)
    }
}

@Composable
fun Content(
    text: String,
    showButton: Boolean = true,
    onClick: () -> Unit = {},
    onSettingClick: () -> Unit,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    wallpaperViewModel: WallpaperViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        if (showButton) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onClick) {
                    Text(text = "Request")
                }
                Button(onClick = onSettingClick) {
                    Text(text = "Open Settings")
                }

            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContent(
    onSettingClick: () -> Unit,
    deniedMessage: String,
    rationaleMessage: String,
    shouldShowRationale: Boolean,
    onRequestPermission: () -> Unit,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    wallpaperViewModel: WallpaperViewModel,
) {
    if (shouldShowRationale) {
        AlertDialog(onDismissRequest = {}, title = {
            Text(
                text = "Permission Request", style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
        }, text = { Text(rationaleMessage) }, confirmButton = {
            Button(onClick = onRequestPermission) {
                Text("Give Permission")
            }
        })
    } else {
        Content(
            text = deniedMessage,
            onClick = onRequestPermission,
            onSettingClick = onSettingClick,
            navController = navController,
            homeViewModel = homeViewModel,
            wallpaperViewModel = wallpaperViewModel
        )
    }
}
