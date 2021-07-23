package com.alirnp.androidwoocommerceapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.core.constant.TYPE_EMPTY_STATE
import com.alirnp.androidwoocommerceapp.databinding.ItemCategoryBinding
import com.alirnp.androidwoocommerceapp.databinding.ItemEmptyStateBinding
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.ui.adapter.recyclerView.EmptyStateHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class CategoryAdapter(private val items: List<Category>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemViewType(position: Int): Int {
        if (items.isEmpty())
            return TYPE_EMPTY_STATE

        return super.getItemViewType(position)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)

        return if (viewType == TYPE_EMPTY_STATE) {
            val binding = ItemEmptyStateBinding.inflate(layoutInflater!!)
            EmptyStateHolder(binding)

        } else {
            val binding = ItemCategoryBinding.inflate(layoutInflater!!)
            CategoryHolder(binding, items)
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CategoryHolder) {
            val item = items[position]
            holder.bind(item)
        }
    }

    override fun getItemCount() : Int {
        return if (items.isEmpty())
            1
        else
            items.size
    }


    inner class CategoryHolder(private val binding: ItemCategoryBinding, items: List<Category>) :
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
                    .placeholder(R.drawable.woocommerce_placeholder)
                    .into(binding.imageViewPhoto)
            }
        }
    }
}
