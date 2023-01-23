package com.medapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val isLogged = repository.isLogged()

    fun login(username: String) {
        viewModelScope.launch {
            repository.login(username)
        }
    }

}