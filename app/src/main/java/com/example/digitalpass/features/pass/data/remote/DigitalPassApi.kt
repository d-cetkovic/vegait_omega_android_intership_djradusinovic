package com.example.digitalpass.features.pass.data.remote

import com.example.digitalpass.features.pass.data.remote.dto.CreateAccountDto
import com.example.digitalpass.features.pass.data.remote.dto.UserDto
import com.google.gson.JsonObject
import okhttp3.Response//not using Response form retrofit2 here
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer
import retrofit2.http.GET
import java.util.*
import retrofit2.Response as Response1

interface DigitalPassApi {

    @GET("/createAccount")
    suspend fun createUser(): retrofit2.Response<JsonObject>
}