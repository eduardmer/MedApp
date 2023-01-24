package com.medapp.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.medapp.model.UserStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private object Keys {
        val usernameKey = stringPreferencesKey("username")
    }

    val isLogged: Flow<UserStatus> = dataStore.data.map {
        UserStatus(it[Keys.usernameKey] != null, it[Keys.usernameKey])
    }

    suspend fun login(username: String) {
        dataStore.edit { preferences ->
            preferences[Keys.usernameKey] = username
        }
    }

}