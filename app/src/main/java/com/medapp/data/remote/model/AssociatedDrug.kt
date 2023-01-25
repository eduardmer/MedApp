package com.medapp.data.remote.model

import com.medapp.data.local.database.MedicineEntity

data class AssociatedDrug(
    val name: String,
    val dose: String,
    val strength: String
)

fun AssociatedDrug.toMedicineEntity() : MedicineEntity {
    return MedicineEntity(name = name, dose = dose, strength = strength)
}
