package com.example.digitalpass.features.pass.presentation.passlist

import android.util.Log
import androidx.lifecycle.*
import com.example.digitalpass.features.pass.data.remote.RetrofitInstance
import com.example.digitalpass.features.pass.data.remote.dto.CreateAccountDto
import com.example.digitalpass.features.pass.data.remote.dto.PassDto
import com.example.digitalpass.features.pass.data.remote.dto.toCreateAccountDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG: String = "PassListViewModel"
class PassListViewModel: ViewModel() {

    private var createAccountDto : CreateAccountDto? = null
    val passList:MutableLiveData<List<PassDto>>? = null

    fun createAccountApiCallAndUpdateLiveData(){
        //        //makes an api call and than updates the live data accordingly
        createAccountApiCall()
        updateLiveData()//should be done after api call is made
    }
    //makes api call to /createAccount
    //updates live data needed for the recycler view
    private fun createAccountApiCall(){
        viewModelScope.launch(Dispatchers.IO) {

            val response = try {
                RetrofitInstance.api.createUser()
            }
            catch (e: IOException){
                Log.d(TAG, "Retrofit -> No internet ")
                return@launch
            }
            catch (e: HttpException){
                Log.d(TAG, "Retrofit -> Httpexception")
                return@launch
            }
            if( response.isSuccessful && response != null){
                val json = response.body()
                json?.toCreateAccountDto()?.let {
                    createAccountDto = it
                }
            }
            else{
                Log.d(TAG, "/createUser api call -> RESPONSE NOT SUCCESSFUL")
            }

        }
    }

    private fun updateLiveData(){
        createAccountDto?.let { currentData ->
            val passesList = currentData.passesDto.values.toList() // hasMap.values.toList()
            passList?.postValue(passesList)
        }
    }
}