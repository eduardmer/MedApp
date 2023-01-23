package com.medapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.medapp.common.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private object Keys {
        val usernameKey = stringPreferencesKey("username")
    }

    val isLogged: Flow<Status> = dataStore.data.map {
        if (it[Keys.usernameKey] != null)
            Status.LogIn(it[Keys.usernameKey]!!)
        else
            Status.LogOut
    }

    suspend fun login(username: String) {
        dataStore.edit { preferences ->
            preferences[Keys.usernameKey] = username
        }
    }

}