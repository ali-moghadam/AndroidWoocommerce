package com.alirnp.androidwoocommerceapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.alirnp.androidwoocommerceapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*
import com.alirnp.androidwoocommerceapp.model.Product

class ProductAdapter(private val items: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.Holder>() {

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflatedView = parent.inflate(R.layout.item_product)
        return Holder(items,inflatedView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    class Holder(items: List<Product>, v: View) : RecyclerView.ViewHolder(v) {

        init {
            itemView.setOnClickListener {
                Toast.makeText(v.context, items[adapterPosition].name, Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(product: Product) {

            // set product name
            itemView.textView_name.text = product.name

            // load product image into imageView
            loadProductImage(product)
        }

        private fun loadProductImage(product: Product) {
            if (product.images != null && product.images.size > 0) {
                val images = product.images
                val primaryImage = images[0].src
                Picasso.get().load(primaryImage).into(itemView.imageView_photo)
            }
        }
    }
}