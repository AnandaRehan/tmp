package com.ehan.app1

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.ehan.data.UserPreferences

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class MyApplication : Application() {

    lateinit var userPreferences: UserPreferences
        private set

    override fun onCreate() {
        super.onCreate()
        userPreferences = UserPreferences(this)
    }
}
