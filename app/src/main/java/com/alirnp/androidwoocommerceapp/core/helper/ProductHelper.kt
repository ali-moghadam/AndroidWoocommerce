package com.alirnp.androidwoocommerceapp.core.helper

class ProductHelper {

    enum class Status(val status: String) {
        Publish("publish"),
        Draft("draft"),
        Pending("pending"),
    }

    enum class StockStatus(val stockStatus: String) {
        InStock("instock"),
        OutOfStock("outofstock"),
        OnBackOrder("onbackorder")
    }
}