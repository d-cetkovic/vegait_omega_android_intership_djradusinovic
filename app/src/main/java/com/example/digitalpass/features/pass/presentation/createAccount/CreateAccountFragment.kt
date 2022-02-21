package com.example.digitalpass.features.pass.presentation.createAccount

import android.R.attr.path
import android.os.Bundle
import android.os.Environment
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


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
            if( response.isSuccessful && response.body() != null){
                Log.e("WORKS", response.body().toString())
//                File("somefile.txt").printWriter().use {
//                    out -> out.print(response.body().toString())
//                }
                val path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MOVIES
                )
                val file: File = File(path, "/textfajl")
                file.writeText("nseteo", Charset.defaultCharset())
//                File("jsonoutput").writeText("some text")
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