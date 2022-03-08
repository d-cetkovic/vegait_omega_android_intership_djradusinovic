package com.example.digitalpass.features.pass.data.remote.dto

import com.example.digitalpass.features.pass.data.local.entities.User
import com.google.gson.JsonObject

data class UserDto(
    val firstName: String,
    val image: String,
    val lastName: String
)

fun UserDto.toUser():User{
    return User(
        firstName = this.firstName,
        lastName = this.lastName,
        image = this.image
    )
}

fun JsonObject.toUserDto(): UserDto {
    return UserDto(
        this.get("firstName").toString(),
        this.get("image").toString(),
        this.get("lastName").toString()
    )
}