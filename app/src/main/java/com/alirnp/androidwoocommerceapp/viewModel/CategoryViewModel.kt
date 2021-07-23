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
    private var categoriesSource: LiveData<Resource<List<Category>>> = MutableLiveData()


    /**
     * get all categories
     */
    fun fetchCategories() = getCategories()

    private fun getCategories()  {
        _categories.removeSource(categoriesSource) // We make sure there is only one source of livedata (allowing us properly refresh)
        categoriesSource = categoryRepository.getCategories()
        _categories.addSource(categoriesSource) {
            _categories.value = it
        }
    }
}