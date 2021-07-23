package com.alirnp.androidwoocommerceapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.alirnp.androidwoocommerceapp.core.helper.NetworkHelper
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.repository.api.CategoryAPI
import com.alirnp.androidwoocommerceapp.repository.base.WooRepository
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.AppExecutors
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.NetworkBoundResource
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.Resource
import com.alirnp.androidwoocommerceapp.repository.roomDB.AppDatabase


class CategoryRepository(application: Application, baseUrl: String) :
    WooRepository(application, baseUrl) {

    private val categoryAPI: CategoryAPI = retrofit.create(CategoryAPI::class.java)

    /**
     * get all categories
     */
    fun getCategories(): LiveData<Resource<List<Category>>> {
        val categoryDao = AppDatabase.getInstance(application).categoryDao()

        return object : NetworkBoundResource<List<Category>, List<Category>>(AppExecutors()) {
            override fun saveCallResult(item: List<Category>) {
                categoryDao.deleteAll()
                categoryDao.insertAll(item)
            }

            override fun shouldFetch(data: List<Category>?) = NetworkHelper.isNetworkAvailable(application)

            override fun loadFromDb() = categoryDao.getAll()

            override fun createCall() = categoryAPI.getAllCategories()
        }.asLiveData()
    }
}