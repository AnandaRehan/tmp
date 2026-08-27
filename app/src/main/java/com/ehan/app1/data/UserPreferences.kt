package com.ehan.app1.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(private val context: Context) {

    companion object {
        // 2. Tentukan Key unik beserta tipe datanya (stringPreferencesKey, intPreferencesKey, dll.)
        val DATA1_KEY = intPreferencesKey("data1")
    }

    // 3. Membaca Data (Mengembalikan data dalam bentuk Flow secara reactive)
    val userNameFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            // Mengembalikan nilai tersimpan, atau string kosong "" jika null
            preferences[USER_NAME_KEY] ?: 0
        }

    // 4. Menulis/Menyimpan Data (Wajib menggunakan fungsi suspend / di dalam Coroutine)
    suspend fun saveUserName(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }
}