package com.example.digitalpass.features.pass.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val PASS_TABLE_NAME = "PASS"
@Entity(tableName = PASS_TABLE_NAME)
data class Pass(
    @PrimaryKey(autoGenerate = false)
    val pass_id: String,
    @ColumnInfo(name = "user_id")
    val user_id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "icon")
    val icon: String,
    @ColumnInfo(name = "name")
    val name: String
)
//add extensions here to convert from Dto to local room database viable format
