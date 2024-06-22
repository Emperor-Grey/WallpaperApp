import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.wallpapertest.ui.screens.wallpaper.WallpaperContent
import com.example.wallpapertest.ui.screens.wallpaper.WallpaperControls
import com.example.wallpapertest.ui.screens.wallpaper.WallpaperViewModel
import com.example.wallpapertest.utils.helpers.downloadImage
import com.example.wallpapertest.utils.helpers.setAsWallpaper
import kotlinx.coroutines.launch

@Composable
fun WallpaperScreen(
    imageId: String, navigateBack: () -> Unit, wallpaperViewModel: WallpaperViewModel
) {
    val wallpaperState by wallpaperViewModel.wallpaperState.collectAsState()

    var showButtons by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(imageId) {
        wallpaperViewModel.loadWallpaper(imageId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        WallpaperContent(wallpaperState = wallpaperState,
            showButtons = showButtons,
            toggleShowButtons = { showButtons = !showButtons })

        WallpaperControls(navigateBack = navigateBack,
            showButtons = showButtons,
            imageId = imageId,
            context = context,
            coroutineScope = coroutineScope,
            onSetWallpaperClick = {
                coroutineScope.launch {
                    setAsWallpaper(context, Uri.parse(imageId))
                }
            },
            onDownloadClick = {
                coroutineScope.launch {
                    downloadImage(imageUrl = imageId, context = context)
                }
            })
    }
}
