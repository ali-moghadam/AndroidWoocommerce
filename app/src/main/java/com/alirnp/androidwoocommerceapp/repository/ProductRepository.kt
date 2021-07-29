package com.alirnp.androidwoocommerceapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.alirnp.androidwoocommerceapp.core.helper.NetworkHelper
import com.alirnp.androidwoocommerceapp.core.helper.filter.ProductFilter
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.api.ProductAPI
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.ApiResponse
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.AppExecutors
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.NetworkBoundResource
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.Resource
import com.alirnp.androidwoocommerceapp.repository.roomDB.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import java.util.*
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val productAPI: ProductAPI,
    @ApplicationContext private val context: Context
) {

    /**
     * get all products
     */
    fun getProducts(): LiveData<Resource<List<Product>>> {
        val productDao = appDatabase.productDao()

        return object : NetworkBoundResource<List<Product>, List<Product>>(AppExecutors()) {
            override fun saveCallResult(item: List<Product>) {
                productDao.insertAll(item)
            }

            override fun shouldFetch(data: List<Product>?) =
                NetworkHelper.isNetworkAvailable(context)

            override fun loadFromDb() = productDao.getAll()

            override fun createCall() = productAPI.getAllProducts()
        }.asLiveData()
    }

    /**
     * get filtered products
     */
    fun getProducts(productFilter: ProductFilter): LiveData<Resource<List<Product>>> {
        val productDao = appDatabase.productDao()

        return object : NetworkBoundResource<List<Product>, List<Product>>(AppExecutors()) {
            override fun saveCallResult(item: List<Product>) {
                productDao.insertAll(item)
            }

            override fun shouldFetch(data: List<Product>?) =
                NetworkHelper.isNetworkAvailable(context)

            override fun loadFromDb() = productDao.getAll()

            override fun createCall() = filter(productFilter.filters)
        }.asLiveData()
    }

    private fun filter(filters: Map<String, String>): LiveData<ApiResponse<List<Product>>> {
        return productAPI.filter(filters)
    }

    fun products(page: Int, per_page: Int): LiveData<ApiResponse<List<Product>>> {
        val productFilter = ProductFilter()
        productFilter.page = page
        productFilter.per_page = per_page

        return filter(productFilter.filters)
    }


    fun create(product: Product): Call<Product> {
        return productAPI.create(product)
    }


    fun product(id: Int): Call<Product> {
        return productAPI.view(id)
    }

    fun products(productFilter: ProductFilter): LiveData<ApiResponse<List<Product>>> {
        return filter(productFilter.filters)
    }

    fun search(term: String): LiveData<ApiResponse<List<Product>>> {
        val productFilter = ProductFilter()
        productFilter.search = term

        return filter(productFilter.filters)
    }

    fun products(page: Int): LiveData<ApiResponse<List<Product>>> {
        val productFilter = ProductFilter()
        productFilter.page = page

        return filter(productFilter.filters)
    }

    fun update(id: Int, product: Product): Call<Product> {
        return productAPI.update(id, product)
    }

    fun delete(id: Int): Call<Product> {
        return productAPI.delete(id)
    }

    fun delete(id: Int, force: Boolean): Call<Product> {
        return productAPI.delete(id, force)
    }

    fun products(filters: HashMap<String, String>): LiveData<ApiResponse<List<Product>>> {
        return productAPI.filter(filters)
    }
}