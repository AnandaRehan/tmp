package com.ehan.app1

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import com.ehan.app1.data.UserPreferences

// Ekstensi DataStore untuk Context
val Context.dataStore: DataStore by preferencesDataStore(name = "user_preferences") 

class App1 : Application() {
    
    lateinit var userPreferences: UserPreferences
    private set
    override fun onCreate() {
        super.onCreate()
        // Cukup dibuat satu kali di sini, menggunakan konteks aplikasi (safe dari memory leak)
        userPreferences = UserPreferences(this)
    }
}
