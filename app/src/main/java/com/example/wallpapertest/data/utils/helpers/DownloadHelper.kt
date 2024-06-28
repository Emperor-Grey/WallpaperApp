package com.example.wallpapertest.data.utils.helpers

import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

suspend fun downloadImage(
    context: Context, imageUrl: String
) = withContext(Dispatchers.IO) {
    try {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context).data(imageUrl).allowHardware(false).build()

        val result = loader.execute(request)
        val drawable = result.drawable

        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            val filename = "${System.currentTimeMillis()}.jpg"

            val fileOutputStream: OutputStream?
            val imageUri: Uri?

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val resolver = context.contentResolver
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                imageUri =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fileOutputStream = resolver.openOutputStream(imageUri!!)
            } else {
                val imagesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File(imagesDir, filename)
                fileOutputStream = FileOutputStream(image)
                imageUri = Uri.fromFile(image)
            }

            fileOutputStream?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                it.flush()
            }

            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Image Downloaded", Toast.LENGTH_SHORT).show()
            }
        } else {
            throw Exception("Failed to decode image")
        }

    } catch (e: Exception) {
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Failed to download image", Toast.LENGTH_SHORT).show()
        }
    }
}


suspend fun setAsWallpaper(context: Context, imageUrl: String) = withContext(Dispatchers.IO) {
    try {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context).data(imageUrl).allowHardware(false).build()

        val result = loader.execute(request)
        val drawable = result.drawable

        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            val wallpaperManager = WallpaperManager.getInstance(context)
            wallpaperManager.setBitmap(bitmap)

            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Wallpaper Changed", Toast.LENGTH_SHORT).show()
            }
        } else {
            throw Exception("Failed to decode image")
        }

    } catch (e: Exception) {
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Failed to Set Wallpaper", Toast.LENGTH_SHORT).show()
        }
    }
}
