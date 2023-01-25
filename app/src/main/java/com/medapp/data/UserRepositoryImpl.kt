package com.medapp.data

import com.medapp.data.local.datastore.PreferencesDataSource
import com.medapp.model.UserStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val preferencesDataSource: PreferencesDataSource) : UserRepository {

    override fun getUser(): Flow<UserStatus> =
        preferencesDataSource.isLogged

    override suspend fun login(username: String) =
        preferencesDataSource.login(username)

    override suspend fun logout() =
        preferencesDataSource.logout()

}