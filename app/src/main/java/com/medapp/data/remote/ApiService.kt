package com.medapp.data.remote

import com.medapp.data.remote.model.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/637d9ef1-b334-4438-ab06-0712044643dc")
    suspend fun getMedicines() : Response

}