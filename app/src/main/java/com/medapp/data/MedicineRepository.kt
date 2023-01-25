package com.medapp.data

import com.medapp.data.local.database.MedicineEntity
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {

    fun getAllMedicines() : Flow<List<MedicineEntity>>

    suspend fun insertAllMedicines(medicines: List<MedicineEntity>)

    suspend fun deleteAll()

}