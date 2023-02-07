package com.medapp.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MedicineDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var medicineDao: MedicineDao
    private val item = MedicineEntity(0, "Aspirine", "dose", "strength")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        medicineDao = db.medicineDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertReadDeleteSuccessfully() = runTest {
        medicineDao.insertAllMedicines(listOf(item))
        assertEquals(1, medicineDao.getAllMedicines().first().size)
        medicineDao.insertAllMedicines(listOf(item))
        assertEquals(2, medicineDao.getAllMedicines().first().size)
        medicineDao.deleteAll()
        assertEquals(0, medicineDao.getAllMedicines().first().size)
    }

}