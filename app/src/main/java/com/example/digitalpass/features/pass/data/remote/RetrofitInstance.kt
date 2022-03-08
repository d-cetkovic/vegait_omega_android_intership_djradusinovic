package com.example.digitalpass.features.pass.data.remote

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//here only for testing to see the response, will be moved later on into dependency injection!
//will be done in repository!
const val TAG = "RetrofitInstance"
object RetrofitInstance {

    private var retroftiInstanceScope: CoroutineScope = CoroutineScope(
        Dispatchers.IO + CoroutineName(TAG))

    val api: DigitalPassApi by lazy{
        Retrofit.Builder()
            .baseUrl("http://10.0.2.16:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DigitalPassApi::class.java)
    }

//    fun createUser(){
//        retroftiInstanceScope.launch {
//            api.createUser()
//        }
//    }
}