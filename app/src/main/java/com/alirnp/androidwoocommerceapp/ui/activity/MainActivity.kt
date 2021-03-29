package com.alirnp.androidwoocommerceapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.databinding.ActivityMainBinding
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.repository.roomDB.AppDatabase
import com.alirnp.androidwoocommerceapp.ui.adapter.ProductAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test.*
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private val productRepository = WoocommerceApi.instance.productRepository()

    private val TAG = "LOG_ME"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()
        getProducts()

        AppDatabase.getInstance(this@MainActivity).productDao()
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (product in it) {
                    Log.i(TAG, "onCreate: ${product.name}")
                }
            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                Log.i(TAG, "onCreate: ${it.message}")
            })

    }

    private fun initRecyclerView() {
        binding.recyclerView.showShimmerAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getProducts() {

        productRepository.products()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: retrofit2.Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    onResponseProducts(response)
                }

                override fun onFailure(call: retrofit2.Call<List<Product>>, t: Throwable) {
                    onFailureProducts(t)
                }
            })

    }

    private fun onFailureProducts(t: Throwable) {
        Log.i(TAG, "onFailure: ${t.message}")
    }

    private fun onResponseProducts(response: Response<List<Product>>) {
        Log.i(TAG, "onResponse: ${response.code()}")

        if (response.isSuccessful) {
            response.body()?.let { productList ->
                declareRecyclerView(productList)

                AppDatabase.getInstance(this@MainActivity).productDao()
                    .insertAll(productList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
                    }, {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        Log.i(TAG, "onCreate: ${it.message}")
                    })
            }
        }
    }

    private fun declareRecyclerView(items: List<Product>) {
        adapter = ProductAdapter(items)
        recyclerView.adapter = adapter
    }
}