package com.medapp.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.medapp.data.local.database.MedicineDao
import com.medapp.data.local.database.MedicineEntity
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
        val medicinesList = mutableListOf<MedicineEntity>()
        response.problems.forEach { problem ->
            problem.diabetes.forEach { diabete ->
                diabete.medications.forEach { medication ->
                    medication.medicationsClasses.forEach { medicationsClass ->
                        medicationsClass.className.forEach { className ->
                            className.associatedDrug.forEach { associatedDrug ->
                                medicinesList.add(associatedDrug.toMedicineEntity())
                            }
                            className.associatedDrug2.forEach { associatedDrug ->
                                medicinesList.add(associatedDrug.toMedicineEntity())
                            }
                        }
                        medicationsClass.className2.forEach { className ->
                            className.associatedDrug.forEach { associatedDrug ->
                                medicinesList.add(associatedDrug.toMedicineEntity())
                            }
                            className.associatedDrug2.forEach { associatedDrug ->
                                medicinesList.add(associatedDrug.toMedicineEntity())
                            }
                        }
                    }
                }
            }
        }
        medicineDao.deleteAll()
        medicineDao.insertAllMedicines(medicinesList)
        return Result.success()
    }

}