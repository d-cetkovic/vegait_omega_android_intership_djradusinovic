package com.example.digitalpass.features.pass.data.local.repository

import androidx.annotation.WorkerThread
import com.example.digitalpass.features.pass.data.local.dao.UserDao
import com.example.digitalpass.features.pass.data.local.entities.User
import com.example.digitalpass.features.pass.data.local.relations.UserWithPasses
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    @WorkerThread
    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    fun getUserById(userId: Int): Flow<User>{
        return userDao.getUserById(userId)
    }

    fun getUserWithPasses(userId: Int): Flow<UserWithPasses> {
        return userDao.getUserWithPasses(userId)
    }
}