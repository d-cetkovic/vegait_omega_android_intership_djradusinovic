package com.example.digitalpass.features.pass.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.digitalpass.features.pass.data.local.entities.Pass

@Dao
interface PassDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPass(pass: Pass)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPasses(passes: List<Pass>) //idk if it works

    suspend fun insertPassList(passes: List<Pass>){
        for( pass in passes ){
            insertPass(pass)
        }
    }
}