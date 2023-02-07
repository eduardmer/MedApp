package com.medapp.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PreferencesDataStoreTest {

    private val datastore: DataStore<Preferences> = PreferenceDataStoreFactory.create(produceFile = {
        ApplicationProvider.getApplicationContext<Context>().preferencesDataStoreFile("app_preferences_test")
    })
    private val preferencesDataSource = PreferencesDataSource(datastore)

    @Test
    fun loginUserSuccessfully() = runTest {
        preferencesDataSource.logout()
        assertFalse(preferencesDataSource.isLogged.first().isLogged)
        preferencesDataSource.login("username")
        assertTrue(preferencesDataSource.isLogged.first().isLogged)
        preferencesDataSource.logout()
        assertFalse(preferencesDataSource.isLogged.first().isLogged)
    }

}