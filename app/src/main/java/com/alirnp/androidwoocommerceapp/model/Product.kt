package com.alirnp.androidwoocommerceapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity
class Product : Serializable {

    @PrimaryKey
    var id: Int = 0
    lateinit var name: String
    var slug: String? = null
    var permalink: String? = null
    var type: String? = null
    lateinit var status: String
    var isFeatured: Boolean = false
    lateinit var catalog_visibility: String
    lateinit var description: String
    lateinit var short_description: String
    lateinit var sku: String
    lateinit var price: String
    lateinit var regular_price: String
    lateinit var sale_price: String
    lateinit var date_on_sale_from: Date
    lateinit var date_on_sale_from_gmt: Date
    lateinit var date_on_sale_to: Date
    lateinit var date_on_sale_to_gmt: Date
    lateinit var price_html: String
    var isOn_sale: Boolean = false
    var isPurchasable: Boolean = false
    var total_sales: Int = 0
    var isVirtual: Boolean = false
    var isDownloadable: Boolean = false
    lateinit var downloads: List<Download>
    var download_limit: Int = 0
    var download_expiry: Int = 0
    lateinit var external_url: String
    lateinit var button_text: String
    lateinit var tax_status: String
    lateinit var tax_class: String
    var isManage_stock: Boolean = false
    var stock_quantity: Int = 0
    var isIn_stock: Boolean = false
    lateinit var backorders: String
    var isBackorders_allowed: Boolean = false
    var isBackordered: Boolean = false
    var isSold_individually: Boolean = false
    lateinit var weight: String
   // var dimensions: String = ""
    var isShipping_required: Boolean = false
    var isShipping_taxable: Boolean = false
    lateinit var shipping_class: String
    var shipping_class_id: Int = 0
    var isReviews_allowed: Boolean = false
    lateinit var average_rating: String
    var rating_count: Int = 0

    lateinit var related_ids: List<Int>
    lateinit var upsell_ids: List<Int>
    lateinit var cross_sell_ids: List<Int>
    var parent_id: Int = 0
    lateinit var purchase_note: String
    lateinit var categories: List<Category>
    lateinit var tags: List<Tag>

    @SerializedName("attributes")
    lateinit var productAttributes: List<ProductAttribute>

    lateinit var default_attributes: List<DefaultAttribute>
    lateinit var variations: List<Int>
    lateinit var grouped_products: List<Int>
    var menu_order: Int = 0
    // lateinit var meta_data: List<Metadata>


    lateinit var images: List<Image>

    fun getFeatureImage(): String {
        if (this.images.isEmpty()) {
            return ""
        }

        return this.images.first().src!!
    }


}

