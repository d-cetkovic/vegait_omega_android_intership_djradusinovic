package com.example.digitalpass.features.pass.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class CreateAccountDto(
    @SerializedName("user")
    val users: User,
    //custom serializer za kotlin retrofit
    @Expose
    @SerializedName("passes")
    val passes: HashMap<String, PassDto>
)