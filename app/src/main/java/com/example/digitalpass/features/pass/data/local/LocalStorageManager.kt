package com.example.digitalpass.features.pass.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.digitalpass.features.pass.data.local.entities.Pass
import com.example.digitalpass.features.pass.data.local.relations.UserWithPasses
import com.example.digitalpass.features.pass.data.local.repository.PassRepository
import com.example.digitalpass.features.pass.data.local.repository.UserRepository
import com.example.digitalpass.features.pass.data.remote.dto.CreateAccountDto
import com.example.digitalpass.features.pass.data.remote.dto.toUser
import com.example.digitalpass.features.pass.data.remote.dto.toUserPasses
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

const val TAG: String = "LocalStorageManager"
const val SHARED_PREFS_LSM = "LocalStorageManager -> Shared preferences"

class LocalStorageManager(context: Context) {

    private val userRepository = UserRepository( DatabaseApp.getDatabase(context).userDao() )
    private val passRepository = PassRepository( DatabaseApp.getDatabase(context).passDao() )
    private var localStorageManageScope: CoroutineScope? = null
    init {
        localStorageManageScope = CoroutineScope(Dispatchers.IO + CoroutineName(TAG))
    }

    fun insertAllPasses(passes: List<Pass>){
        localStorageManageScope?.launch { passRepository.insertPasses(passes) }
    }

    fun getUserWithPasses(userId: Int): Flow<UserWithPasses> {
        return userRepository.getUserWithPasses()
    }

//    fun storeUserIdIntoSharedPreferences(user_id : Int){
//        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_LSM, MODE_PRIVATE)
//        sharedPrefs.edit().apply {
//            putString("user_id", user_id.toString())//add as string since idk which vlaues autogenerated id will be adn since i don't know that i don't know if getID call from shared pref failed or succeeded
//            apply()//does it async
//        }
//    }
//    //-1 indicates no user!
//    fun getUserIdFromSharedPreferences(): String?{
//        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_LSM, MODE_PRIVATE)
//        return sharedPrefs.getString("user_id", null)
//    }

    fun insertUserWithPasses(createAccountDto: CreateAccountDto){
        localStorageManageScope?.launch {
            val userId = async {
                userRepository.insertUser(createAccountDto.user.toUser())
            }
            val passes = createAccountDto.toUserPasses(userId = userId.await() )
            passRepository.insertPasses(passes)
        }

    }
}