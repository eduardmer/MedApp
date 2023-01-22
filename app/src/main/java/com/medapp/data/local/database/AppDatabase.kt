package com.medapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = AppDatabase.DB_VERSION)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "med_database"
        const val DB_VERSION = 1
    }

}