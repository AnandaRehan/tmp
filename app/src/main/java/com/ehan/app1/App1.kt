/**package com.ehan.app1

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
*/
package com.ehan.app1 // Ganti dengan package asli Anda (com.ehan.app1)

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

// 1. DEKLARASI TOP-LEVEL: Pastikan letak baris ini ada di LUAR class App1 (paling atas file)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class App1 : Application() {

    // 2. Gunakan 'lateinit var' agar variabel tidak langsung diinisialisasi saat class dibuat
    lateinit var userPreferences: UserPreferences
        private set

    override fun onCreate() {
        super.onCreate()
        
        // 3. DI SINI TEMPAT YANG BENAR: Inisialisasi WAJIB dilakukan di dalam onCreate()
        // Di titik ini, Context aplikasi ('this') sudah dijamin siap dan tidak null oleh OS Android
        userPreferences = UserPreferences(this)
    }
}