package com.alirnp.androidwoocommerceapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.test.TestActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.gilo.woodroid.models.Product
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewHelloWord.setOnClickListener {

            startActivity(Intent(this, TestActivity::class.java))
        }

    }
}