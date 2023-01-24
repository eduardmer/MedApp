package com.medapp.model

import com.medapp.data.local.database.MedicineEntity

sealed interface Result {
    data class Success(val username: String, val medicines: List<MedicineEntity>) : Result
    data class Error(val msg: String) : Result
    object Loading : Result
    object NotLogged : Result
}