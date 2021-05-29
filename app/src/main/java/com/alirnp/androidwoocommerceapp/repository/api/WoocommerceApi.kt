package com.alirnp.androidwoocommerceapp.repository.api

import android.app.Application
import com.alirnp.androidwoocommerceapp.core.woocomere.Woocommerce

object WoocommerceApi {

    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    val instance: Woocommerce by lazy { Woocommerce(application) }
}



