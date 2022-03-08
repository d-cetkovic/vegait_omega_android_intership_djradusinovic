package com.example.digitalpass.features.pass.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val USER_TABLE_NAME = "USER"
@Entity(tableName = USER_TABLE_NAME)
data class User (//only one user -> no need for primary key of it
    @PrimaryKey(autoGenerate = true)
    val user_id: Int = 0,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "last_name")
    val lastName: String
)
//add extensions here to convert from Dto to local room database viable format