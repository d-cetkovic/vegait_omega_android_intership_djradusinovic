package com.example.digitalpass.features.pass.data.remote.dto

import com.google.gson.JsonObject

data class UserDto(
    val firstName: String,
    val image: String,
    val lastName: String
)

fun JsonObject.toUserDto(): UserDto {
    return UserDto(
        this.get("firstName").toString(),
        this.get("image").toString(),
        this.get("lastName").toString()
    )
}