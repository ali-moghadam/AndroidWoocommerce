package com.alirnp.androidwoocommerceapp.core

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.NonNull
import androidx.multidex.MultiDexApplication
import com.alirnp.androidwoocommerceapp.BuildConfig
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import timber.log.Timber


class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        initWoocommerce()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(MyTimberTree())
        }
    }

    private fun initWoocommerce() {
        WoocommerceApi.init(this)
    }

    /** A tree which logs important information for crash reporting.  */
    private class MyTimberTree : Timber.Tree() {
        @SuppressLint("LogNotTimber")
        override fun log(priority: Int, tag: String?, @NonNull message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            Log.i("LOG_ME", "$message")
        }
    }
}