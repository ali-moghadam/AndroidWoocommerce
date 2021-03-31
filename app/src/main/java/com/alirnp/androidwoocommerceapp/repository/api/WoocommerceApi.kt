package com.alirnp.androidwoocommerceapp.repository.api

import android.app.Application
import com.alirnp.androidwoocommerceapp.Config
import com.alirnp.androidwoocommerceapp.core.woocomere.Woocommerce

object WoocommerceApi {

    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    val instance: Woocommerce by lazy {
        Woocommerce.Builder(application)
            .setSiteUrl(Config.URL)
            .setApiVersion(Woocommerce.API_V3)
            .setConsumerKey(Config.CONSUMER_KEY)
            .setConsumerSecret(Config.CONSUMER_SECRET)
            .build()
    }
}



