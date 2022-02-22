package com.example.digitalpass.features.pass.presentation.createAccount

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.digitalpass.R
import com.example.digitalpass.databinding.FragmentCreateAccountBinding
import com.example.digitalpass.features.pass.data.remote.RetrofitInstance
import com.example.digitalpass.features.pass.data.remote.dto.User
import com.example.digitalpass.features.pass.data.remote.dto.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException


class CreateAccountFragment:Fragment(R.layout.fragment_create_account) {

    private var _binding: FragmentCreateAccountBinding? = null

//     This property is only valid between onCreateView and
//     onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        binding.createAccountButton.setOnClickListener { createAccountClicked() }
        lifecycleScope.launch(Dispatchers.IO) {
            //odje steka
            val response = try {
                RetrofitInstance.api.createUser()
            }
            catch (e: IOException){
                Log.d("Retrofit", " No internet ")
                return@launch
            }
            catch (e: HttpException){
                Log.d("Retrofit", "Httpexception")
                return@launch
            }
            if( response.isSuccessful && response != null){
                val str = response.body().toString()
                Log.d("WORKS", str.substring(str.length-100, str.length))
                val jsonObject = JSONObject(str)
                val user : JSONObject =  jsonObject.get("user") as JSONObject
                val obj = user.getUserFromJSON(user)
                Log.d("ALSJDLAS", obj.firstName + " " + obj.lastName)
                Log.d("USER", user.toString())
                val name = (user as JSONObject).get("firstName")


//                val user:UserDto = jsonObject.get("user") as UserDto
//                Log.d("USER", user.lastName )
//                response.body().
//                val body = response.body()
//                Log.e("WORKS", body?.users.toString() )
//                Log.e("WORKS", body?.passes.toString() )
//                if( response.body()!!.passes == null ){
//                    Log.d("WORKS", "ITS NULL")
//                }
//                if( response.body()!!.users == null ){
//                    Log.d("WORKS", "ITS USER NULL")
//                }
//                response.body()?.let { responses ->
//                    for( inst in responses ){
//                        Log.d("WORKS", inst.toString())
//                    }
//                }
//                response.body()?.apply {
//                    if( passes == null )
//                        Log.d("WORD", "PASSES ARE NULL")
//                }
            }
            else{
                Log.d("DOESN'T WORK", "DOESN'T WORK")
            }

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createAccountClicked(){
       findNavController().navigate(R.id.action_createAccountFragment_to_passListFragment)
    }
}

fun JSONObject.getUserFromJSON(userJson: JSONObject): UserDto{
    return UserDto(userJson.getString("firstName"), userJson.getString("image"), userJson.getString("lastName"))
}