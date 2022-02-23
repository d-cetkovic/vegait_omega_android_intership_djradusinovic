package com.example.digitalpass.features.pass.data.remote.dto

import com.google.gson.JsonObject

data class PassDto(
    val description: String,
    val icon: String,
    val name: String
)

fun JsonObject.toPassDto(): PassDto {
    return PassDto(
        this.get("description").toString(),
        this.get("icon").toString(),
        this.get("name").toString()
    )
}
