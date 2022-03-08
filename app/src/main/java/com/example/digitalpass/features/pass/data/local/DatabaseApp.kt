package com.example.digitalpass.features.pass.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.digitalpass.features.pass.data.local.dao.PassDao
import com.example.digitalpass.features.pass.data.local.dao.UserDao
import com.example.digitalpass.features.pass.data.local.entities.Pass
import com.example.digitalpass.features.pass.data.local.entities.User

const val DATABASE_NAME = "DATABASE_DIGITAL_PASS"
@Database(
    entities = [User::class, Pass::class],
    exportSchema = false,
    version = 1
)
abstract class DatabaseApp: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun passDao(): PassDao

    companion object{
        @Volatile
        private var instance : DatabaseApp? = null

        fun getDatabase(context: Context): DatabaseApp{
            return instance ?: synchronized(this){
                return Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseApp::class.java,
                    DATABASE_NAME
                ).build().also {
                    instance = it
                }
            }
        }
    }
}