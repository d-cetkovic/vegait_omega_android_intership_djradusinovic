package com.example.digitalpass.features.pass.data.local.repository

import androidx.annotation.WorkerThread
import com.example.digitalpass.features.pass.data.local.dao.PassDao
import com.example.digitalpass.features.pass.data.local.entities.Pass
import com.example.digitalpass.features.pass.data.local.relations.UserWithPasses

class PassRepository(private val passDao: PassDao) {

    @WorkerThread
    suspend fun insertPass(pass: Pass){
        passDao.insertPass(pass)
    }
    @WorkerThread
    suspend fun insertPasses(passes: List<Pass>){
        passDao.insertAllPasses(passes)
    }
}