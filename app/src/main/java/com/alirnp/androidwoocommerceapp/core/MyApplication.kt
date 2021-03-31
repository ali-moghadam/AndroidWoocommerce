package com.alirnp.androidwoocommerceapp.core

import android.app.Application
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        WoocommerceApi.init(this)
    }
}