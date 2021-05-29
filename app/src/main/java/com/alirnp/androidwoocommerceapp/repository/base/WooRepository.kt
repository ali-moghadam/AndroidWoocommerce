package com.alirnp.androidwoocommerceapp.repository.base

import android.app.Application
import com.alirnp.androidwoocommerceapp.Config
import com.alirnp.androidwoocommerceapp.core.woocomere.AuthInterceptor
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

open class WooRepository(
    val application: Application,
    baseUrl: String
) {

    //TODO ('Apply DI or single instance on this')
    var retrofit: Retrofit

    init {
        val loggingInterceptor =
            HttpLoggingInterceptor { logger -> Timber.i("retrofit -> $logger") }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(Config.CONSUMER_KEY, Config.CONSUMER_SECRET))
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            // TODO: 5/29/2021 remove  RxJava2CallAdapterFactory
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(client)
            .build()
    }
}