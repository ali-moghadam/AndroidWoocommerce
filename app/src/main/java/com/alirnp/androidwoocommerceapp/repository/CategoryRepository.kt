package com.alirnp.androidwoocommerceapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.alirnp.androidwoocommerceapp.core.helper.NetworkHelper
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.repository.api.CategoryAPI
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.AppExecutors
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.NetworkBoundResource
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.Resource
import com.alirnp.androidwoocommerceapp.repository.roomDB.AppDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class CategoryRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val categoryAPI: CategoryAPI,
    @ApplicationContext private val context: Context
) {

    /**
     * get all categories
     */
    fun getCategories(): LiveData<Resource<List<Category>>> {
        val categoryDao = appDatabase.categoryDao()

        return object : NetworkBoundResource<List<Category>, List<Category>>(AppExecutors()) {
            override fun saveCallResult(item: List<Category>) {
                categoryDao.deleteAll()
                categoryDao.insertAll(item)
            }

            override fun shouldFetch(data: List<Category>?) =
                NetworkHelper.isNetworkAvailable(context)

            override fun loadFromDb() = categoryDao.getAll()

            override fun createCall() = categoryAPI.getAllCategories()

        }.asLiveData()
    }
}