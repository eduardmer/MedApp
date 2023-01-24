package com.medapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MedicineEntity::class], version = AppDatabase.DB_VERSION)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "med_database"
        const val DB_VERSION = 1
    }

    abstract fun medicineDao(): MedicineDao

}