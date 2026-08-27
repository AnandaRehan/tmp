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

class UserPreferences(private val dataStore: DataStore<Preferences>) {

    companion object {
        // 2. Tentukan Key unik beserta tipe datanya (stringPreferencesKey, intPreferencesKey, dll.)
        private val DATA1_KEY = intPreferencesKey("data1")
    }

    // 3. Membaca Data (Mengembalikan data dalam bentuk Flow secara reactive)
    val userNameFlow: Flow<Int> = dataStore.data
        .map { preferences ->
            // Mengembalikan nilai tersimpan, atau string kosong "" jika null
            preferences[DATA1_KEY] ?: 0
        }

    // 4. Menulis/Menyimpan Data (Wajib menggunakan fungsi suspend / di dalam Coroutine)
    suspend fun saveUserName(name: Int) {
        dataStore.edit { preferences ->
            preferences[DATA1_KEY] = name
        }
    }
}