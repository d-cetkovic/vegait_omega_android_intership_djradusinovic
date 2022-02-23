package com.example.digitalpass.features.pass.data.remote

import com.google.gson.JsonObject
import retrofit2.http.GET

interface DigitalPassApi {

    @GET("/createAccount")
    suspend fun createUser(): retrofit2.Response<JsonObject>
}

