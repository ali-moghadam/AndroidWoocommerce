package com.alirnp.androidwoocommerceapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.core.helper.ProductHelper
import com.alirnp.androidwoocommerceapp.databinding.FragmentProfileBinding
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.Resource
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import com.alirnp.androidwoocommerceapp.ui.adapter.ProductAdapter
import timber.log.Timber

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mContext: Context
    lateinit var navController: NavController
    private val productRepository = WoocommerceApi.instance.productRepository()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        //  showDefaultImage()


        binding.recyclerView.layoutManager = GridLayoutManager(mContext, 2)

        getProducts()

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