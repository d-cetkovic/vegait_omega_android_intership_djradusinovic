package com.example.digitalpass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.digitalpass.features.pass.data.local.DatabaseApp
import com.example.digitalpass.features.pass.data.remote.RetrofitInstance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

}