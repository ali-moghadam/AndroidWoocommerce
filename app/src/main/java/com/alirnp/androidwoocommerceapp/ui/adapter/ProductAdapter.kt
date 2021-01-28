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
import me.gilo.woodroid.models.Product


class ProductAdapter(private val items: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.Holder>() {

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflatedView = parent.inflate(R.layout.item_product, false)
        return Holder(items,inflatedView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val itemPhoto = items[position]
        holder.bindPhoto(itemPhoto)
    }

    override fun getItemCount() = items.size


    class Holder(items: List<Product> , v: View) : RecyclerView.ViewHolder(v) {

        init {
            itemView.setOnClickListener {
                Toast.makeText(v.context, items[adapterPosition].name, Toast.LENGTH_SHORT).show()
            }
        }

        fun bindPhoto(product: Product) {

            itemView.textView_name.text = product.name

            if (product.images != null && product.images.size > 0) {
                val primaryImage = product.images[0].src
                Picasso.get().load(primaryImage).into(itemView.imageView_photo)
            }
        }
    }
}