package com.medapp

import com.medapp.data.remote.model.AssociatedDrug
import com.medapp.data.remote.model.toMedicineEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class AssociateDrugTest {

    @Test
    fun associateDrugToMedicineEntity() {
        val associateDrug = AssociatedDrug("name", "dose", "strength")
        val medicineEntity = associateDrug.toMedicineEntity()
        assertEquals(associateDrug.name, medicineEntity.name)
        assertEquals(associateDrug.dose, medicineEntity.dose)
        assertEquals(associateDrug.strength, medicineEntity.strength)
    }

}