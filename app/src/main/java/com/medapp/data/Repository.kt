package com.medapp.data

import com.medapp.common.Status
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun isLogged() : Flow<Status>

    suspend fun login(username: String)

}