package com.alirnp.androidwoocommerceapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.databinding.ItemCategoryBinding
import com.alirnp.androidwoocommerceapp.databinding.ItemProductBinding
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.model.Product
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class CategoryAdapter(private val items: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.Holder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemCategoryBinding.inflate(layoutInflater!!)
        //  val binding = DataBindingUtil.inflate<ItemCategoryBinding>(layoutInflater!! , R.layout.item_category,parent , false)
        return Holder(binding, items)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size


    inner class Holder(private val binding: ItemCategoryBinding, items: List<Category>) :
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

        fun bind(category: Category) {

            // set product name
            binding.textViewName.text = category.name

            // load product image into imageView
            loadProductImage(category)
        }

        private fun loadProductImage(category: Category) {

            category.image?.let { image ->
                Glide.with(binding.root.context)
                    .load(image.src)
                    .apply(RequestOptions().override(1000, 1000))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imageViewPhoto)
            }
        }
    }
}
