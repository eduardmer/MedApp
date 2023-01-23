package com.medapp.data

import com.medapp.common.Status
import com.medapp.data.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: LocalDataSource) : Repository {

    override fun isLogged(): Flow<Status> =
        dataSource.isLogged

    override suspend fun login(username: String) =
        dataSource.login(username)

}