package com.medapp.di

import com.medapp.data.MedicineRepository
import com.medapp.data.MedicineRepositoryImpl
import com.medapp.data.UserRepository
import com.medapp.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMedicineRepository(medicineRepositoryImpl: MedicineRepositoryImpl): MedicineRepository

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}