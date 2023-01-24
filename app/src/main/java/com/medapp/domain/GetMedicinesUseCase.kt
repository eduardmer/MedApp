package com.medapp.domain

import com.medapp.data.MedicineRepository
import com.medapp.data.UserRepository
import com.medapp.model.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetMedicinesUseCase @Inject constructor(private val userRepository: UserRepository, private val medicineRepository: MedicineRepository) {

    operator fun invoke(): Flow<Result> =
        userRepository.getUser().flatMapLatest { user ->
            if (user.isLogged) {
                medicineRepository.getAllMedicines().map { medicines ->
                    Result.Success(user.username!!, medicines)
                }.onStart {
                    Result.Loading
                }.catch {
                    Result.Error(it.message ?: "Error")
                }
            } else
                flowOf(Result.NotLogged)
        }

}