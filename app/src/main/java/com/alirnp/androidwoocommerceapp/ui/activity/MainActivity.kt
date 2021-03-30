package com.alirnp.androidwoocommerceapp.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.databinding.ActivityMainBinding
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.AppExecutors
import com.alirnp.androidwoocommerceapp.repository.Resource
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.repository.roomDB.AppDatabase
import com.alirnp.androidwoocommerceapp.ui.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_test.*

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

    }

    private fun initRecyclerView() {
        binding.recyclerView.showShimmerAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getProducts() {

        // Create the observer which updates the UI.
        val nameObserver = Observer<Resource<List<Product>>> { newName ->
            // Update the UI, in this case, a TextView.
            when(newName){
                is Resource.Loading -> Log.i(TAG, "getProducts: Loading")
                is Resource.Success -> onResponseProducts(newName.data)
                is Resource.Error -> onFailureProducts(newName.message)
            }

        }

            productRepository.getProducts(
                this ,
                AppDatabase.getInstance(this).productDao(),
                AppExecutors())
            .observe(this ,nameObserver )


    }



    private fun onFailureProducts(t: String?) {
        Log.i(TAG, "onFailure: $t?")
    }

    private fun onResponseProducts(response: List<Product>?) {
        Log.i(TAG, "onResponseProducts: ${response?.size}")
            response?.let { productList ->
                declareRecyclerView(productList)
            }

    }

    private fun declareRecyclerView(items: List<Product>) {
        adapter = ProductAdapter(items)
        recyclerView.adapter = adapter
    }
}