package com.alirnp.androidwoocommerceapp.core

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.NonNull
import androidx.multidex.MultiDexApplication
import com.alirnp.androidwoocommerceapp.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(MyTimberTree())
        }
    }

    /** A tree which logs important information for crash reporting.  */
    private class MyTimberTree : Timber.Tree() {
        @SuppressLint("LogNotTimber")
        override fun log(priority: Int, tag: String?, @NonNull message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            Log.i("WooApp", message)
        }
    }
}