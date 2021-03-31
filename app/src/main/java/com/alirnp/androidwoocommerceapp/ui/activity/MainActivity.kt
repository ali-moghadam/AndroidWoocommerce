package com.alirnp.androidwoocommerceapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.core.helper.ProductHelper
import com.alirnp.androidwoocommerceapp.databinding.ActivityMainBinding
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.Resource
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.ui.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_test.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private val productRepository = WoocommerceApi.instance.productRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initSwipeRefreshLayout()
        initRecyclerView()
        getProducts()

    }

    /*
* Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
* performs a swipe-to-refresh gesture.
*/
    private fun initSwipeRefreshLayout() {

        binding.swipeRefreshLayout.setOnRefreshListener {
            // This method performs the actual data-refresh operation.
            // The method calls setRefreshing(false) when it's finished.
            getProducts()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.showShimmerAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }


    private fun getProducts() {
        productRepository.getProducts().observe(this, productsObserver)
    }

    private fun onFailureProducts(resource: Resource<List<Product>>) {

        if (resource.fromServer)
            stopRefreshingLayout()

        val message = resource.message
        Timber.i("onFailureProducts $message")
    }


    private fun onResponseProducts(resource: Resource<List<Product>>) {
        if (resource.fromServer)
            stopRefreshingLayout()

        val response: List<Product>? = resource.data
        Timber.i("onResponseProducts ${response?.size}")

        response?.sortedByDescending { it.date_modified }
            ?.filter { it.status == ProductHelper.Status.Publish.status }?.let { productList ->
                declareRecyclerView(productList)
            }
    }

    // Create the observer which updates the UI.
    private val productsObserver = Observer<Resource<List<Product>>> { resource ->
        // Update the UI, in this case, a TextView.
        when (resource) {
            is Resource.Loading -> Timber.i("getting all products..")
            is Resource.Success -> onResponseProducts(resource)
            is Resource.Error -> onFailureProducts(resource)
        }

    }

    private fun stopRefreshingLayout() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun declareRecyclerView(items: List<Product>) {
        adapter = ProductAdapter(items)
        recyclerView.adapter = adapter
    }
}