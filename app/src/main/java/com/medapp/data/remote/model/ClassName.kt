package com.medapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ClassName(
    val associatedDrug: List<AssociatedDrug>,
    @SerializedName("associatedDrug#2")
    val associatedDrug2: List<AssociatedDrug>
)
