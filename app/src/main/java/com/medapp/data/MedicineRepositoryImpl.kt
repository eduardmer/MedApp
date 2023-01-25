package com.medapp.data

import androidx.work.*
import com.medapp.common.WORK_NAME
import com.medapp.data.local.database.MedicineDao
import com.medapp.data.local.database.MedicineEntity
import com.medapp.worker.SyncWorker
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicineRepositoryImpl @Inject constructor(
    private val medicineDao: MedicineDao,
    workManager: WorkManager) : MedicineRepository {

    init {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val work = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            work)
    }

    override fun getAllMedicines(): Flow<List<MedicineEntity>> =
        medicineDao.getAllMedicines()

    override suspend fun insertAllMedicines(medicines: List<MedicineEntity>) =
        medicineDao.insertAllMedicines(medicines)

    override suspend fun deleteAll() =
        medicineDao.deleteAll()
}