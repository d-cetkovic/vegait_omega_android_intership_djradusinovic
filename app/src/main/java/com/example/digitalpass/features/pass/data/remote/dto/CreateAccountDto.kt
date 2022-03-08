package com.example.digitalpass.features.pass.data.remote.dto

import com.example.digitalpass.features.pass.data.local.entities.Pass
import com.example.digitalpass.features.pass.data.local.entities.User
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CreateAccountDto(
    @SerializedName("user")
    val user: UserDto,
    //custom serializer za kotlin retrofit
    @SerializedName("passes")
    val passesDto: HashMap<String, PassDto>
)

fun CreateAccountDto.toUserPasses(userId: Long):List<Pass>{
    val passesList :MutableList<Pass> = mutableListOf()

    for( pass in passesDto ){
        passesList.add(
            pass.value.toPass(pass.key, userId)
        )
    }

    return passesList
}

fun JsonObject.toCreateAccountDto(): CreateAccountDto{
    val passesDto: HashMap<String, PassDto> = HashMap()
    val user = this.getAsJsonObject("user").toUserDto()

    //traverse all the keys in order to get all of the passes alongside with their IDs
    this.keySet()?.let { keys ->

        for( i in 1 until keys.size){
            val currentPassID = keys.elementAt(i)
            val currentPassJsonObject = this.getAsJsonObject(currentPassID)
            val passDto = currentPassJsonObject.toPassDto()
            passesDto[currentPassID] = passDto
        }
    }

    return CreateAccountDto( user, passesDto )
}
