package com.medapp.data

import com.medapp.model.UserStatus
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser() : Flow<UserStatus>

    suspend fun login(username: String)

    suspend fun logout()

}