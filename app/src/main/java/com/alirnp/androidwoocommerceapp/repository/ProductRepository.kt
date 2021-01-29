package com.alirnp.androidwoocommerceapp.repository

import androidx.lifecycle.LiveData
import me.gilo.woodroid.models.Product
import retrofit2.Response

class ProductRepository () {

    fun getProducts() =
        object : NetworkBoundResource<Product, Product>(){
            override suspend fun saveCallResult(item: Product) {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: Product?): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun loadFromDb(): LiveData<Product> {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<Response<Product>> {
                TODO("Not yet implemented")
            }

        }
}