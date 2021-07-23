package com.alirnp.androidwoocommerceapp.viewModel

import androidx.lifecycle.*
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.Resource
import javax.inject.Inject


class CategoryViewModel @Inject constructor() : ViewModel() {

    private val categoryRepository = WoocommerceApi.instance.categoryRepository

    private val _categories = MediatorLiveData<Resource<List<Category>>>()
    val categories: LiveData<Resource<List<Category>>> get() = _categories

    /**
     * get all categories
     */
    fun fetchCategories() = getCategories()

    private fun getCategories()  {
        _categories.addSource(categoryRepository.getCategories()) {
            _categories.value = it
        }
    }
}