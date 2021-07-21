package com.alirnp.androidwoocommerceapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alirnp.androidwoocommerceapp.databinding.ItemProductBinding
import com.alirnp.androidwoocommerceapp.model.Product
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class ProductAdapter(private val items: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.Holder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemProductBinding.inflate(layoutInflater!!)
        return Holder(binding, items)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size


    inner class Holder(private val binding: ItemProductBinding, items: List<Product>) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    items[adapterPosition].name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        fun bind(product: Product) {

            // set product name
            binding.textViewName.text = product.name

            // load product image into imageView
            loadProductImage(product)
        }

        private fun loadProductImage(product: Product) {

            if (product.getFeatureImage().isNotEmpty()) {

                val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(16))

                Glide.with(binding.root.context)
                    .load(product.getFeatureImage())
                  //  .apply(requestOptions)
                    .into(binding.imageViewPhoto)
            }
        }
    }
}
