package com.alirnp.androidwoocommerceapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.test.TestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewHelloWord.setOnClickListener {

            startActivity(Intent(this, TestActivity::class.java))
        }

    }
}