package com.alirnp.androidwoocommerceapp.viewModel

import androidx.lifecycle.*
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.repository.CategoryRepository
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor( private val categoryRepository : CategoryRepository) : ViewModel() {

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