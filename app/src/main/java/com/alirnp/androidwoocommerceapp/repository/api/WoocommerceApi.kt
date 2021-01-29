package com.alirnp.androidwoocommerceapp.repository.api

import com.alirnp.androidwoocommerceapp.Config
import com.alirnp.androidwoocommerceapp.auth.Woocommerce

object WoocommerceApi {

    val instance : Woocommerce by lazy {
        Woocommerce.Builder()
            .setSiteUrl(Config.URL)
            .setApiVersion(Woocommerce.API_V3)
            .setConsumerKey(Config.CONSUMER_KEY)
            .setConsumerSecret(Config.CONSUMER_SECRET)
            .build()
    }
}



