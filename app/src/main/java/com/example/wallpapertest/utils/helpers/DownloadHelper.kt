package com.example.wallpapertest.utils.helpers

import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.net.toUri
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.URL

suspend fun downloadImage1(
    context: Context, imageUrl: String,
) = withContext(Dispatchers.IO) {
    try {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context).data(imageUrl).allowHardware(false).build()

        val result = loader.execute(request)
        val bitmap = (result.drawable as BitmapDrawable).bitmap
        val filename = "${System.currentTimeMillis()}.jpg"

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
            it.flush()
        }

        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Image Downloaded", Toast.LENGTH_SHORT).show()
        }

    } catch (e: Exception) {
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Failed to download image", Toast.LENGTH_SHORT).show()
        }
    }
}

suspend fun setAsWallpaper1(context: Context, imageUrl: String) = withContext(Dispatchers.IO) {
    val wallpaperManager = WallpaperManager.getInstance(context)

    val loader = ImageLoader(context)
    val request = ImageRequest.Builder(context).data(imageUrl).allowHardware(false).build()

    val result = loader.execute(request)
    val bitmap = (result.drawable as BitmapDrawable).bitmap

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

suspend fun downloadImage(imageUrl: String, context: Context): Uri {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection()
            connection.connect()

            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()

            // Create images directory if it doesn't exist
            val imagesDir = File(context.getExternalFilesDir(null), "images")
            if (!imagesDir.exists()) {
                imagesDir.mkdirs()
            }

            // Save image to images directory
            val file = File(imagesDir, "downloaded_image.jpg")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            file.toUri()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}

suspend fun setAsWallpaper(context: Context, imageUri: Uri) {
    withContext(Dispatchers.IO) {
        try {
            val wallpaperManager = WallpaperManager.getInstance(context)
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            wallpaperManager.setBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
