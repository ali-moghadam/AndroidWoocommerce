package com.alirnp.androidwoocommerceapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.databinding.FragmentCategoryBinding
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.Resource
import timber.log.Timber

class CategoryFragment : Fragment() {

    private val categoryRepository = WoocommerceApi.instance.categoryRepository

    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        getCategories()

    }

    // Create the observer which updates the UI.
    private val categoryObserver = Observer<Resource<List<Category>>> { resource ->
        // Update the UI, in this case, a TextView.
        when (resource) {
            is Resource.Loading -> Timber.i("getting all categories..")
            is Resource.Success -> onResponseCategories(resource)
            is Resource.Error -> onFailureCategories(resource)
        }

    }

    private fun getCategories() {
        activity?.let { categoryRepository.getCategories().observe(it, categoryObserver) }
    }

    private fun onFailureCategories(resource: Resource<List<Category>>) {
        val message = resource.message
        Timber.i("onFailureCategories $message")
    }


    private fun onResponseCategories(resource: Resource<List<Category>>) {
        val response: List<Category>? = resource.data
        Timber.i("onResponseCategories ${response?.size}")
        
        binding.textView.text = "categories size ${response?.size}"
    }
}