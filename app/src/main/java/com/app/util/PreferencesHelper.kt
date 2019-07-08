package com.app.util

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

// Link -> https://stackoverflow.com/questions/48920133/singleton-sharedpreferences-with-dagger-2

@Singleton
class PreferencesHelper @Inject constructor(private val preferences: SharedPreferences)
{
    fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return preferences.getString(key, "")
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun putInt(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key, -1)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }
}
