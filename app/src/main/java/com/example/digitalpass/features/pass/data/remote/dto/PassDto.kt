package com.example.digitalpass.features.pass.data.remote.dto

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.digitalpass.features.pass.data.local.entities.Pass
import com.google.gson.JsonObject

data class PassDto(
    val description: String,
    val icon: String,
    val name: String
)

fun PassDto.toPass(passId: String, userId: Long):Pass{
    return Pass(
        pass_id = passId,
        user_id = userId,
        description = this.description,
        icon = this.icon,
        name = this.name
    )
}
fun JsonObject.toPassDto(): PassDto {
    return PassDto(
        this.get("description").toString(),
        this.get("icon").toString(),
        this.get("name").toString()
    )
}
