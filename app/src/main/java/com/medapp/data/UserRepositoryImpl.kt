package com.medapp.data

import com.medapp.data.local.datastore.PreferencesDataSource
import com.medapp.model.UserStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val preferencesDataSource: PreferencesDataSource) : UserRepository {

    override fun getUser(): Flow<UserStatus> =
        preferencesDataSource.isLogged

    override suspend fun login(username: String) =
        preferencesDataSource.login(username)

}