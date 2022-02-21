package com.example.digitalpass.features.pass.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface DigitalPassApi {

    @GET("/createAccount")
    suspend fun createUser(): Response<Any>
}