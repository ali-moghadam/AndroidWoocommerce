package com.alirnp.androidwoocommerceapp.repository

import android.app.Application
import android.util.Log
import com.alirnp.androidwoocommerceapp.core.woocomere.AuthInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class WooRepository(
    val application: Application,
    baseUrl: String,
    consumerKey: String,
    consumerSecret: String
) {

    private val TAG = "LOG_ME"

    //TODO ('Apply DI or single instance on this')
    var retrofit: Retrofit

    init {
        val loggingInterceptor =
            HttpLoggingInterceptor { logger -> Log.i(TAG, "retrofit log: $logger") }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()


        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(consumerKey, consumerSecret))
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(client)
            .build()
    }


}