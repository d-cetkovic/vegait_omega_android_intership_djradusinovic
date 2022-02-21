package com.example.digitalpass.features.pass.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//here only for testing to see the response, will be moved later on into dependency injection!
//will be done in repository!
object RetrofitInstance {

    val api: DigitalPassApi by lazy{
        Retrofit.Builder()
            .baseUrl("http://10.0.2.16:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DigitalPassApi::class.java)
    }
}