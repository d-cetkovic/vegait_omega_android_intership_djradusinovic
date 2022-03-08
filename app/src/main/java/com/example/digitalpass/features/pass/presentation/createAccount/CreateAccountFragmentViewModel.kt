package com.example.digitalpass.features.pass.presentation.createAccount

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.digitalpass.features.pass.data.local.LocalStorageManager
import com.example.digitalpass.features.pass.data.remote.RetrofitInstance
import com.example.digitalpass.features.pass.data.remote.dto.CreateAccountDto
import com.example.digitalpass.features.pass.data.remote.dto.toCreateAccountDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

//class CreateAccountViewModel(context: Context): ViewModel() {

class CreateAccountFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val localStorageManager = LocalStorageManager(application)

//        localStorageManager.insertUser()
//        localStorageManager.insertAllPasses()
//    localStorageManager.storeUserIdIntoSharedPreferences(1)
    fun createAccount(){

        viewModelScope.launch(Dispatchers.IO) {

            val createAccountDto = async {
                RetrofitInstance.api.createUser().body()?.toCreateAccountDto()
            }
            createAccountDto.await()?.let {
                localStorageManager.insertUserWithPasses(it)
            }
        }
    }
}