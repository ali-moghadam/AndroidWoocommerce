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
import com.squareup.picasso.Picasso

class CategoryAdapter(private val items: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.Holder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)

        //val binding = ItemCategoryBinding.inflate(layoutInflater!!)
        val binding = DataBindingUtil.inflate<ItemCategoryBinding>(layoutInflater!! , R.layout.item_category,parent , false)
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
                Picasso.get().load(image.src).into(binding.imageViewPhoto)
            }
        }
    }
}
