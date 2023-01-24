package com.medapp.data

import com.medapp.data.local.database.MedicineDao
import com.medapp.data.local.database.MedicineEntity
import com.medapp.data.local.datastore.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(private val medicineDao: MedicineDao) : MedicineRepository {

    override fun getAllMedicines(): Flow<List<MedicineEntity>> =
        medicineDao.getAllMedicines()

}