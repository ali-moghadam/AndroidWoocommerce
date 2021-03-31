package com.alirnp.androidwoocommerceapp.repository

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import com.alirnp.androidwoocommerceapp.core.helper.NetworkHelper
import com.alirnp.androidwoocommerceapp.core.helper.filter.ProductFilter
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.api.ProductAPI
import com.alirnp.androidwoocommerceapp.repository.roomDB.AppDatabase
import com.alirnp.androidwoocommerceapp.repository.roomDB.dao.ProductDao
import retrofit2.Call
import java.util.*


class ProductRepository(application: Application, baseUrl: String, consumerKey: String, consumerSecret: String) :
    WooRepository(application , baseUrl, consumerKey, consumerSecret) {

    private val productAPI: ProductAPI = retrofit.create(ProductAPI::class.java)


    fun create(product: Product): Call<Product> {
        return productAPI.create(product)
    }


    fun product(id: Int): Call<Product> {
        return productAPI.view(id)
    }

    /**
     * get list of product
     */
    fun getProducts(): LiveData<Resource<List<Product>>> {
        val productDao = AppDatabase.getInstance(application).productDao()

        return object : NetworkBoundResource<List<Product>, List<Product>>(AppExecutors()) {
            override fun saveCallResult(item: List<Product>) {
                productDao.insertAll(item)
            }

            override fun shouldFetch(data: List<Product>?) = NetworkHelper.isNetworkAvailable(application)

            override fun loadFromDb() = productDao.getAll()

            override fun createCall() = productAPI.list()
        }.asLiveData()
    }

    fun filter(filters: Map<String, String>): Call<List<Product>> {
        return productAPI.filter(filters)
    }

    fun products(productFilter: ProductFilter): Call<List<Product>> {
        return filter(productFilter.filters)
    }

    fun search(term: String): Call<List<Product>> {
        val productFilter = ProductFilter()
        productFilter.search = term

        return filter(productFilter.filters)
    }

    fun products(page: Int, per_page: Int): Call<List<Product>> {
        val productFilter = ProductFilter()
        productFilter.page = page
        productFilter.per_page = per_page

        return filter(productFilter.filters)
    }

    fun products(page: Int): Call<List<Product>> {
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

    fun products(filters: HashMap<String, String>): Call<List<Product>> {
        return productAPI.filter(filters)
    }


}