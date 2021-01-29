package com.alirnp.androidwoocommerceapp.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.ui.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_test.*
import me.gilo.woodroid.models.Product
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ProductAdapter

    private val TAG = "LOG_ME"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        recyclerView.showShimmerAdapter()

        WoocommerceApi.instance.ProductRepository().products()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: retrofit2.Call<List<Product>>, response: Response<List<Product>>
                ) {
                    Log.i(TAG, "onResponse: ${response.code()}")

                    if (response.code() == 200) {
                        val items = response.body()

                        if (items != null){
                            declareRecyclerView(items)
                        }

                    }
                }

                override fun onFailure(call: retrofit2.Call<List<Product>>, t: Throwable) {
                    Log.i(TAG, "onFailure: ${t.message}")
                }

                private fun declareRecyclerView(items: List<Product>) {
                        adapter = ProductAdapter(items)
                        recyclerView.adapter = adapter


                }

            })
    }
}