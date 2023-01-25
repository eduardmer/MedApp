package com.medapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Problems(
    @SerializedName("Diabetes")
    val diabetes: List<Diabetes>
    )
