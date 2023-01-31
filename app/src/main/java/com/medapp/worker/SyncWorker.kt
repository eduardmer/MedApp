package com.medapp.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.medapp.data.local.database.MedicineDao
import com.medapp.data.remote.ApiService
import com.medapp.data.remote.model.toMedicineEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val service: ApiService,
    private val medicineDao: MedicineDao) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val response = service.getMedicines()
        val medicineEntities = response.problems
            .flatMap { it.diabetes }
            .flatMap { it.medications }
            .flatMap { it.medicationsClasses }
            .flatMap { listOf(it.className, it.className2).flatten() }
            .flatMap { listOf(it.associatedDrug, it.associatedDrug2).flatten() }
            .map { it.toMedicineEntity() }
        medicineDao.deleteAll()
        medicineDao.insertAllMedicines(medicineEntities)
        return Result.success()
    }

}