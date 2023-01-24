package com.medapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medapp.data.UserRepository
import com.medapp.domain.GetMedicinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.medapp.model.Result

@HiltViewModel
class SharedViewModel @Inject constructor(private val userRepository: UserRepository, getMedicinesUseCase: GetMedicinesUseCase) : ViewModel() {

    val profileUiState = getMedicinesUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        Result.Loading
    )

    val isLogged = userRepository.getUser()

    fun login(username: String) {
        viewModelScope.launch {
            userRepository.login(username)
        }
    }

}