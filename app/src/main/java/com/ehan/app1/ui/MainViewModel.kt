package com.ehan.app1.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.ehan.app1.App1

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Ambil singleton userPreferences dari MyApplication
    private val userPreferences = (application as App1).userPreferences

    // Mengubah Flow biasa menjadi StateFlow agar optimal di lingkungan Compose
    val userName: StateFlow<Int> = userPreferences.userNameFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    fun updateName(newName: Int) {
        viewModelScope.launch {
            userPreferences.saveUserName(newName)
        }
    }
}