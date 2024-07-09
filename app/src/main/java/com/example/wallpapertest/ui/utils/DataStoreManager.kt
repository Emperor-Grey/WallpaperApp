package com.example.wallpapertest.ui.utils

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


private val Context.datastore by preferencesDataStore("user_prefs")

object Preferences {
    val SHOW_SPLASH = booleanPreferencesKey("show_splash")
}

class DataStoreManager(private val context: Context) {
    val showSplash = context.datastore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { prefs ->
        prefs[Preferences.SHOW_SPLASH] ?: true
    }

    suspend fun setShowSplash(show: Boolean) {
        context.datastore.edit { prefs ->
            prefs[Preferences.SHOW_SPLASH] = show
        }
    }
}
