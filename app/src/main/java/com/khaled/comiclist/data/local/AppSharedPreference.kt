package com.khaled.comiclist.data.local

import android.content.Context
import android.content.SharedPreferences

class AppSharedPreference(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getString(key: String, defaultValue: String) = sharedPreferences.getString(key, defaultValue)
    fun setString(key: String, value: String) = sharedPreferences.edit().putString(key, value).apply()

    companion object {
        private const val SHARED_PREFERENCE_NAME = "sharedPreferencesName"
    }
}