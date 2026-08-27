package com.ehan.app1

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.ehan.app1.data.UserPreferences

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class App1 : Application() {

    val userPreferences = UserPreferences(dataStore)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App1
            private set
    }
}
