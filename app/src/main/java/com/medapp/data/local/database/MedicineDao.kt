package com.medapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Query("SELECT * FROM Medicine")
    fun getAllMedicines() : Flow<List<MedicineEntity>>

    @Insert
    suspend fun insertAllMedicines(medicines: List<MedicineEntity>)

    @Query("DELETE FROM Medicine")
    suspend fun deleteAll()

}