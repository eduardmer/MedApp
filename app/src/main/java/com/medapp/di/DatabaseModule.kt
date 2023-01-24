package com.medapp.di

import android.content.Context
import androidx.room.Room
import com.medapp.data.local.database.AppDatabase
import com.medapp.data.local.database.MedicineDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideMedicineDao(database: AppDatabase) : MedicineDao {
        return database.medicineDao()
    }

}