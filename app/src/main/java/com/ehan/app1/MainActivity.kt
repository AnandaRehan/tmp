package com.ehan.app1

import android.os.Bundle
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ehan.app1.ui.theme.App1Theme
import com.ehan.app1.ui.theme.ThemeMode

class MainActivity : ComponentActivity() {
    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App1Theme {
                greeting(dataStore = dataStore)
            }
        }
    }
}

@Composable
fun greeting(dataStore: DataStore<Preferences>) {
    val DATA1_KEY = stringPreferencesKey("data1")
    val _angka_1: String = dataStore.data.map { preferences ->
            preferences[DATA1_KEY] ?: "0"
    }
    val angka_1: Int = _angka_1.toIntOrNull() ?: 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = "Angka Saat Ini " + angka_1.toString())
        Button(
            onClick = {
                angka_1++
                dataStore.edit { preferences ->
                    preferences[DATA1_KEY] = angka_1.toString()
                }
            }
        ) {
            Text(
                text = "Tambah 1"
            )
        }
    }
}