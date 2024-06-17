package com.example.wallpapertest.utils.helpers

import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

suspend fun downloadImage(
    context: Context, imageId: Int, notificationHelper: NotificationHelper
) = withContext(Dispatchers.IO) {
    val bitmap = BitmapFactory.decodeResource(context.resources, imageId)
    val filename = "${imageId}.jpg"

    val fileOutputStream: OutputStream?
    val imageUri: Uri?
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        fileOutputStream = resolver.openOutputStream(imageUri!!)
    } else {
        val imagesDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File(imagesDir, filename)
        fileOutputStream = FileOutputStream(image)
        imageUri = Uri.fromFile(image)
    }

    fileOutputStream?.use {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        withContext(Dispatchers.Main) {
            notificationHelper.showDownloadNotification(filename, imageUri)
            Toast.makeText(context, "Image Downloaded", Toast.LENGTH_SHORT).show()
        }
    }
}


suspend fun setAsWallpaper(context: Context, imageId: Int) = withContext(Dispatchers.IO) {
    val wallpaperManager = WallpaperManager.getInstance(context)

    val loader = ImageLoader(context)
    val request = ImageRequest.Builder(context).data(context.resources.getDrawable(imageId)).build()
    val bitmap = withContext(Dispatchers.Main) {
        loader.execute(request).drawable?.toBitmap()
    }

    try {
        wallpaperManager.setBitmap(bitmap)
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Wallpaper Changed", Toast.LENGTH_SHORT).show()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Failed to Set Wallpaper", Toast.LENGTH_SHORT).show()
        }
    }
}
