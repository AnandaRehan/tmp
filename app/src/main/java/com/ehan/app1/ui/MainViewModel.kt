package com.ehan.app1.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.ehan.app1.App1
import com.ehan.app1.data.UserPreferences

class MainViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    // StateFlow sekarang memegang objek AppPreferences lengkap
    val userName: StateFlow<Int> = userPreferences.userNameFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    fun updateName(newName: Int) {
        viewModelScope.launch {
            userPreferences.saveUserName(newName)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App1)
                MainViewModel(
                    userPreferences = application.userPreferences
                )
            }
        }
    }
}