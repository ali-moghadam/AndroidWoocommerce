package com.alirnp.androidwoocommerceapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.core.helper.ProductHelper
import com.alirnp.androidwoocommerceapp.databinding.FragmentMainBinding
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.Resource
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.ui.adapter.ProductAdapter
import timber.log.Timber

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: ProductAdapter
    private val productRepository = WoocommerceApi.instance.productRepository
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSwipeRefreshLayout()
        initRecyclerView()

        getProducts()

    }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            getProducts()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.showShimmerAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(mContext, 2)
    }


    private fun getProducts() {
        // TODO: 4/20/2021 check it
        activity?.let { productRepository.getProducts().observe(it, productsObserver) }
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
        binding.recyclerView.adapter = adapter
    }
}