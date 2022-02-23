package com.example.digitalpass.features.pass.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digitalpass.features.pass.data.local.entities.PASS_TABLE_NAME
import com.example.digitalpass.features.pass.data.local.entities.USER_TABLE_NAME
import com.example.digitalpass.features.pass.data.local.entities.User
import com.example.digitalpass.features.pass.data.local.relations.UserWithPasses
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    @Query("SELECT * FROM $USER_TABLE_NAME")//will always return 1 user here of course but if it was to scale it would be easier to have multiple users like this
    suspend fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM $USER_TABLE_NAME WHERE user_id = :userId")//unique id -> one user will be returned only even though I used '*' here
    fun getUserById(userId: Int): Flow<User>
    //query for 1-n relation built between User and its passes
    @Query("""SELECT * FROM $PASS_TABLE_NAME WHERE user_id = :userId""")
    fun getUserWithPasses(userId: Int) : Flow<UserWithPasses>
}