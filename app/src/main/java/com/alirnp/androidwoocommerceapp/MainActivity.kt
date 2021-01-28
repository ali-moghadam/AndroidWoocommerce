package com.alirnp.androidwoocommerceapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alirnp.androidwoocommerceapp.api.WoocommerceApi
import me.gilo.woodroid.models.Product
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "LOG_ME"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WoocommerceApi.instance.ProductRepository().products()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: retrofit2.Call<List<Product>>, response: Response<List<Product>>
                ) {
                    Log.i(TAG, "onResponse: ${response.code()}")

                    if (response.code() == 200) {
                        val items = response.body()
                        for (item in items!!) {
                            Log.i(TAG, "onResponse: ${item.name}")
                        }
                    }
                }

                override fun onFailure(call: retrofit2.Call<List<Product>>, t: Throwable) {
                    Log.i(TAG, "onFailure: ${t.message}")
                }

            })


    }
}