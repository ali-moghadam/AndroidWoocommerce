package com.alirnp.androidwoocommerceapp.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import com.alirnp.androidwoocommerceapp.core.helper.filter.ProductFilter
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.api.ProductAPI
import com.alirnp.androidwoocommerceapp.repository.roomDB.dao.ProductDao
import retrofit2.Call
import java.util.*


class ProductRepository(baseUrl: String, consumerKey: String, consumerSecret: String) :
    WooRepository(baseUrl, consumerKey, consumerSecret) {

    private val productAPI: ProductAPI = retrofit.create(ProductAPI::class.java)


    fun create(product: Product): Call<Product> {
        return productAPI.create(product)
    }


    fun product(id: Int): Call<Product> {
        return productAPI.view(id)
    }

/*    fun products(): Call<List<Product>> {
        return productAPI.list()
    }*/

    fun getProducts(context: Context? , productDao: ProductDao, appExecutors: AppExecutors): LiveData<Resource<List<Product>>> {
        return object : NetworkBoundResource<List<Product>, List<Product>>(appExecutors) {
            override fun saveCallResult(items: List<Product>) {
                productDao.insertAll(items)
            }

            override fun shouldFetch(data: List<Product>?) = isNetworkAvailable(context)

            override fun loadFromDb() = productDao.getAll()

            override fun createCall() = productAPI.list()
        }.asLiveData()
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
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