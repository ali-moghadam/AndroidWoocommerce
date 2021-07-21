package com.alirnp.androidwoocommerceapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.core.helper.ProductHelper
import com.alirnp.androidwoocommerceapp.databinding.FragmentProfileBinding
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.Resource
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.ui.adapter.ProductAdapter
import com.alirnp.androidwoocommerceapp.viewModel.UserViewModel
import timber.log.Timber

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mContext: Context
    lateinit var navController: NavController
    private val productRepository = WoocommerceApi.instance.productRepository

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController()

        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle
        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry, { success ->
                if (!success) {
                    val startDestination = navController.graph.startDestination
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(startDestination, true)
                        .build()
                    navController.navigate(startDestination, null, navOptions)
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        observeUser()

        binding.recyclerView.layoutManager = GridLayoutManager(mContext, 2)

        //  showDefaultImage()

        getProducts()
    }

    private fun observeUser() {

        userViewModel.user.observe(viewLifecycleOwner, { user ->
            if (user != null) {
                showWelcomeMessage()
            } else {
                navController.navigate(R.id.loginFragment)
            }
        })
    }

    private fun showWelcomeMessage() {
        Toast.makeText(context, "Welcome Message", Toast.LENGTH_SHORT).show()
    }

    // Create the observer which updates the UI.
    private val productsObserver = Observer<Resource<List<Product>>> { resource ->
        // Update the UI, in this case, a TextView.
        when (resource) {
            is Resource.Loading -> Timber.i("getting all products..")
            is Resource.Success -> onResponseProducts(resource)
            is Resource.Error -> onFailureProducts(resource)
        }

    }

    private fun getProducts() {
        // TODO: 4/20/2021 check it
        activity?.let { productRepository.getProducts().observe(it, productsObserver) }
    }

    private fun onFailureProducts(resource: Resource<List<Product>>) {
        val message = resource.message
        Timber.i("onFailureProducts $message")
    }


    private fun onResponseProducts(resource: Resource<List<Product>>) {
        val response: List<Product>? = resource.data
        Timber.i("onResponseProducts ${response?.size}")

        response?.sortedByDescending { it.date_modified }
            ?.filter { it.status == ProductHelper.Status.Publish.status }?.let { productList ->
                declareRecyclerView(productList)
            }
    }

    private fun declareRecyclerView(items: List<Product>) {
        val adapter = ProductAdapter(items)
        binding.recyclerView.adapter = adapter
    }

    /* private fun showImage(bitmap: Bitmap) {
         Glide.with(this)
             .load(bitmap)
             .circleCrop()
             .diskCacheStrategy(DiskCacheStrategy.ALL)
             .into(binding.imageViewUserImage)
     }

     private fun showImage(drawable: Drawable?) {
         Glide.with(this)
             .load(drawable)
             .circleCrop()
             .diskCacheStrategy(DiskCacheStrategy.ALL)
             .into(binding.imageViewUserImage)
     }

     private fun showDefaultImage() {
         val drawableHolder = ContextCompat.getDrawable(mContext, R.drawable.placeholder_image)
         showImage(drawableHolder)

     }*/
}