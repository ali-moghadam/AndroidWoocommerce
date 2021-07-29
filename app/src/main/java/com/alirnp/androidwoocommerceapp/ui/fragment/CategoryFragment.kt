package com.alirnp.androidwoocommerceapp.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.core.decorator.GridSpacingItemDecoration
import com.alirnp.androidwoocommerceapp.databinding.FragmentCategoryBinding
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.Resource
import com.alirnp.androidwoocommerceapp.ui.adapter.CategoryAdapter
import com.alirnp.androidwoocommerceapp.viewModel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var binding: FragmentCategoryBinding

    private var defaultSpanCount = 2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setupSweepRefreshLayout()
        observeCategories()

        fetchCategories()

    }

    private fun setupSweepRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchCategories()
        }
    }

    // Create the observer which updates the UI.
    private val categoryObserver = Observer<Resource<List<Category>>> { resource ->
        when (resource) {
            is Resource.Loading -> Timber.i("trying to get all categories..")
            is Resource.Success -> onResponseCategories(resource)
            is Resource.Error -> onFailureCategories(resource)
        }

    }

    private fun observeCategories() {
        categoryViewModel.categories.observe(requireActivity(), categoryObserver)
    }

    private fun fetchCategories() {
        categoryViewModel.fetchCategories()
    }

    private fun onFailureCategories(resource: Resource<List<Category>>) {
        binding.swipeRefreshLayout.isRefreshing = false

        val message = resource.message
        Timber.i("onFailureCategories $message")

        context?.let { ctx ->
            Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
        }
    }


    private fun onResponseCategories(resource: Resource<List<Category>>) {
        binding.swipeRefreshLayout.isRefreshing = false

        val response: List<Category>? = resource.data

        response?.let { categories ->
            Timber.i("onResponseCategories ${categories.size}")
            declareRecyclerView(categories)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            val space = context.resources.getDimension(R.dimen._10sdp).toInt()
            val itemDecoration = GridSpacingItemDecoration(defaultSpanCount, space, true, 0)

            addItemDecoration(itemDecoration)
            showShimmerAdapter()
            layoutManager = GridLayoutManager(requireContext(), defaultSpanCount)
        }
    }

    private fun declareRecyclerView(items: List<Category>) {

        context?.let {
            binding.recyclerView.apply {
                if (items.isEmpty()) {
                    layoutManager = GridLayoutManager(requireContext(), 1)
                    adapter = CategoryAdapter(listOf())

                } else {
                    layoutManager = GridLayoutManager(requireContext(), defaultSpanCount)
                    adapter = CategoryAdapter(items)
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.category_menu, menu)
        super.onCreateOptionsMenu(menu, inflater);
    }
}