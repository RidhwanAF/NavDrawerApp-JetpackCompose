package com.raf.navdrawerapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")

class DataStore {

    private val darkThemeKey = booleanPreferencesKey("dark_theme_key")
    private val dynamicColorThemeKey = booleanPreferencesKey("material_u_theme_key")

    fun getDarkTheme(context: Context): Flow<Boolean> = context.dataStore.data.map { pref ->
        pref[darkThemeKey] ?: false
    }

    suspend fun saveDarkTheme(context: Context, value: Boolean) {
        context.dataStore.edit { setting ->
            setting[darkThemeKey] = value
        }
    }

    fun getDynamicColorTheme(context: Context): Flow<Boolean> = context.dataStore.data.map { pref ->
        pref[dynamicColorThemeKey] ?: false
    }

    suspend fun saveDynamicColorTheme(context: Context, value: Boolean) {
        context.dataStore.edit { setting ->
            setting[dynamicColorThemeKey] = value
        }
    }
}